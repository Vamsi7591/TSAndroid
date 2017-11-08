package com.android.timesheet.shared.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by vamsikonanki on 8/21/2017.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    static final String NAME = "TSWS";

    static final int VERSION = 1;
}
