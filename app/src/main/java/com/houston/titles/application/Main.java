package com.houston.titles.application;

import android.app.Application;

import com.houston.titles.commons.Methods;

/**
 * Created by bcast_000 on 30/06/2015.
 */
public class Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Methods.init(getApplicationContext());

    }
}
