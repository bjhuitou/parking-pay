package net.itgoo.parkingpay.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.image.ParkingImageActivity;
import net.itgoo.parkingpay.ui.parkings.ParkingParkingsActivity;
import net.itgoo.parkingpay.ui.payconfirm.ParkingPayConfirmActivity;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

import hk.ids.gws.android.sclick.SClick;

import static net.itgoo.parkingpay.ui.main.ParkingMainFragment.PARKING_ACTION_SHOW_MAIN_MENU;

public class ParkingHomeChildFragment extends ParkingBaseFragment implements ParkingHomeContract.View {

    public static final String PARKING_ACTION_PLATE_NAME = "parking_action_plate_name";
    private ParkingHomeContract.Presenter mPresenter;
    private TextView mPlateTextView;

    public ParkingHomeChildFragment() {
        // Required empty public constructor
    }

    public static ParkingHomeChildFragment newInstance() {
        return new ParkingHomeChildFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.start();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(PARKING_ACTION_PLATE_NAME);
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
    public void setPresenter(ParkingHomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_home_child;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initViews();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initViews() {
        View contentView = getContentView();
        mPlateTextView = contentView.findViewById(R.id.parking_fragment_home_plate_tv);
        contentView.findViewById(R.id.parking_fragment_home_left_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPreviousPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_right_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_child_park_selection_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onParkingsAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_child_park_image_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVehicleImageAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_pay_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPayAction();
            }
        });
    }

    private void onPreviousPlateAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.previousPlate();
    }

    private void onNextPlateAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.nextPlate();
    }

    private void onVehicleImageAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingImageActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }

    private void onParkingsAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingParkingsActivity.class);
        startActivity(intent);
    }

    private void onPayAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingPayConfirmActivity.class);
        startActivity(intent);
    }

    @Override
    public void showPlate(String plate) {
        mPlateTextView.setText(plate);
    }

    @Override
    public void showMainActionView(boolean show) {
        Intent intent = new Intent(PARKING_ACTION_SHOW_MAIN_MENU);
        intent.putExtra("show", show);
        getActivity().sendBroadcast(intent);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (PARKING_ACTION_PLATE_NAME.equals(intent.getAction())) {
                String plate = intent.getStringExtra("plate");
                mPresenter.setPlate(plate);
            }
        }
    };
}
