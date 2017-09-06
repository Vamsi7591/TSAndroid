package com.android.timesheet;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;

import com.android.timesheet.shared.database.AppDatabase;
import com.android.timesheet.shared.store_models.UserStore;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.otto.Bus;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class App extends Application {

    private static Context mContext;
    private Bus mBus;

    String TAG = "App";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        // This instantiates DBFlow
        initDatabase();
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

    public void initDatabase() {
        FlowManager.reset();
        FlowManager.destroy();

        FlowManager.init(
                new FlowConfig
                        .Builder(this)
                        .addDatabaseConfig(new DatabaseConfig.Builder(AppDatabase.class).build())
                        .build());
    }

    public Bus getBus() {
        if (mBus == null) {
            mBus = new Bus();
        }

        return mBus;
    }

}
