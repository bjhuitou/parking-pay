package net.itgoo.parkingpay.ui.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.contact.ParkingContactActivity;
import net.itgoo.parkingpay.ui.edit.ParkingEditFragment;
import net.itgoo.parkingpay.ui.home.ParkingHomeFragment;
import net.itgoo.parkingpay.ui.orders.ParkingOrdersActivity;
import net.itgoo.parkingpay.ui.park.ParkingParkFragment;
import net.itgoo.parkingpay.ui.rfid.ParkingRfidFragment;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.parkingpay.vendor.widget.fragmentBackHandler.FragmentBackHandler;

import java.util.ArrayList;
import java.util.List;

import hk.ids.gws.android.sclick.SClick;

import static net.itgoo.parkingpay.ui.home.ParkingHomeChildFragment.PARKING_ACTION_PLATE_NAME;

public class ParkingMainFragment extends ParkingBaseFragment implements ParkingMainContract.View,
        FragmentBackHandler {

    public static final String PARKING_ACTION_SHOW_MAIN_MENU = "parking_action_show_main_menu";
    public static final String PARKING_ACTION_SHOW_PLATE_INFO = "parking_action_show_plate_info";
    private static final int PARKING_FRAGMENT_EDIT_INDEX = 0;
    private static final int PARKING_FRAGMENT_HOME_INDEX = 1;
    private static final int PARKING_FRAGMENT_PARK_INDEX = 2;
    private static final int PARKING_FRAGMENT_RFID_INDEX = 3;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private ParkingMainContract.Presenter mPresenter;
    private View mActionView;
    private ParkingEditFragment mParkingEditFragment;
    private ParkingHomeFragment mParkingHomeFragment;
    private ParkingParkFragment mParkingParkFragment;
    private ParkingRfidFragment mParkingRfidFragment;

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
        filter.addAction(PARKING_ACTION_SHOW_PLATE_INFO);
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
        initFragments();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        requestPermission();
    }

    private void initViews() {
        View contentView = getContentView();
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

    private void initFragments() {
        showFragment(PARKING_FRAGMENT_EDIT_INDEX);
    }

    private void onHomeAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        showFragment(PARKING_FRAGMENT_HOME_INDEX);
    }

    private void onParkAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        showFragment(PARKING_FRAGMENT_PARK_INDEX);
    }

    private void onRfidAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        showFragment(PARKING_FRAGMENT_RFID_INDEX);
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
        mActionView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private void requestPermission() {
        String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[i]);
            }
        }
        if (permissionList.size() > 0) {
            requestPermissions(permissionList.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        } else {
            mPresenter.requestLocation();
        }
    }

    private void showPlateInfo(String plate) {
        showFragment(PARKING_FRAGMENT_HOME_INDEX);
        Intent intent = new Intent(PARKING_ACTION_PLATE_NAME);
        intent.putExtra("plate", plate);
        getActivity().sendBroadcast(intent);
    }

    private void showFragment(int position) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (mParkingEditFragment != null) {
            beginTransaction.hide(mParkingEditFragment);
        }
        if (mParkingHomeFragment != null) {
            beginTransaction.hide(mParkingHomeFragment);
        }
        if (mParkingParkFragment != null) {
            beginTransaction.hide(mParkingParkFragment);
        }
        if (mParkingRfidFragment != null) {
            beginTransaction.hide(mParkingRfidFragment);
        }

        switch (position) {
            case PARKING_FRAGMENT_EDIT_INDEX:
                if (mParkingEditFragment == null) {
                    mParkingEditFragment = new ParkingEditFragment();
                    beginTransaction.add(R.id.parking_fragment_main_fl, mParkingEditFragment);
                } else {
                    beginTransaction.show(mParkingEditFragment);
                }
                break;

            case PARKING_FRAGMENT_HOME_INDEX:
                if (mParkingHomeFragment == null) {
                    mParkingHomeFragment = new ParkingHomeFragment();
                    beginTransaction.add(R.id.parking_fragment_main_fl, mParkingHomeFragment);
                } else {
                    beginTransaction.show(mParkingHomeFragment);
                }
                break;

            case PARKING_FRAGMENT_PARK_INDEX:
                if (mParkingParkFragment == null) {
                    mParkingParkFragment = new ParkingParkFragment();
                    beginTransaction.add(R.id.parking_fragment_main_fl, mParkingParkFragment);
                } else {
                    beginTransaction.show(mParkingParkFragment);
                }
                break;

            case PARKING_FRAGMENT_RFID_INDEX:
                if (mParkingRfidFragment == null) {
                    mParkingRfidFragment = new ParkingRfidFragment();
                    beginTransaction.add(R.id.parking_fragment_main_fl, mParkingRfidFragment);
                } else {
                    beginTransaction.show(mParkingRfidFragment);
                }
                break;
        }
        beginTransaction.commit();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (PARKING_ACTION_SHOW_MAIN_MENU.equals(intent.getAction())) {
                boolean show = intent.getBooleanExtra("show", false);
                showActionView(show);
            } else if (PARKING_ACTION_SHOW_PLATE_INFO.equals(intent.getAction())) {
                String plate = intent.getStringExtra("plate");
                showPlateInfo(plate);
                showActionView(true);
            }
        }
    };

    @Override
    public boolean onBackPressed() {
        if (mParkingEditFragment.isHidden()) {
            showFragment(PARKING_FRAGMENT_EDIT_INDEX);
            showActionView(false);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;
        if (PERMISSION_REQUEST_CODE == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            if (!hasPermissionDismiss) {
                mPresenter.requestLocation();
            }
        }
    }
}
