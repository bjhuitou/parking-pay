package net.itgoo.parkingpay.ui.payconfirm;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingPayConfirmPresenter implements ParkingPayConfirmContract.Presenter {

    private final ParkingPayConfirmContract.View mView;
    private final ParkingPayConfirmContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingPayConfirmPresenter(Map<String, String> params, ParkingPayConfirmContract.View view,
                                      ParkingPayConfirmContract.DataSource repository) {
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
