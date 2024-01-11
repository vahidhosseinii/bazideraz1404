package com.shohda.bazideraz;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ApiConfig {

    public static String VolleyErrorMessage(VolleyError error) {
        String message = "";
        try {
            if (error instanceof NetworkError) {
                message = "Cannot connect to Internet...Please check your connection!";
            } else if (error instanceof ServerError) {
                message = "The server could not be found. Please try again after some time!!";
            } else if (error instanceof AuthFailureError) {
                message = "Cannot connect to Internet...Please check your connection!";
            } else if (error instanceof ParseError) {
                message = "Parsing error! Please try again after some time!!";
            } else if (error instanceof TimeoutError) {
                message = "Connection TimeOut! Please check your internet connection.";
            } else
                message = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }



    public static void MultipartRequestToVolley(final VolleyCallback callback, final Map<String, String> params, final Map<String, String> fileParams,final String url) {
        com.shohda.bazideraz.helper.VolleyMultiPartRequest multipartRequest = new com.shohda.bazideraz.helper.VolleyMultiPartRequest(url, response -> callback.onSuccess(true, response),
                error -> callback.onSuccess(false, "")) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params1 = new HashMap<String, String>();
                params1.put(Constant.AUTHORIZATION, "Bearer " + AppController.createJWT("bazideraz", "bazideraz Authentication"));
                return params1;
            }
            @Override
            public Map<String, String> getDefaultParams() {
                params.put(Constant.accessKey, Constant.accessKeyValue);
                return params;
            }
            @Override
            public Map<String, String> getFileParams() {
                return fileParams;
            }
        };
        AppController.getInstance().getRequestQueue().getCache().clear();
        AppController.getInstance().addToRequestQueue(multipartRequest);
    }

    public static void RequestToVolley(final VolleyCallback callback, final Map<String, String> params) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Url, response -> callback.onSuccess(true, response),
                error -> callback.onSuccess(false, "")) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> params1 = new HashMap<String, String>();
//                params1.put(Constant.AUTHORIZATION, "Bearer " + AppController.createJWT("quiz", "quiz Authentication"));
//                Log.e("Auth",AppController.createJWT("quiz", "quiz Authentication"));
//                return params1;
////            }

            @Override
            protected Map<String, String> getParams() {
                params.put(Constant.accessKey, Constant.accessKeyValue);
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().getRequestQueue().getCache().clear();

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(boolean result, String message);
        //void onSuccessWithMsg(boolean result, String message);

    }


    public static boolean CheckValidation(String item, boolean emailValidation, boolean mobileValidation) {
        if (item.length() == 0)
            return true;
        else if (emailValidation && (!android.util.Patterns.EMAIL_ADDRESS.matcher(item).matches()))
            return true;
        else return mobileValidation && (item.length() < 9 || item.length() > 11);
    }

}
