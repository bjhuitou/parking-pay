package net.itgoo.parkingpay.ui.edit;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingEditPresenter implements ParkingEditContract.Presenter {

    private final ParkingEditContract.View mView;
    private final ParkingEditContract.DataSource mRepository;

    public ParkingEditPresenter(ParkingEditContract.View view,
                                ParkingEditContract.DataSource repository) {
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

    }

    @Override
    public void savePlate(String plate) {
        if (plate == null || plate.isEmpty()) {
            return;
        }
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

    }

    @Override
    public void nextPlate() {

    }

    @Override
    public void openPlate() {
        mView.showPlateInfo("111");
    }
}
