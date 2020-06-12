package net.itgoo.parkingpay.ui.park;

import android.os.Bundle;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

public class ParkingParkFragment extends ParkingBaseFragment {

    public static ParkingParkFragment newInstance() {
        return new ParkingParkFragment();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_park;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initChildFragment();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initChildFragment() {
        ParkingParkChildFragment fragment =
                (ParkingParkChildFragment) getChildFragmentManager().findFragmentById(R.id.parking_fragment_park_content_fl);

        if (fragment == null) {
            fragment = ParkingParkChildFragment.newInstance();
            ParkingMVPUtils.addFragmentToActivity(getChildFragmentManager(),
                    fragment, R.id.parking_fragment_park_content_fl);
        }

        new ParkingParkPresenter(fragment, new ParkingParkDataRepository());
    }
}
