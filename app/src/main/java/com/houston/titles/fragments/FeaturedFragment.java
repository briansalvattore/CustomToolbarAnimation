package com.houston.titles.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.houston.titles.R;

/**
 * Created by bcast_000 on 30/06/2015.
 */
public class FeaturedFragment extends Fragment {

    public static Fragment newInstance(){

        return new FeaturedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.cardviews, container, false);
    }

}
