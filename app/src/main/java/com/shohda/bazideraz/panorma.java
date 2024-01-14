package com.shohda.bazideraz;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.panoramagl.PLCamera;
import com.panoramagl.PLImage;
import com.panoramagl.PLSpherical2Panorama;
import com.panoramagl.PLView;
import com.panoramagl.utils.PLUtils;


public class panorma extends PLView  {
    private static final int REQUEST_CODE = 123;
    PLSpherical2Panorama panorama;
    SensorManager sensorManager;
    Sensor gyroscopeSensor;
    static {
        System.loadLibrary("glues");
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.panorma);
        //Load panorama
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        panorama = new PLSpherical2Panorama();
        panorama.getCamera().lookAt(30.0f, 90.0f);

        panorama.setImage(new PLImage(PLUtils.getBitmap(this, R.drawable.pl1_0), false));
        panorama.setImage(new PLImage(PLUtils.getBitmap(this, R.drawable.plt1_0), false));

        this.setPanorama(panorama);
    }
    SensorEventListener gyroscopeListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Handle gyroscope data changes here
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            // Do something with the values

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Handle accuracy changes if needed
        }
    };

    @Override
    protected View onContentViewCreated(View contentView)
    {
        //Load layout
        ViewGroup mainView = (ViewGroup)this.getLayoutInflater().inflate(R.layout.panorma, null);
        //Add 360 view
        mainView.addView(contentView, 0);
        //Return root content view
        return super.onContentViewCreated(mainView);
    }
}
