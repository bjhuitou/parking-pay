package net.itgoo.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import net.itgoo.parkingpay.ui.main.ParkingMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void parkingPayAction(View view) {
        startActivity(new Intent(this, ParkingMainActivity.class));
    }
}
