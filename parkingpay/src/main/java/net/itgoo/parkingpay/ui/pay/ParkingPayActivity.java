package net.itgoo.parkingpay.ui.pay;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingPayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_pay);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingPayFragment fragment =
                (ParkingPayFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_pay_content_fl);

        if (fragment == null) {
            fragment = ParkingPayFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_pay_content_fl);
        }

        new ParkingPayPresenter(null, fragment, new ParkingPayDataRepository());
    }
}
