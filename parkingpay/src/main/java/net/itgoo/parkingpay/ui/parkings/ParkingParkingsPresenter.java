package net.itgoo.parkingpay.ui.parkings;

import android.location.Location;

import net.itgoo.parkingpay.manage.ParkingManage;

import java.util.Map;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingParkingsPresenter implements ParkingParkingsContract.Presenter {

    private final ParkingParkingsContract.View mView;
    private final ParkingParkingsContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingParkingsPresenter(Map<String, String> params, ParkingParkingsContract.View view,
                                    ParkingParkingsContract.DataSource repository) {
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
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        ParkingManage.getInstance(mView.getContext())
                                .setLocation(location.getLatitude(), location.getLongitude());
                    }
                });
    }

    @Override
    public void openPark(int position) {

    }

    @Override
    public void openCouncils(int position) {

    }
}
