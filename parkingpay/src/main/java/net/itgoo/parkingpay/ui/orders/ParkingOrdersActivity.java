package net.itgoo.parkingpay.ui.orders;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_orders);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingOrdersFragment fragment =
                (ParkingOrdersFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_orders_content_fl);

        if (fragment == null) {
            fragment = ParkingOrdersFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_orders_content_fl);
        }

        new ParkingOrdersPresenter(null, fragment, new ParkingOrdersDataRepository());
    }
}
