package com.android.timesheet.shared.routers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.android.timesheet.App;
import com.android.timesheet.main.MainActivity;
import com.android.timesheet.shared.RestClient;
import com.android.timesheet.splash.SplashActivity;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public abstract class BaseRouter {

    @NonNull
    protected Context context;

    final SharedPreferences sharedPreferences;

    public BaseRouter(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void finishCurrentActivity() {
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public void logout() {

        App.getInstance().removeAuthorization();

        RestClient.getInstance().reset();

        openFreshActivity(SplashActivity.class);
    }

    public void openDeviceLocationSetting() {
        Intent settingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(settingIntent);
    }

    public void openAppPermission() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }

    public void openFreshActivity(Class<?> activityClass) {
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void openActivityDetails(Class<?> activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }

    public void openMainActivity() {
        openFreshActivity(MainActivity.class);
    }
}
