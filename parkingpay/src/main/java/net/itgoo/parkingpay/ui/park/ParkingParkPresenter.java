package net.itgoo.parkingpay.ui.park;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingParkPresenter implements ParkingParkContract.Presenter {

    private final ParkingParkContract.View mView;
    private final ParkingParkContract.DataSource mRepository;

    public ParkingParkPresenter(ParkingParkContract.View view,
                                ParkingParkContract.DataSource repository) {
        mView = view;
        mRepository = repository;
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
    public void openMerchants() {
        mView.showMerchants();
    }
}
