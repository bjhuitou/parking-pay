package net.itgoo.parkingpay.manage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by apple on 16/12/8.
 */
public class ParkingManage {

    private static final String PREF_NAME = "parking_pref";
    private static final String LOCATION_LAT_KEY = "parking_location_lat";
    private static final String LOCATION_LON_KEY = "parking_location_lon";
    private static ParkingManage sInstance;
    private Context mContext;

    public static synchronized ParkingManage getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ParkingManage(context);
        }

        return sInstance;
    }

    private ParkingManage(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void setLocation(double lat, double lon) {
        SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(LOCATION_LAT_KEY, String.valueOf(lat))
                .putString(LOCATION_LON_KEY, String.valueOf(lon))
                .apply();
    }

    public Map<String, Double> getLocation() {
        Map<String, Double> map = new ArrayMap<>();
        SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        map.put("lat", Double.valueOf(sp.getString(LOCATION_LAT_KEY, "0")));
        map.put("lon", Double.valueOf(sp.getString(LOCATION_LON_KEY, "0")));

        return map;
    }
}
