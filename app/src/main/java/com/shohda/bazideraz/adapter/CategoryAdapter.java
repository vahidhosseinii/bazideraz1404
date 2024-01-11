package com.shohda.bazideraz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.shohda.bazideraz.detail_shop;
import com.shohda.bazideraz.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemRowHolder> {

    private final ArrayList<Category> dataList;
    public Context mContext;
    public  ImageLoader imageLoader;
    public CategoryAdapter(Context context, ArrayList<Category> dataList) {
        this.dataList = dataList;
        this.mContext = context;
        imageLoader = AppController.getInstance().getImageLoader();
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_maincat, parent, false);
        return new ItemRowHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, final int position) {

        final Category category = dataList.get(position);

        holder.text.setText(category.getName());
        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.iran_sans_web);
        holder.text.setTypeface(typeface);
        holder.image.setDefaultImageResId(R.drawable.ic_contest);
        holder.image.setImageUrl(category.getImage(), imageLoader);


        holder.tvNoOfQue.setText(category.getPrice() + " تومان");
        Typeface typeface2 = ResourcesCompat.getFont(mContext, R.font.iran_sans_web);
        holder.tvNoOfQue.setTypeface(typeface2);
        holder.relativeLayout
                .setOnClickListener(v -> {

                    String  ID   = category.getId();
                    String name = category.getName();
                    String price = category.getPrice();

                    Intent intent = new Intent(mContext, detail_shop.class);
                    intent.putExtra("ID", ID);
                    intent.putExtra("name", name);
                    intent.putExtra("price", price);
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
        public TextView text, tvNoOfQue;
        RelativeLayout relativeLayout, cardTitle;

        public ItemRowHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cateImg);
            text = itemView.findViewById(R.id.item_title);
            relativeLayout = itemView.findViewById(R.id.cat_layout);
            tvNoOfQue = itemView.findViewById(R.id.noofque);
            cardTitle = itemView.findViewById(R.id.cardTitle);
        }
    }
}
