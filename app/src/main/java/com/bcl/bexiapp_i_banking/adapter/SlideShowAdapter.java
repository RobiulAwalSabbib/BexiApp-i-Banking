package com.bcl.bexiapp_i_banking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bcl.bexiapp_i_banking.R;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class SlideShowAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater layoutInflater;
    public int[] images = {

            R.drawable.bcl,
            R.drawable.bcl1,
            R.drawable.bcl2,
            R.drawable.bcl3,
            R.drawable.bcl4,
            R.drawable.bcl5
    };

    public SlideShowAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return images.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slideshow_layout,container,false);
        ImageView img = (ImageView)view.findViewById(R.id.imageView_ID);
        //img.setImageResource(images[position]);
        Glide.with(context).load(images[position]).into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"image "+( position +1), Snackbar.LENGTH_LONG).show();
            }
        });
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  (view == (LinearLayout)object);

    }
}
