package net.itgoo.parkingpay.ui.contact;

import android.os.Bundle;
import android.view.View;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

import hk.ids.gws.android.sclick.SClick;

public class ParkingContactFragment extends ParkingBaseFragment implements ParkingContactContract.View {

    private ParkingContactContract.Presenter mPresenter;
    private TitleBar mTitleBar;

    public ParkingContactFragment() {
        // Required empty public constructor
    }

    public static ParkingContactFragment newInstance(Bundle bundle) {
        ParkingContactFragment fragment = new ParkingContactFragment();
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
    public void setPresenter(ParkingContactContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_contact;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initViews();
        initTitleBar();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initViews() {
        View contentView = getContentView();
        mTitleBar = contentView.findViewById(R.id.parking_view_title_bar_ct);
    }

    private void initTitleBar() {
        mTitleBar.setTitle(getString(R.string.parking_contact_us));
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;
                getActivity().finish();
            }
        });
    }
}
