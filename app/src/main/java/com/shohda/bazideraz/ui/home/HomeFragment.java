package com.shohda.bazideraz.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shohda.bazideraz.ApiConfig;
import com.shohda.bazideraz.AppController;
import com.shohda.bazideraz.Constant;
import com.shohda.bazideraz.activity_delneveshteh;
import com.shohda.bazideraz.adapter.CategoryAdapter;
import com.shohda.bazideraz.adapter.MissonAdapter;
import com.shohda.bazideraz.databinding.FragmentHomeBinding;
import com.shohda.bazideraz.databinding.FragmentHomeBinding;
import com.shohda.bazideraz.helper.CircularNetworkImageView;
import com.shohda.bazideraz.helper.Session;
import com.shohda.bazideraz.login_activity;
import com.shohda.bazideraz.model.Category;
import com.shohda.bazideraz.model.Mission;
import com.shohda.bazideraz.verify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public static CategoryAdapter adapter;
    public static MissonAdapter missonAdapter;
    TextView tvnamee;
    public ArrayList<Category> categoryList;
    public ArrayList<Mission> missionList;
    public RecyclerView recyclerView,MissionView;
    public ImageLoader imageLoader;
    RelativeLayout top;
    CircularNetworkImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        MissionView = binding.Mission;
        MissionView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
     //   final TextView textView = binding.textHome;
        imageLoader = AppController.getInstance().getImageLoader();
        tvnamee = binding.tvName;
        imageView = binding.imgProfile;
        String pic = Session.getUserData("profile",getContext());
        imageView.setImageUrl(pic,imageLoader);
        String name = Session.getUserData("name",getContext());
        tvnamee.setText(name);
       // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        top = binding.topPanel;
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), activity_delneveshteh.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        getMainCategoryFromJson();
        getMissionFromJson();
        return root;
    }
    public void getMissionFromJson() {
        // progressBar.setVisibility(View.VISIBLE);
       // lytCategory.setVisibility(View.GONE);
        Constant.Url="http://bazideraz.offroadteam.ir/bazideraz/public/Api/getmission";
        Map<String, String> params = new HashMap<>();


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {
                    missionList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    String error = jsonObject.getString(Constant.ERROR);
                    System.out.println("=====Data response " + response);
                    if (error.equalsIgnoreCase("false")) {

                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Mission misson = new Mission();
                            JSONObject object = jsonArray.getJSONObject(i);
                            misson.setId(object.getString("id"));
                            misson.setMission_name(object.getString("mission_name"));
                            misson.setMission_image(object.getString("mission_imagelarge"));
                            misson.setLocation(object.getString("Location"));
                            missionList.add(misson);
                        }

                        missonAdapter = new MissonAdapter(getContext(), missionList);
                        MissionView.setAdapter(missonAdapter);
                     //   lytCategory.setVisibility(View.VISIBLE);
                    } else {
                        //lytCategory.setVisibility(View.GONE);
                        if (missonAdapter != null) {
                            missonAdapter = new MissonAdapter(getContext(), missionList);
                            MissionView.setAdapter(missonAdapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, params);
    }
    public void getMainCategoryFromJson() {
        // progressBar.setVisibility(View.VISIBLE);
        // lytCategory.setVisibility(View.GONE);
        Constant.Url="http://bazideraz.offroadteam.ir/bazideraz/public/Api/getshop";
        Map<String, String> params = new HashMap<>();


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {
                    categoryList = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    String error = jsonObject.getString(Constant.ERROR);
                    System.out.println("=====Data response " + response);
                    if (error.equalsIgnoreCase("false")) {

                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Category category = new Category();
                            JSONObject object = jsonArray.getJSONObject(i);
                            category.setId(object.getString("product_id"));
                            category.setName(object.getString("product_name"));
                            category.setImage(object.getString("product_image"));
                            category.setPrice(object.getString("product_price"));
                            categoryList.add(category);
                        }

                        adapter = new CategoryAdapter(getContext(), categoryList);
                        recyclerView.setAdapter(adapter);
                        //   lytCategory.setVisibility(View.VISIBLE);
                    } else {
                        //lytCategory.setVisibility(View.GONE);
                        if (adapter != null) {
                            adapter = new CategoryAdapter(getContext(), categoryList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, params);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}