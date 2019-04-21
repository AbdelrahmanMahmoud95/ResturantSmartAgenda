package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.agenda.smart.restaurant.R;

/**
 * Created by Abdelrahman on 7/5/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private int[] images = {R.drawable.kofta,R.drawable.stek,R.drawable.botato};
    private LayoutInflater inflater;
    private Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View view1 = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) view1.findViewById(R.id.slider_image);
        myImage.setImageResource(images[position]);
        // Picasso.with(context).load(images.get(position).getImage()).into(myImage);

        view.addView(view1, 0);
        return view1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
