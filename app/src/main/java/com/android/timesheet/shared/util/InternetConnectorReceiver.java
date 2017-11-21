package com.android.timesheet.shared.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.timesheet.app.App;

/**
 * Created by vamsi on 11/21/2017.
 */

public class InternetConnectorReceiver extends BroadcastReceiver {

    static Context context;

    public InternetConnectorReceiver() {

    }

    public InternetConnectorReceiver(Context context) {
        InternetConnectorReceiver.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            if (InternetConnectorReceiver.context == null)
                InternetConnectorReceiver.context = context;

            boolean isVisible = App.isActivityVisible();// Check if
            // activity
            // is
            // visible
            // or not
//            Log.i("Activity is Visible ", "Is activity visible : " + isVisible);

            // If it is visible then trigger the task else do nothing
            if (isVisible) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connectivityManager != null) {
                    networkInfo = connectivityManager
                            .getActiveNetworkInfo();
                }

                // Check internet connection and accrding to state change the
                // text of activity by calling method
                if (networkInfo != null && networkInfo.isConnected()) {
                    InternetUtils.hideLoadingDialog();
                } else {
                    InternetUtils.showLoadingDialog(InternetConnectorReceiver.context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
