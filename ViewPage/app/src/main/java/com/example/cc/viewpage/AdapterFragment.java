package com.example.cc.viewpage;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cc on 17-12-29.
 */

public class AdapterFragment extends FragmentPagerAdapter {
    private List<Fragment> mFragment;

    public AdapterFragment(FragmentManager fragmentManager, List<Fragment> mFragment){
        super(fragmentManager);
        this.mFragment = mFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragment.get(position).getClass().getSimpleName();
    }
}
