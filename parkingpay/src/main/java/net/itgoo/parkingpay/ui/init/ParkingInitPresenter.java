package net.itgoo.parkingpay.ui.init;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingInitPresenter implements ParkingInitContract.Presenter {

    private final ParkingInitContract.View mView;
    private final ParkingInitContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingInitPresenter(Map<String, String> params, ParkingInitContract.View view,
                                ParkingInitContract.DataSource repository) {
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
