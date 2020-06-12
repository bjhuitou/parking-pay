package net.itgoo.parkingpay.ui.park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.merchants.ParkingMerchantsActivity;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

import hk.ids.gws.android.sclick.SClick;

public class ParkingParkChildFragment extends ParkingBaseFragment implements ParkingParkContract.View {

    private ParkingParkContract.Presenter mPresenter;

    public ParkingParkChildFragment() {
        // Required empty public constructor
    }

    public static ParkingParkChildFragment newInstance() {
        return new ParkingParkChildFragment();
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
    public void setPresenter(ParkingParkContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_park_child;
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
        contentView.findViewById(R.id.parking_fragment_park_child_all_merchants_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAllMerchantsAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_park_child_shopping_mall_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShoppingMallAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_park_child_commercial_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCommercialAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_park_child_residential_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResidentialAction();
            }
        });
    }

    private void onAllMerchantsAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.openMerchants();
    }

    private void onShoppingMallAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.openMerchants();
    }

    private void onCommercialAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.openMerchants();
    }

    private void onResidentialAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.openMerchants();
    }

    @Override
    public void showMerchants() {
        Intent intent = new Intent(getActivity(), ParkingMerchantsActivity.class);
        startActivity(intent);
    }
}
