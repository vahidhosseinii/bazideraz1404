package com.shohda.bazideraz;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shohda.bazideraz.adapter.CategoryAdapter;
import com.shohda.bazideraz.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class detail_shop extends Activity {
    TextView productname,textPrice,contetnt;
    NetworkImageView img;
    public ImageLoader imageLoader;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_details);
        imageLoader = AppController.getInstance().getImageLoader();
        productname = findViewById(R.id.name);
        textPrice = findViewById(R.id.textPrice);
        contetnt = findViewById(R.id.contetnt);
        img = findViewById(R.id.ContainerGallery);
        String id = getIntent().getStringExtra("ID");
        getdetail(id);
    }
    public void getdetail(String id) {
        // progressBar.setVisibility(View.VISIBLE);
        // lytCategory.setVisibility(View.GONE);
        Constant.Url="http://bazideraz.offroadteam.ir/bazideraz/public/Api/getshop";
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
                             productname.setText(jsonob.getString("product_name"));
                             img.setImageUrl(jsonob.getString("product_image"), imageLoader);
                             textPrice.setText(jsonob.getString("product_price") + " تومان");
                             contetnt.setText(jsonob.getString("product_content"));




                    } else {


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, params);
    }
}
