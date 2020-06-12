package net.itgoo.parkingpay.ui.payconfirm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingPayConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_pay_confirm);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingPayConfirmFragment fragment =
                (ParkingPayConfirmFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_pay_confirm_content_fl);

        if (fragment == null) {
            fragment = ParkingPayConfirmFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_pay_confirm_content_fl);
        }

        new ParkingPayConfirmPresenter(null, fragment, new ParkingPayConfirmDataRepository());
    }
}
