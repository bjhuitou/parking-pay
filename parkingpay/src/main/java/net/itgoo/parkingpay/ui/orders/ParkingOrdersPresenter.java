package net.itgoo.parkingpay.ui.orders;

import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingOrdersPresenter implements ParkingOrdersContract.Presenter {

    private final ParkingOrdersContract.View mView;
    private final ParkingOrdersContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingOrdersPresenter(Map<String, String> params, ParkingOrdersContract.View view,
                                  ParkingOrdersContract.DataSource repository) {
        mView = view;
        mRepository = repository;
        mParams = params;
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
    public void refresh() {
        mRepository.refresh(new ParkingMVPLoadDataCallback<List<String>>() {
            @Override
            public void onDataLoaded(List<String> data) {
                if (!mView.isActive()) {
                    return;
                }

                if (data.isEmpty()) {
                    mView.showEmptyData();
                } else {
                    mView.showRefreshData(data);
                }
            }

            @Override
            public void onDataError(String error) {
                if (!mView.isActive()) {
                    return;
                }

                mView.showRefreshFailure(error);
            }
        });
    }

    @Override
    public void loadMore() {
        mRepository.loadMore(new ParkingMVPLoadDataCallback<List<String>>() {
            @Override
            public void onDataLoaded(List<String> data) {
                if (!mView.isActive()) {
                    return;
                }

                mView.showMoreData(data);
            }

            @Override
            public void onDataError(String error) {
                if (!mView.isActive()) {
                    return;
                }

                mView.showMoreFailure(error);
            }
        });
    }

    @Override
    public void openItem(int position) {
        mView.showItem(0);
    }
}
