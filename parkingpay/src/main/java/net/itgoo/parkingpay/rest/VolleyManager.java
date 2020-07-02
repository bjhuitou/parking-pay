package net.itgoo.parkingpay.rest;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyManager {

    private static RequestQueue requestQueue;

    private final static int TIME_OUT = 30000;

    public static void init(Context context) {
        if (requestQueue == null) {
            synchronized (VolleyManager.class) {
                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(context);
                }
            }
        }
    }

    private static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    private static <T> void addRequest(RequestQueue requestQueue, Request<T> request) {
        request.setShouldCache(true);
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    public static void sendJsonObjectRequest(int method, String url,
                                             JSONObject params,
                                             final Response.Listener<JSONObject> listener,
                                             final Response.ErrorListener errorListener) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method,
                    url, params, listener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("accept-encoding", "utf-8");
                    return headers;
                }
            };
            addRequest(getRequestQueue(), jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendStringRequest(int method, String url,
                                         final Map<String, String> params,
                                         final Response.Listener<String> listener,
                                         final Response.ErrorListener errorListener) {
        try {
            StringRequest stringRequest = new StringRequest(method, url, listener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
            addRequest(getRequestQueue(), stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
