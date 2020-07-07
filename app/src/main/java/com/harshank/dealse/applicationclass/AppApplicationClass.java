package com.harshank.dealse.applicationclass;

import android.app.Application;
import android.content.Context;

import com.harshank.dealse.manager.AppManager;


/**
 * Created by harshank on 25/6/18.
 */

public class AppApplicationClass extends Application {
    private static Context context;
    private static AppApplicationClass mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppApplicationClass.context = getApplicationContext();
        AppManager.getInstance().init();

    }
    public static Context getAppContext() {
        return AppApplicationClass.context;
    }
    public static synchronized AppApplicationClass getInstance() {
        return mInstance;
    }

}