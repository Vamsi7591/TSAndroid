package com.android.timesheet.shared.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class InternetUtils {

    public static boolean isInternetConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        //boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
       return  activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
