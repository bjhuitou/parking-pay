package net.itgoo.parkingpay.ui.parkings;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;

/**
 * Created by apple on 16/11/21.
 */
public interface ParkingParkingsContract {

    interface View extends ParkingBaseView<Presenter> {

    }

    interface Presenter extends ParkingBasePresenter {
        void requestLocation();
        void openPark(int position);
        void openCouncils(int position);
    }

    interface DataSource {
        void getData(ParkingMVPLoadDataCallback callback);
        void cancel();
    }
}
