package net.itgoo.parkingpay.ui.contact;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingContactPresenter implements ParkingContactContract.Presenter {

    private final ParkingContactContract.View mView;
    private final ParkingContactContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingContactPresenter(Map<String, String> params, ParkingContactContract.View view,
                                   ParkingContactContract.DataSource repository) {
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
