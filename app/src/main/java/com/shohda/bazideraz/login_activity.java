package com.shohda.bazideraz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import  com.shohda.bazideraz.Api.ApiInterface;

public class login_activity extends Activity {

    ProgressDialog mProgressDialog;
    EditText mobile;
    EditText name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login_click = findViewById(R.id.button);
        mobile =   findViewById(R.id.mobile);
        name =   findViewById(R.id.name);
        login_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.Url  ="https://bazideraz.offroadteam.ir/bazideraz/public/Api/Login";
                String number  =   mobile.getText().toString();
                String namef   =   name.getText().toString();
                if(number.isEmpty()){
                    setSnackBar("لطفا فیلد شماره همراه را وارد نمایید", getString(R.string.ok));
                }
                if(namef.isEmpty()){
                    setSnackBar("لطفا فیلد  نام و نام خانوادگی وارد کنید", getString(R.string.ok));
                }
                else if(number.length() < 11){
                    setSnackBar("شماره موبایل وارد شده معتبر نمی باشد", getString(R.string.ok));
                }
                else{
                    showProgressDialog();
                    if(validateMobile()) {
                        Map<String, String> params = new HashMap<>();
                        params.put(Constant.mobile, number);
                        params.put(Constant.name, namef);


                        ApiConfig.RequestToVolley((result,response ) -> {
                            Log.e("REsposeLogin",response.toString());
                            if (result) {

                                JSONObject obj = null;
                                try {
                                    obj = new JSONObject(response);
                                    boolean error = obj.getBoolean("error");
                                    if (!error) {
                                        hideProgressDialog();
                                        Log.e("response", response.toString());
                                        String message = obj.getString("message");
                                        setSnackBar(message, getString(R.string.ok));
                                        Intent i = new Intent(login_activity.this, verify.class);
                                        i.putExtra("mobile", number);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                        finish();
                                    }


                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }, params);

                        hideProgressDialog();
                    }
                    }
                }

        });
    }


    public boolean validateMobile() {
        boolean a = true;
        if (mobile.getText().toString().trim().isEmpty()) {
            a = false;
            setSnackBar("فیلد شماره همراه نمیتواند خالی باشد.", getString(R.string.ok));
        } else {
            String numberPattern = "[0-9]+";
            if (mobile.getText().toString().matches(numberPattern)) {
                if (mobile.getText().toString().length() == 11) {
                    if (mobile.getText().toString().substring(0, 2).equals("09")) {
                        a = true;
                    } else {
                        a = false;
                        setSnackBar("شماره موبایل وارد شده معتبر نمی باشد", getString(R.string.ok));
                    }

                } else if (mobile.getText().toString().length() == 10) {
                    if (mobile.getText().toString().substring(0, 1).equals("9")) {
                        a = true;
                    } else {
                        a = false;
                        setSnackBar("شماره موبایل وارد شده معتبر نمی باشد", getString(R.string.ok));
                    }
                } else {
                    a = false;
                    setSnackBar("شماره موبایل وارد شده معتبر نمی باشد", getString(R.string.ok));
                }

            } else {
                a = false;
                setSnackBar("شماره موبایل وارد شده معتبر نمی باشد", getString(R.string.ok));
            }
        }
        return a;
    }
    public void setSnackBar(String message, String action) {

        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            View snack = snackbar.getView();
            snack.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        snackbar.setAction(action, view -> {

            snackbar.show();
        });
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.iran_sans_web);
        textView.setTypeface(typeface);

        textView.setMaxLines(5);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(login_activity.this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
