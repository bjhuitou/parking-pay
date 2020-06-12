package net.itgoo.parkingpay.ui.merchants;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingMerchantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_merchants);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingMerchantsFragment fragment =
                (ParkingMerchantsFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_merchants_content_fl);

        if (fragment == null) {
            fragment = ParkingMerchantsFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_merchants_content_fl);
        }

        new ParkingMerchantsPresenter(null, fragment, new ParkingMerchantsDataRepository());
    }
}
