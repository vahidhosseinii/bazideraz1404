package com.shohda.bazideraz;


import android.annotation.SuppressLint;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.android.volley.toolbox.ImageLoader;
import com.shohda.bazideraz.helper.ArcNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.shohda.bazideraz.helper.Utils;
import java.util.Locale;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static ArcNavigationView navigationView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;
    protected FrameLayout frameLayout;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    //public static CircleImageView imgProfile;
    public TextView tvEmail;
    public static TextView tvName;
    static final float END_SCALE = 0.7f;
    private static Locale myLocale;
    public static Configuration config;
    androidx.appcompat.app.AlertDialog alertDialogs;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.transparentStatusAndNavigation(DrawerActivity.this);
        setContentView(R.layout.activity_drawer);
        frameLayout = findViewById(R.id.content_frame);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);

       // Utils.loadAd(DrawerActivity.this);
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                frameLayout.setScaleX(offsetScale);
                frameLayout.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = frameLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                frameLayout.setTranslationX(xTranslation);
            }
        });

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
//
//         //   case R.id.navhome:
//                drawerLayout.closeDrawers();
//                break;
//            case R.id.nav_help:
//                drawerLayout.closeDrawers();
//                break;
//            case R.id.logout:
//             //   Utils.SignOutWarningDialog(DrawerActivity.this);
//                drawerLayout.closeDrawers();
//                break;
//            default:
        }
        return false;
    }



}
