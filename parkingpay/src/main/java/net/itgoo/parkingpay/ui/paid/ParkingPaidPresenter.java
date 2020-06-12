package net.itgoo.parkingpay.ui.paid;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingPaidPresenter implements ParkingPaidContract.Presenter {

    private final ParkingPaidContract.View mView;
    private final ParkingPaidContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingPaidPresenter(Map<String, String> params, ParkingPaidContract.View view,
                                ParkingPaidContract.DataSource repository) {
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
