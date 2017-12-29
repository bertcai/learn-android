package com.example.cc.viewpage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by cc on 17-12-29.
 */

public class AdapterViewerPager extends PagerAdapter {
    private Context context;
    private ArrayList<ImageView> mViewList;

    public AdapterViewerPager(Context context,  ArrayList<ImageView> views){
        mViewList = views;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }
}
