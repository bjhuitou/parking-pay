package net.itgoo.parkingpay.ui.rfid;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingRfidPresenter implements ParkingRfidContract.Presenter {

    private final ParkingRfidContract.View mView;
    private final ParkingRfidContract.DataSource mRepository;

    public ParkingRfidPresenter(ParkingRfidContract.View view,
                                ParkingRfidContract.DataSource repository) {
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
}
