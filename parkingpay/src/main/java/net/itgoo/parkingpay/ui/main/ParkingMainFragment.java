package net.itgoo.parkingpay.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.contact.ParkingContactActivity;
import net.itgoo.parkingpay.ui.home.ParkingHomeFragment;
import net.itgoo.parkingpay.ui.orders.ParkingOrdersActivity;
import net.itgoo.parkingpay.ui.park.ParkingParkFragment;
import net.itgoo.parkingpay.ui.rfid.ParkingRfidFragment;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

import java.util.ArrayList;
import java.util.List;

import hk.ids.gws.android.sclick.SClick;
import me.tabak.fragmentswitcher.FragmentStateArrayPagerAdapter;
import me.tabak.fragmentswitcher.FragmentSwitcher;

public class ParkingMainFragment extends ParkingBaseFragment implements ParkingMainContract.View {

    public static final String PARKING_ACTION_SHOW_MAIN_MENU = "parking_action_show_main_menu";
    private ParkingMainContract.Presenter mPresenter;
    private FragmentSwitcher mFragmentSwitcher;
    private View mActionView;
    private FragmentStateArrayPagerAdapter mFragmentArrayPagerAdapter;

    public ParkingMainFragment() {
        // Required empty public constructor
    }

    public static ParkingMainFragment newInstance(Bundle bundle) {
        ParkingMainFragment fragment = new ParkingMainFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.start();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(PARKING_ACTION_SHOW_MAIN_MENU);
        getActivity().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.stop();
        }
        getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void setPresenter(ParkingMainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initViews();
        initFragmentSwitcher();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        requestPermission();
    }

    private void initViews() {
        View contentView = getContentView();
        mFragmentSwitcher = contentView.findViewById(R.id.parking_fragment_main_fs);
        mActionView = contentView.findViewById(R.id.parking_fragment_main_action_view);
        contentView.findViewById(R.id.parking_fragment_main_home_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHomeAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_main_park_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onParkAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_main_rfid_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRfidAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_main_history_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHistoryAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_main_info_contact_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContactAction();
            }
        });
    }

    private void initFragmentSwitcher() {
        mFragmentArrayPagerAdapter = new FragmentStateArrayPagerAdapter(getFragmentManager());
        mFragmentSwitcher.setAdapter(mFragmentArrayPagerAdapter);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ParkingHomeFragment.newInstance());
        fragments.add(ParkingParkFragment.newInstance());
        fragments.add(ParkingRfidFragment.newInstance());
        mFragmentArrayPagerAdapter.addAll(fragments);
        mFragmentSwitcher.setCurrentItem(0);
    }

    private void onHomeAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mFragmentSwitcher.setCurrentItem(0);
    }

    private void onParkAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mFragmentSwitcher.setCurrentItem(1);
    }

    private void onRfidAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mFragmentSwitcher.setCurrentItem(2);
    }

    private void onHistoryAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingOrdersActivity.class);
        startActivity(intent);
    }

    private void onContactAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingContactActivity.class);
        startActivity(intent);
    }

    @Override
    public void showActionView(boolean show) {
        mActionView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void requestPermission() {
        AndPermission.with(getActivity())
                .runtime()
                .permission(Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        mPresenter.requestLocation();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                    }
                })
                .start();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (PARKING_ACTION_SHOW_MAIN_MENU.equals(intent.getAction())) {
                boolean show = intent.getBooleanExtra("show", false);
                showActionView(show);
            }
        }
    };
}
