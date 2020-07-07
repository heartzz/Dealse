package com.harshank.dealse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.harshank.dealse.R;
import com.harshank.dealse.manager.AppManager;
import com.harshank.dealse.utility.FirebaseConstants;


/**
 * Created by harshank on 31/10/17.
 */

public class SplashScreen extends Activity {

    public static String TAG = "SplashScreen";
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    public String uuidString;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(AppManager.getInstance().getPref().getString(FirebaseConstants.FIELD_EMAIL,"").equalsIgnoreCase("")){
                    Intent mainscreenIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(mainscreenIntent);
                    finish();
                }else {
                    Intent mainscreenIntent = new Intent(SplashScreen.this, HomeScreen.class);
                    startActivity(mainscreenIntent);
                    finish();
                }
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}