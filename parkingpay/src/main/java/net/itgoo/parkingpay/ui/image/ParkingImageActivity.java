package net.itgoo.parkingpay.ui.image;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_image);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingImageFragment fragment =
                (ParkingImageFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_image_content_fl);

        if (fragment == null) {
            fragment = ParkingImageFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_image_content_fl);
        }

        new ParkingImagePresenter(null, fragment, new ParkingImageDataRepository());
    }
}
