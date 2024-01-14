package com.shohda.bazideraz;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.shohda.bazideraz.R;

import java.util.Objects;

class ViewPagerAdapter extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    int[] images;
    String[] title;

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPagerAdapter(Context context, int[] images, String[] text) {
        this.context = context;
        this.images = images;
        this.title  = text;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.slider_item, container, false);


        // referencing the image view from the item.xml file
        ImageView imageView =  itemView.findViewById(R.id.imageViewMain);

        Log.e("title[position]", String.valueOf(position));
       //  mainslider.textView.setText(title[position]);
        // setting the image in the imageView
        imageView.setImageResource(images[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0 :
                        Intent intent0 = new Intent(context, panorma.class);
                        context.startActivity(intent0);

                        break;
                    case  1:
                        Intent intent1 = new Intent(context, login_activity.class);
                        context.startActivity(intent1);
                    case 2 :
                        Intent intent2 = new Intent(context, login_activity.class);
                        context.startActivity(intent2);
                    case 3 :
                         Intent intent3 = new Intent(context, login_activity.class);
                         context.startActivity(intent3);
                    case 4 :
                        Intent intent4 = new Intent(context, login_activity.class);
                        context.startActivity(intent4);
                    case 5 :
                        Intent intent5 = new Intent(context, login_activity.class);
                        context.startActivity(intent5);
                    case  6 :
                        Intent intent6 = new Intent(context, login_activity.class);
                        context.startActivity(intent6);
                }
            }
        });
        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
