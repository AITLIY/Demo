package com.demo.test;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2019/4/8.
 */

public class GlobalApplication extends Application{

    private static RequestQueue mRequestQueue;
    private static GlobalApplication instance;                                 //实例

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        LeakCanary.install(this);
    }

    public static GlobalApplication getInstance() {
        return instance;
    }

    public static RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getInstance());
            mRequestQueue.getCache().clear();
        }

        return mRequestQueue;
    }
}
