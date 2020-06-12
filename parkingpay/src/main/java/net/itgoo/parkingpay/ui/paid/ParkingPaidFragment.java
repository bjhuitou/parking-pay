package net.itgoo.parkingpay.ui.paid;

import android.os.Bundle;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

public class ParkingPaidFragment extends ParkingBaseFragment implements ParkingPaidContract.View {

    private ParkingPaidContract.Presenter mPresenter;
    private TitleBar mTitleBar;

    public ParkingPaidFragment() {
        // Required empty public constructor
    }

    public static ParkingPaidFragment newInstance(Bundle bundle) {
        ParkingPaidFragment fragment = new ParkingPaidFragment();
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
    public void setPresenter(ParkingPaidContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_paid;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initTitleBar();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initTitleBar() {
        mTitleBar.setTitle("");
    }
}
