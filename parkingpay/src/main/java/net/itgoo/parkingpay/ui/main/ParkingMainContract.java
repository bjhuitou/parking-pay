package net.itgoo.parkingpay.ui.main;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;

/**
 * Created by apple on 16/11/21.
 */
public interface ParkingMainContract {

    interface View extends ParkingBaseView<Presenter> {
        void showActionView(boolean show);
    }

    interface Presenter extends ParkingBasePresenter {
        void requestLocation();
    }

    interface DataSource {
        void getData(ParkingMVPLoadDataCallback callback);
        void cancel();
    }
}
