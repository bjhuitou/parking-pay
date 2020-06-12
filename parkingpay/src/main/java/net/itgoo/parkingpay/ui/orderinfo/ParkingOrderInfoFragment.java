package net.itgoo.parkingpay.ui.orderinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

import hk.ids.gws.android.sclick.SClick;

public class ParkingOrderInfoFragment extends ParkingBaseFragment implements ParkingOrderInfoContract.View {

    private ParkingOrderInfoContract.Presenter mPresenter;
    private TitleBar mTitleBar;
    private TextView mAmountTextView;
    private TextView mPlateTextView;
    private TextView mPaidToTextView;
    private TextView mInTimeTextView;
    private TextView mDateTimeTextView;
    private TextView mLocationTextView;
    private TextView mTIdTextView;

    public ParkingOrderInfoFragment() {
        // Required empty public constructor
    }

    public static ParkingOrderInfoFragment newInstance(Bundle bundle) {
        ParkingOrderInfoFragment fragment = new ParkingOrderInfoFragment();
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
    public void setPresenter(ParkingOrderInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_order_info;
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
        mAmountTextView = contentView.findViewById(R.id.parking_fragment_order_info_plate_tv);
        mPlateTextView = contentView.findViewById(R.id.parking_fragment_order_info_plate_tv);
        mPaidToTextView = contentView.findViewById(R.id.parking_fragment_order_info_paid_to_tv);
        mInTimeTextView = contentView.findViewById(R.id.parking_fragment_order_info_in_time_tv);
        mDateTimeTextView = contentView.findViewById(R.id.parking_fragment_order_info_date_time_tv);
        mLocationTextView = contentView.findViewById(R.id.parking_fragment_order_info_location_tv);
        mTIdTextView = contentView.findViewById(R.id.parking_fragment_order_info_t_id_tv);
    }

    private void initTitleBar() {
        mTitleBar.setTitle(getString(R.string.parking_parking_history));
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;
                getActivity().finish();
            }
        });
    }
}
