package com.shohda.bazideraz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class verify extends Activity {
    EditText activecode;
    String getcode;
    ProgressDialog mProgressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState  ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        String mobile = getIntent().getStringExtra("mobile");
        activecode =  findViewById(R.id.activecode);

        Button active_click = findViewById(R.id.button);
        active_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getcode = activecode.getText().toString();
                Log.e("getcode",getcode);
                if(getcode.isEmpty()){
                    setSnackBar("کدفعالیسازی نمی تواند خالی باشد", getString(R.string.ok));
                }
                else if(getcode.length() < 6){
                    setSnackBar("کدفعالسازی صحیح نیست", getString(R.string.ok));
                }
                else{
                    verifyCode(getcode, mobile);
                }

            }
        });

    }
    private void verifyCode(String code ,String phoneNumber) {
        Constant.Url ="https://bazideraz.offroadteam.ir/bazideraz/public/Api/verify";
        showProgressDialog();
        Log.e("CODEEEE",code);
        Log.e("phoneNumber",phoneNumber);
        Map<String, String> params = new HashMap<>();
        params.put("phone", phoneNumber);
        params.put("verificationCode", code);

        ApiConfig.RequestToVolley((result, response) -> {
            Log.e("Respone0",response.toString());

            if (result) {
                try {
                    JSONObject obj = new JSONObject(response);
                    System.out.println("urlResponse:=" + response);
                    if (obj.getString("error").equals("false")) {
                        Log.e("RESPONSEEEE",obj.getString("message"));

                        JSONObject jsonobj = obj.getJSONObject("data");
                        Log.e("Response1",jsonobj.toString());

                        Log.e("Response2active",jsonobj.toString());
                        String message = obj.getString("message");
                        //setSnackBar(message, getString(R.string.ok));
                        com.shohda.bazideraz.helper.Session.saveUserDetail(this,
                                jsonobj.getString(Constant.userId),
                                jsonobj.getString(Constant.name),
                                jsonobj.getString(Constant.mobile),
                                jsonobj.getString(Constant.PROFILE));

                        Intent i = new Intent(this, MainActivity.class);
                        i.putExtra("type", "default");
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        this.finish();

                        hideProgressDialog();

                    } else {

                        hideProgressDialog();
                        Toast.makeText(verify.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        //String message = obj.getString("message");
                        // setSnackBar(message, getString(R.string.ok));
                    }

                }catch (JSONException e) {
                    System.out.println("urlResponse:=" + e);
                    e.printStackTrace();
                }

            }
        }, params);



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
            mProgressDialog = new ProgressDialog(verify.this);
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
