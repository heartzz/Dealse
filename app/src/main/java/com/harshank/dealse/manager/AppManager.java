package com.harshank.dealse.manager;

import android.content.SharedPreferences;

import com.harshank.dealse.applicationclass.AppApplicationClass;


/**
 * Created by harshank on 25/6/18.
 */

public class AppManager {
    /* shared Preference and its editor */
    private static final String PREFERENCE_NAME = "aprsapp";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    /* Constants for shared preferences */
    private static final String IS_FIRST_TIME = "isFirstTime";
    /* preference mode - private , only be accessed by JohnnyZCasino application */
    public int PRIVATE_MODE = 0;

    public boolean isFirstTime = false;

    /* Singleton Class */
    private static AppManager instance = new AppManager();

    private AppManager() {
    }


    public static AppManager getInstance() {
        return instance;
    }

    public void init() {
        isFirstTime = getIsFirstTime();
        if (isFirstTime) {
            setIsFirstTime(false);
        }
    }

    private boolean getIsFirstTime() {
        boolean value = true;
        if (getPref() != null && getPref().contains(IS_FIRST_TIME)) {
            value = getPref().getBoolean(IS_FIRST_TIME, true);
        }
        return value;
    }

    private void setIsFirstTime(boolean value) {
        getEditor().putBoolean(IS_FIRST_TIME, value);
        getEditor().commit();
    }

    public SharedPreferences getPref() {
        if (pref == null) {
            pref = AppApplicationClass.getAppContext().getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        }
        return pref;
    }

    public SharedPreferences.Editor getEditor() {
        if (editor == null) {
            editor = getPref().edit();
        }
        return editor;
    }
}