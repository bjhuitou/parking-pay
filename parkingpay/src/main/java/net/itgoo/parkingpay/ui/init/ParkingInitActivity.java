package net.itgoo.parkingpay.ui.init;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingInitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_init);
        initUI();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingInitFragment fragment =
                (ParkingInitFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_init_content_fl);

        if (fragment == null) {
            fragment = ParkingInitFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_init_content_fl);
        }

        new ParkingInitPresenter(null, fragment, new ParkingInitDataRepository());
    }
}
