package com.shohda.bazideraz;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class mainslider extends AppCompatActivity  {
    // creating object of ViewPager
    ViewPager mViewPager;
    public  static  TextView textView;

    // images array
    int[] images = {R.drawable.menu_image0, R.drawable.menu_image1, R.drawable.menu_image2, R.drawable.menu_image3,
            R.drawable.menu_image4, R.drawable.menu_image5, R.drawable.menu_image6};

    String[] text = new String[]{
            "زیارت مجازی مناطق عملیاتی",
            "راهنما و توشه سفر زائران",
            "گالری و چند رسانه ای",
            "دانشنامه دفاع مقدس",
            "نقشه و مسیریابی سفر",
            "دلنوشته با شهدا",
            "تفال به شهدا"
    };

    // Creating Object of ViewPagerAdapter
    com.shohda.bazideraz.ViewPagerAdapter mViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_slider);

         textView = findViewById(R.id.textView_title);

        // Initializing the ViewPager Object
        mViewPager = (ViewPager)findViewById(R.id.viewpager);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new com.shohda.bazideraz.ViewPagerAdapter(mainslider.this, images,text);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels == 0) {

                    textView.setText(text[position]);

                }
                textView.setText(text[position]);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    }
