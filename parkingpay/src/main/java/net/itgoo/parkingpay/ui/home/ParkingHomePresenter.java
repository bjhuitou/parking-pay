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
    public void openPlateEdit() {
        mView.showPlateEdit(null);
    }

    @Override
    public void savePlate(String plate) {
        if (plate == null || plate.isEmpty()) {
            return;
        }

        mView.showMainActionView(true);
        mView.showToolView(true);
    }

    @Override
    public void deletePlateConfirm() {
        mView.showDeleteConfirm();
    }

    @Override
    public void deletePlate() {

    }

    @Override
    public void previousPlate() {
        mView.showPlate("");
        mView.showPayView();
    }

    @Override
    public void nextPlate() {
        mView.showPlate("");
        mView.showEditView();
    }
}
