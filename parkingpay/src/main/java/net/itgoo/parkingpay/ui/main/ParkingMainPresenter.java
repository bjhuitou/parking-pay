package net.itgoo.parkingpay.ui.main;

import android.location.Location;

import net.itgoo.parkingpay.manage.ParkingManage;

import java.util.Map;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingMainPresenter implements ParkingMainContract.Presenter {

    private final ParkingMainContract.View mView;
    private final ParkingMainContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingMainPresenter(Map<String, String> params, ParkingMainContract.View view,
                                ParkingMainContract.DataSource repository) {
        mView = view;
        mRepository = repository;
        mParams = params;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        mRepository.cancel();
    }

    @Override
    public void requestLocation() {
        SmartLocation.with(mView.getContext()).location()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        ParkingManage.getInstance(mView.getContext())
                                .setLocation(location.getLatitude(), location.getLongitude());
                    }
                });
    }
}
