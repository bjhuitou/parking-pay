package net.itgoo.parkingpay.ui.vehicleimage;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingVehicleImagePresenter implements ParkingVehicleImageContract.Presenter {

    private final ParkingVehicleImageContract.View mView;
    private final ParkingVehicleImageContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingVehicleImagePresenter(Map<String, String> params, ParkingVehicleImageContract.View view,
                                        ParkingVehicleImageContract.DataSource repository) {
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
}
