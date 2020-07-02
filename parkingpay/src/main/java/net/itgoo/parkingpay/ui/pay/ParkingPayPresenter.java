package net.itgoo.parkingpay.ui.pay;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingPayPresenter implements ParkingPayContract.Presenter {

    private final ParkingPayContract.View mView;
    private final ParkingPayContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingPayPresenter(Map<String, String> params, ParkingPayContract.View view,
                               ParkingPayContract.DataSource repository) {
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
