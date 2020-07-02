package net.itgoo.parkingpay.ui.edit;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;


/**
 * Created by apple on 16/11/21.
 */
public interface ParkingEditContract {

    interface View extends ParkingBaseView<Presenter> {
        void showPlateEdit(String plate);
        void showDeleteConfirm();
        void showPlate(String plate);
        void showPlateInfo(String plate);
    }

    interface Presenter extends ParkingBasePresenter {
        void openPlateEdit();
        void savePlate(String plate);
        void deletePlateConfirm();
        void deletePlate();
        void previousPlate();
        void nextPlate();
        void openPlate();
    }

    interface DataSource {
        void getData(ParkingMVPLoadDataCallback callback);
        void cancel();
    }
}
