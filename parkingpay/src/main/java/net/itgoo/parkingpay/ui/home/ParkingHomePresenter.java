package net.itgoo.parkingpay.ui.home;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingHomePresenter implements ParkingHomeContract.Presenter {

    private final ParkingHomeContract.View mView;
    private final ParkingHomeContract.DataSource mRepository;

    public ParkingHomePresenter(ParkingHomeContract.View view,
                                ParkingHomeContract.DataSource repository) {
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
    public void previousPlate() {
        mView.showPlate("");
    }

    @Override
    public void nextPlate() {
        mView.showPlate("");
    }

    @Override
    public void setPlate(String plate) {
        mView.showPlate(plate);
    }
}
