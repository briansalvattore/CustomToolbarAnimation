package com.houston.titles.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.houston.titles.fragments.FeaturedFragment;

/**
 * Created by bcast_000 on 30/06/2015.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        return FeaturedFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
