package com.shohda.bazideraz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shohda.bazideraz.AppController;
import com.shohda.bazideraz.Constant;
import com.shohda.bazideraz.R;

import java.util.ArrayList;
import java.util.Locale;

import com.shohda.bazideraz.detail_mission;
import com.shohda.bazideraz.detail_shop;
import com.shohda.bazideraz.model.Category;
import com.shohda.bazideraz.model.Mission;

public class MissonAdapter extends RecyclerView.Adapter<MissonAdapter.ItemRowHolder> {

    private final ArrayList<Mission> dataList;
    public Context mContext;
    public  ImageLoader imageLoader;
    public MissonAdapter(Context context, ArrayList<Mission> dataList) {
        this.dataList = dataList;
        this.mContext = context;
        imageLoader = AppController.getInstance().getImageLoader();
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mission, parent, false);
        return new ItemRowHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {

        final Mission Mission = dataList.get(position);

        holder.tvOneToOne.setText(Mission.getMission_name());
        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.iran_sans_web);
        holder.tvOneToOne.setTypeface(typeface);
        holder.image.setDefaultImageResId(R.drawable.ic_contest);
        holder.image.setImageUrl(Mission.getMission_image(), imageLoader);
        holder.image.setScaleType(ImageView.ScaleType.FIT_XY);


        holder.tv_oto_play.setText(Mission.getLocation());
        Typeface typeface2 = ResourcesCompat.getFont(mContext, R.font.iran_sans_web);
        holder.tv_oto_play.setTypeface(typeface2);
        holder.relativeLayout
                .setOnClickListener(v -> {

                    String  ID   = Mission.getId();
                    String name = Mission.getMission_name();


                    Intent intent = new Intent(mContext, detail_mission.class);
                    intent.putExtra("ID", ID);
                    intent.putExtra("name", name);
                    mContext.startActivity(intent);

                });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        public NetworkImageView image;
        public TextView tvOneToOne, tv_oto_play;
        RelativeLayout relativeLayout, cardTitle;

        public ItemRowHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tvOneToOne = itemView.findViewById(R.id.tvOneToOne);
            relativeLayout = itemView.findViewById(R.id.backimg);
            tv_oto_play = itemView.findViewById(R.id.tv_oto_play);
            cardTitle = itemView.findViewById(R.id.cardTitle);
        }
    }
}
