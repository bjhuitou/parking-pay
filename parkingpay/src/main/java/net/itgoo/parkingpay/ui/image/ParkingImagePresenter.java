package net.itgoo.parkingpay.ui.image;

import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingImagePresenter implements ParkingImageContract.Presenter {

    private final ParkingImageContract.View mView;
    private final ParkingImageContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingImagePresenter(Map<String, String> params, ParkingImageContract.View view,
                                 ParkingImageContract.DataSource repository) {
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
