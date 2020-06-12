package net.itgoo.parkingpay.ui.home;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;


/**
 * Created by apple on 16/11/21.
 */
public interface ParkingHomeContract {

    interface View extends ParkingBaseView<Presenter> {
        void showPlateEdit(String plate);
        void showDeleteConfirm();
        void showPlate(String plate);
        void showEditView();
        void showPayView();
        void showMainActionView(boolean show);
        void showToolView(boolean show);
    }

    interface Presenter extends ParkingBasePresenter {
        void openPlateEdit();
        void savePlate(String plate);
        void deletePlateConfirm();
        void deletePlate();
        void previousPlate();
        void nextPlate();
    }

    interface DataSource {
        void getData(ParkingMVPLoadDataCallback callback);
        void cancel();
    }
}
