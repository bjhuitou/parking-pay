package net.itgoo.parkingpay.ui.vehicleimage;

import android.os.Bundle;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

public class ParkingVehicleImageFragment extends ParkingBaseFragment implements ParkingVehicleImageContract.View {

    private ParkingVehicleImageContract.Presenter mPresenter;

    public ParkingVehicleImageFragment() {
        // Required empty public constructor
    }

    public static ParkingVehicleImageFragment newInstance(Bundle bundle) {
        ParkingVehicleImageFragment fragment = new ParkingVehicleImageFragment();
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.stop();
        }
    }

    @Override
    public void setPresenter(ParkingVehicleImageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_vehicle_image;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
