package com.shohda.bazideraz;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shohda.bazideraz.adapter.MissonAdapter;
import com.shohda.bazideraz.model.Mission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class detail_mission extends Activity {
       TextView mission_name,content_test;
       NetworkImageView img;
    public ImageLoader imageLoader;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misson_detail);
        mission_name =findViewById(R.id.mission_name);
        content_test = findViewById(R.id.content_test);
        img = findViewById(R.id.ContainerGallery);
        String id = getIntent().getStringExtra("ID");
        String name = getIntent().getStringExtra("name");
        imageLoader = AppController.getInstance().getImageLoader();
        getMissionFromJson(id);
    }
    public void getMissionFromJson(String id) {
        // progressBar.setVisibility(View.VISIBLE);
        // lytCategory.setVisibility(View.GONE);
        Constant.Url="http://bazideraz.offroadteam.ir/bazideraz/public/Api/getmission";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);

        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String error = jsonObject.getString(Constant.ERROR);
                    System.out.println("=====Data response " + response);
                    if (error.equalsIgnoreCase("false")) {
                        JSONObject jsonob = jsonObject.getJSONObject(Constant.DATA);



                        // object.getString("product_id");
                        mission_name.setText(jsonob.getString("mission_name"));
                        img.setImageUrl(jsonob.getString("mission_imagelarge"), imageLoader);
                        content_test.setText(jsonob.getString("content"));
                        //contetnt.setText(jsonob.getString("product_content"));

                        //   lytCategory.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, params);
    }
}
