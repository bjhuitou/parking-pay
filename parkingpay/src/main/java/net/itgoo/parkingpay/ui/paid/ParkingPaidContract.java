package net.itgoo.parkingpay.ui.paid;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;

/**
 * Created by apple on 16/11/21.
 */
public interface ParkingPaidContract {

    interface View extends ParkingBaseView<Presenter> {

    }

    interface Presenter extends ParkingBasePresenter {

    }

    interface DataSource {
        void getData(ParkingMVPLoadDataCallback callback);
        void cancel();
    }
}
