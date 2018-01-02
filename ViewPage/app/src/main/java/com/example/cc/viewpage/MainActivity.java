package com.example.cc.viewpage;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<Integer> itemList;
    ArrayList<ImageView> imageViewArrayList;
    AdapterViewerPager adapterViewerPager;
    List<Fragment> fragmentList;
    AdapterFragment adapterFragment;
    TabLayout tableLayout;
    ArrayList<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        itemList = new ArrayList<>();
        imageViewArrayList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemList.add(R.drawable.m1);
        }
        for (int i = 0; i < 2; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.m1);
            imageViewArrayList.add(imageView);
            MyFragment myFragment = new MyFragment();
            fragmentList.add(myFragment);
        }
        titleList.add("通话");
        titleList.add("联系人");
        adapterViewerPager = new AdapterViewerPager(this, imageViewArrayList, titleList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapterFragment = new AdapterFragment(fragmentManager, fragmentList,titleList);
        viewPager = (ViewPager) findViewById(R.id.view_page);
        viewPager.setAdapter(adapterFragment);
        tableLayout = (TabLayout) findViewById(R.id.tabs);
        tableLayout.setupWithViewPager(viewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
