package net.itgoo.parkingpay.ui.home;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;


/**
 * Created by apple on 16/11/21.
 */
public interface ParkingHomeContract {

    interface View extends ParkingBaseView<Presenter> {
        void showPlate(String plate);
        void showMainActionView(boolean show);
    }

    interface Presenter extends ParkingBasePresenter {
        void previousPlate();
        void nextPlate();
        void setPlate(String plate);
    }

    interface DataSource {
        void getData(ParkingMVPLoadDataCallback callback);
        void cancel();
    }
}
