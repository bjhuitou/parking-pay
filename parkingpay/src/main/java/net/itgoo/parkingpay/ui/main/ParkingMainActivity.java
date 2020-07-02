package net.itgoo.parkingpay.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;
import net.itgoo.parkingpay.vendor.widget.fragmentBackHandler.BackHandlerHelper;

public class ParkingMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_main);
        initUI();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void initUI() {
        showMainFragment();
    }

    private void showMainFragment() {
        ParkingMainFragment fragment =
                (ParkingMainFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.parking_activity_main_content_fl);

        if (fragment == null) {
            fragment = ParkingMainFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_main_content_fl);
        }

        new ParkingMainPresenter(null, fragment, new ParkingMainDataRepository());
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(getSupportFragmentManager())) {
            super.onBackPressed();
        }
    }
}
