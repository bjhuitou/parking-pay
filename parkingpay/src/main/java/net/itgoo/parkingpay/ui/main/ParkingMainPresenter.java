package net.itgoo.parkingpay.ui.main;

import android.location.Location;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.itgoo.parkingpay.manage.ParkingManage;
import net.itgoo.parkingpay.rest.VolleyManager;

import java.util.HashMap;
import java.util.Map;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by apple on 16/11/21.
 */
public class ParkingMainPresenter implements ParkingMainContract.Presenter {

    private final ParkingMainContract.View mView;
    private final ParkingMainContract.DataSource mRepository;
    private Map<String, String> mParams;

    public ParkingMainPresenter(Map<String, String> params, ParkingMainContract.View view,
                                ParkingMainContract.DataSource repository) {
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
    public void requestLocation() {
        SmartLocation.with(mView.getContext()).location()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        ParkingManage.getInstance(mView.getContext())
                                .setLocation(location.getLatitude(), location.getLongitude());
                    }
                });

        HashMap<String, String> params = new HashMap<>();
        params.put("params1", "xixi");
        VolleyManager.sendStringRequest(Request.Method.GET,
                "https://www.cywzpt.com/api/problem/getKeFuMobile", params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
    }
}
