package net.itgoo.parkingpay.ui.rfid;

import android.os.Bundle;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

public class ParkingRfidFragment extends ParkingBaseFragment {

    public static ParkingRfidFragment newInstance() {
        return new ParkingRfidFragment();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_rfid;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initChildFragment();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initChildFragment() {
        ParkingRfidChildFragment fragment =
                (ParkingRfidChildFragment) getChildFragmentManager().findFragmentById(R.id.parking_fragment_rfid_content_fl);

        if (fragment == null) {
            fragment = ParkingRfidChildFragment.newInstance();
            ParkingMVPUtils.addFragmentToActivity(getChildFragmentManager(),
                    fragment, R.id.parking_fragment_rfid_content_fl);
        }

        new ParkingRfidPresenter(fragment, new ParkingRfidDataRepository());
    }
}
