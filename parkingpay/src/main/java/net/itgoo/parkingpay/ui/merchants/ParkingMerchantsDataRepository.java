package net.itgoo.parkingpay.ui.merchants;

import net.itgoo.parkingpay.vendor.mvp.ParkingMVPLoadDataCallback;

import java.util.ArrayList;
import java.util.List;

public class ParkingMerchantsDataRepository implements ParkingMerchantsContract.DataSource {

    private List<String> mData = new ArrayList<>();

    @Override
    public void refresh(ParkingMVPLoadDataCallback callback) {
        mData.add("11");
        callback.onDataLoaded(mData);
    }

    @Override
    public void loadMore(ParkingMVPLoadDataCallback callback) {

    }

    @Override
    public String value(int position) {
        return null;
    }

    @Override
    public void cancel() {

    }
}
