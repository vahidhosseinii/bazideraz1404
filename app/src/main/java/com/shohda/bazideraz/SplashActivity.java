package com.shohda.bazideraz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.shohda.bazideraz.helper.Session;

import java.util.Locale;


public class SplashActivity extends Activity {

    Handler handler;
    private static Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(() -> {
            if (Session.isLogin(SplashActivity.this)) {
                Intent intent = new Intent(SplashActivity.this, mainslider.class);
                intent.putExtra("type", "default");
                startActivity(intent);
                finish();

            }else {
                Intent intent = new Intent(SplashActivity.this, login_activity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }


}