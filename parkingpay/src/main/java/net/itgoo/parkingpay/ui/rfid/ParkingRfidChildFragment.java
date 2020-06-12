package net.itgoo.parkingpay.ui.rfid;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

public class ParkingRfidChildFragment extends ParkingBaseFragment implements ParkingRfidContract.View {

    private ParkingRfidContract.Presenter mPresenter;
    private TextView mNameTextView;
    private TextView mRfidNumberTextView;
    private View mRegisterView;
    private View mEditView;
    private View mSuccessView;
    private EditText mRfidNumberEditText;
    private EditText mMobileEditText;
    private EditText mOtpEditText;

    public ParkingRfidChildFragment() {
        // Required empty public constructor
    }

    public static ParkingRfidChildFragment newInstance() {
        return new ParkingRfidChildFragment();
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
    public void setPresenter(ParkingRfidContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_rfid_child;
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
        mRegisterView = contentView.findViewById(R.id.parking_fragment_rfid_child_no_register_view);
        mEditView = contentView.findViewById(R.id.parking_fragment_rfid_child_edit_view);
        mSuccessView = contentView.findViewById(R.id.parking_fragment_rfid_child_success_view);
        mNameTextView = contentView.findViewById(R.id.parking_fragment_rfid_child_name_tv);
        mRfidNumberTextView = contentView.findViewById(R.id.parking_fragment_rfid_child_rfid_number_tv);
        mRfidNumberEditText = contentView.findViewById(R.id.parking_fragment_rfid_child_tag_number_et);
        mMobileEditText = contentView.findViewById(R.id.parking_fragment_rfid_child_mobile_number_et);
        mOtpEditText = contentView.findViewById(R.id.parking_fragment_rfid_child_otp_et);

        contentView.findViewById(R.id.parking_fragment_rfid_child_enter_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        contentView.findViewById(R.id.parking_fragment_rfid_child_submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ((Switch) contentView.findViewById(R.id.parking_fragment_rfid_child_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }
}
