package net.itgoo.parkingpay.ui.paid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingPaidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_paid);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingPaidFragment fragment =
                (ParkingPaidFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_paid_content_fl);

        if (fragment == null) {
            fragment = ParkingPaidFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_paid_content_fl);
        }

        new ParkingPaidPresenter(null, fragment, new ParkingPaidDataRepository());
    }
}
