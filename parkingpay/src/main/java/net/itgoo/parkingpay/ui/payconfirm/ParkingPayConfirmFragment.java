package net.itgoo.parkingpay.ui.payconfirm;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

import hk.ids.gws.android.sclick.SClick;

public class ParkingPayConfirmFragment extends ParkingBaseFragment implements ParkingPayConfirmContract.View {

    private ParkingPayConfirmContract.Presenter mPresenter;
    private TitleBar mTitleBar;
    private TextView mAmountTextView;
    private TextView mPlateTextView;
    private TextView mPaidToTextView;
    private TextView mInTimeTextView;
    private TextView mDateTimeTextView;
    private TextView mLocationTextView;
    private TextView mTIdTextView;

    public ParkingPayConfirmFragment() {
        // Required empty public constructor
    }

    public static ParkingPayConfirmFragment newInstance(Bundle bundle) {
        ParkingPayConfirmFragment fragment = new ParkingPayConfirmFragment();
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
    public void setPresenter(ParkingPayConfirmContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_pay_confirm;
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
        mAmountTextView = contentView.findViewById(R.id.parking_fragment_pay_confirm_plate_tv);
        mPlateTextView = contentView.findViewById(R.id.parking_fragment_pay_confirm_plate_tv);
        mPaidToTextView = contentView.findViewById(R.id.parking_fragment_pay_confirm_paid_to_tv);
        mInTimeTextView = contentView.findViewById(R.id.parking_fragment_pay_confirm_in_time_tv);
        mDateTimeTextView = contentView.findViewById(R.id.parking_fragment_pay_confirm_date_time_tv);
        mLocationTextView = contentView.findViewById(R.id.parking_fragment_pay_confirm_location_tv);
        mTIdTextView = contentView.findViewById(R.id.parking_fragment_pay_confirm_t_id_tv);
        contentView.findViewById(R.id.parking_fragment_pay_confirm_no_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                onPayNoAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_pay_confirm_yes_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                onPayYesAction();
            }
        });
    }

    private void initTitleBar() {
        mTitleBar.setTitle(getString(R.string.parking_confirm_payment));
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;
                getActivity().finish();
            }
        });
    }

    private void onPayYesAction() {

    }

    private void onPayNoAction() {

    }
}
