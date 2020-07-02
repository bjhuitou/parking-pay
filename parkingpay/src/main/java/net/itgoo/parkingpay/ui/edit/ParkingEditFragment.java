package net.itgoo.parkingpay.ui.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

public class ParkingEditFragment extends ParkingBaseFragment {

    public static ParkingEditFragment newInstance() {
        return new ParkingEditFragment();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_edit;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initChildFragment();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initChildFragment() {
        ParkingEditChildFragment fragment =
                (ParkingEditChildFragment) getChildFragmentManager().findFragmentById(R.id.parking_fragment_edit_content_fl);

        if (fragment == null) {
            fragment = ParkingEditChildFragment.newInstance();
            ParkingMVPUtils.addFragmentToActivity(getChildFragmentManager(),
                    fragment, R.id.parking_fragment_edit_content_fl);
        }

        new ParkingEditPresenter(fragment, new ParkingEditDataRepository());
    }
}
