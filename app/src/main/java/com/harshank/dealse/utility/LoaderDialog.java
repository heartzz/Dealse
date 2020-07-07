package com.harshank.dealse.utility;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by user on 9/19/2016.
 */
public class LoaderDialog {
    static ProgressDialog pDialog;
    public static void showLoader(Context context){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }
    public static void  hideLoader(){
        if(pDialog != null){
            if(pDialog.isShowing()){
            pDialog.dismiss();
        }
        }
    }
}
