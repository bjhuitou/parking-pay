package net.itgoo.parkingpay.ui.home;

import android.os.Bundle;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

public class ParkingHomeFragment extends ParkingBaseFragment {

    public static ParkingHomeFragment newInstance() {
        return new ParkingHomeFragment();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_home;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initChildFragment();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initChildFragment() {
        ParkingHomeChildFragment fragment =
                (ParkingHomeChildFragment) getChildFragmentManager().findFragmentById(R.id.parking_fragment_home_content_fl);

        if (fragment == null) {
            fragment = ParkingHomeChildFragment.newInstance();
            ParkingMVPUtils.addFragmentToActivity(getChildFragmentManager(),
                    fragment, R.id.parking_fragment_home_content_fl);
        }

        new ParkingHomePresenter(fragment, new ParkingHomeDataRepository());
    }
}
