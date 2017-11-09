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

        /*This instantiates DBFlow Library*/
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

}
