package net.itgoo.parkingpay.ui.orderinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingOrderInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_order_info);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingOrderInfoFragment fragment =
                (ParkingOrderInfoFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_order_info_content_fl);

        if (fragment == null) {
            fragment = ParkingOrderInfoFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_order_info_content_fl);
        }

        new ParkingOrderInfoPresenter(null, fragment, new ParkingOrderInfoDataRepository());
    }
}
