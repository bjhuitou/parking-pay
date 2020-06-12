package net.itgoo.parkingpay.ui.orderinfo;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingOrderInfoPresenter implements ParkingOrderInfoContract.Presenter {

    private final ParkingOrderInfoContract.View mView;
    private final ParkingOrderInfoContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingOrderInfoPresenter(Map<String, String> params, ParkingOrderInfoContract.View view,
                                     ParkingOrderInfoContract.DataSource repository) {
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
