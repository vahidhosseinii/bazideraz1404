package com.shohda.bazideraz;

import android.app.Activity;
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

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class activity_delneveshteh extends Activity {
    EditText name,sen,city,delneveshteh;
    Button send;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState  ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delnevshteh);
        send =findViewById(R.id.button);
        name =findViewById(R.id.name);
        sen =findViewById(R.id.sen);
        city =findViewById(R.id.city);
        delneveshteh =findViewById(R.id.textdel);

        send.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String getdelneveshteh =  delneveshteh.getText().toString();
                String getcity =  city.getText().toString();
                String getn =  name.getText().toString();
                String getsen =  sen.getText().toString();
               senddel(getn,getsen,getcity,getdelneveshteh);
            }
        });

    }
    public void senddel(String name,String sen,String city,String delneveshteh) {
        Log.e("name",name);
        Log.e("sen",sen);
        Log.e("city",city);
        Log.e("delneveshteh",delneveshteh);
        Constant.Url="http://bazideraz.offroadteam.ir/bazideraz/public/Api/delneveshteh";
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("sen", sen);
        params.put("city", city);
        params.put("text", delneveshteh);
        params.put("user_id", "1");


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String error = jsonObject.getString(Constant.ERROR);
                    System.out.println("=====Data response " + response);
                    if (error.equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");
                        setSnackBar(message, getString(R.string.ok));


                    } else {


                    }
                } catch (JSONException e) {
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
}
