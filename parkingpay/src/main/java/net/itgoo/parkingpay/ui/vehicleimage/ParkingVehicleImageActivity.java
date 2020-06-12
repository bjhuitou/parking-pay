package net.itgoo.parkingpay.ui.vehicleimage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingVehicleImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_vehicle_image);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingVehicleImageFragment fragment =
                (ParkingVehicleImageFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_vehicle_image_content_fl);

        if (fragment == null) {
            fragment = ParkingVehicleImageFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_vehicle_image_content_fl);
        }

        new ParkingVehicleImagePresenter(null, fragment, new ParkingVehicleImageDataRepository());
    }
}
