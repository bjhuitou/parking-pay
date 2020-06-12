package net.itgoo.parkingpay.ui.parkings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingParkingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_parkings);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingParkingsFragment fragment =
                (ParkingParkingsFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_parkings_content_fl);

        if (fragment == null) {
            fragment = ParkingParkingsFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_parkings_content_fl);
        }

        new ParkingParkingsPresenter(null, fragment, new ParkingParkingsDataRepository());
    }
}
