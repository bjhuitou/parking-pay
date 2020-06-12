package net.itgoo.parkingpay.vendor.mvp;

public interface ParkingMVPLoadDataCallback<T> {
    void onDataLoaded(T data);
    void onDataError(String error);
}
