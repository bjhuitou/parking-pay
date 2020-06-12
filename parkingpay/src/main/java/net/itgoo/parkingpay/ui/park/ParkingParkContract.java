package net.itgoo.parkingpay.ui.park;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;


/**
 * Created by apple on 16/11/21.
 */
public interface ParkingParkContract {

    interface View extends ParkingBaseView<Presenter> {
        void showMerchants();
    }

    interface Presenter extends ParkingBasePresenter {
        void openMerchants();
    }

    interface DataSource {
        void getData(ParkingMVPLoadDataCallback callback);
        void cancel();
    }
}
