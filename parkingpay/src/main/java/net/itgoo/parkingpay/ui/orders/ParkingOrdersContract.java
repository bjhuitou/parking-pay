package net.itgoo.parkingpay.ui.orders;

import net.itgoo.parkingpay.vendor.mvp.ParkingBasePresenter;
import net.itgoo.parkingpay.vendor.mvp.ParkingBaseView;
import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;

import java.util.List;

/**
 * Created by apple on 16/11/21.
 */
public interface ParkingOrdersContract {

    interface View extends ParkingBaseView<Presenter> {
        void showRefreshData(List<String> data);
        void showRefreshFailure(String error);
        void showMoreData(List<String> data);
        void showMoreFailure(String error);
        void showEmptyData();
        void showItem(long id);
    }

    interface Presenter extends ParkingBasePresenter {
        void refresh();
        void loadMore();
        void openItem(int position);
    }

    interface DataSource {
        void refresh(ParkingMVPLoadDataCallback callback);
        void loadMore(ParkingMVPLoadDataCallback callback);
        String value(int position);
        void cancel();
    }
}
