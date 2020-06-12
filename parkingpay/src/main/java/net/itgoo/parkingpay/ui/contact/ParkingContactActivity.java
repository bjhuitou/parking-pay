package net.itgoo.parkingpay.ui.contact;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPUtils;

public class ParkingContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_activity_contact);
        initUI();
    }

    private void initUI() {
        initChildFragment();
    }

    private void initChildFragment() {
        ParkingContactFragment fragment =
                (ParkingContactFragment) getSupportFragmentManager().findFragmentById(R.id.parking_activity_contact_content_fl);

        if (fragment == null) {
            fragment = ParkingContactFragment.newInstance(getIntent().getExtras());
            ParkingMVPUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.parking_activity_contact_content_fl);
        }

        new ParkingContactPresenter(null, fragment, new ParkingContactDataRepository());
    }
}
