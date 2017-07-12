package com.ywj.meiqijiaju.application;

import android.app.Application;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
