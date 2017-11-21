package com.android.timesheet.app;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.shared.database.AppDatabase;
import com.android.timesheet.shared.store_models.UserStore;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.otto.Bus;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class App extends Application implements Thread.UncaughtExceptionHandler {

    private static Context mContext;
    private Thread.UncaughtExceptionHandler _androidUncaughtExceptionHandler;
    String TAG = "TSApp";

    ProgressDialog proDialog;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        /*Instantiating exception handling globally*/
        _androidUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(this);

        /*This instantiates DBFlow Library*/
        initDatabase();

        proDialog = new ProgressDialog(this);

//        startService(new Intent(this, BackgroundService.class)); //start service which is MyService.java
//        registerReceiver(receiver, new IntentFilter(AppConfig.INTERNET_CHECK));
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static App getInstance() {
        return (App) mContext;
    }

    public void removeAuthorization() {
        SharedPreferences.Editor sharedPrefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPrefEditor.clear().apply();

        UserStore.clearUser();
        initDatabase();
    }

    /**
     * Initializes DBFlow, loading the main application Database holder via reflection one time only.
     * This will trigger all creations, updates, and instantiation for each database defined.
     *
     * @code new FlowConfig The configuration instance that will help shape how DBFlow gets constructed.
     * @code reset() Resets all databases and associated files.
     * @code destroy() Release reference to context and {@link FlowConfig}
     */
    public void initDatabase() {
        FlowManager.reset();
        FlowManager.destroy();

        FlowManager.init(
                new FlowConfig
                        .Builder(this)
                        .addDatabaseConfig(new DatabaseConfig.Builder(AppDatabase.class).build())
                        .build());
    }


    /**
     * Instantiate a Bus and access it throughout the application by calling {@code App.getBus()}.
     *
     * @return mBus instance of Bus.
     */
    private Bus mBus;

    public Bus getBus() {
        if (mBus == null) {
            mBus = new Bus();
        }
        return mBus;
    }

    public void customToast(String message) {
        Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View customToastRoot = inflater.inflate(R.layout.custom_toast, null);

        TextView messageText = (TextView) customToastRoot.findViewById(R.id.messageTV);
        messageText.setText(message);

        Toast customToast = new Toast(context);
        customToast.setView(customToastRoot);
        customToast.setGravity(Gravity.BOTTOM, 0, 200);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.show();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        Log.v(TAG, throwable.getMessage());

        /*Let Android show the default error dialog*/
        _androidUncaughtExceptionHandler.uncaughtException(thread, throwable);

    }

    // Gloabl declaration of variable to use in whole app

    public static boolean activityVisible; // Variable that will check the
    // current activity state

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false;// this will set false when activity paused
    }


    /*Broadcast receiver*/
    boolean isShowing = false;
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case AppConfig.INTERNET_CHECK:
                    // do something
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        boolean flag = bundle.getBoolean(AppConfig.INTERNET);
                        if (flag) {
                            if (isShowing) {
                                if (proDialog != null) {
                                    if (proDialog.isShowing()) {
                                        new Handler().postDelayed(() -> {
                                                    proDialog.setTitle("HURRAY!");
                                                    proDialog.setMessage("Internet available :)");
                                                    proDialog.setCancelable(false);
                                                    proDialog.show();
                                                }
                                                , 1000);
                                        proDialog.dismiss();
                                    }
                                }
                                isShowing = false;
                            }
//                            Toast.makeText(ImageActivity.this, "Have network", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!isShowing) {
                                if (proDialog != null && !proDialog.isShowing()) {
                                    new Handler().postDelayed(() -> {
                                                proDialog.setTitle("OOPS!");
                                                proDialog.setMessage("Internet disconnected :(");
                                                proDialog.setCancelable(false);
                                                proDialog.show();
                                            }
                                            , 0);
                                }
                                isShowing = true;
                            }
//                            Toast.makeText(ImageActivity.this, "No network", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }
    };

}
