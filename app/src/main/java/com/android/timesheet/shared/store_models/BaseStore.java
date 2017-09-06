package com.android.timesheet.shared.store_models;

import android.content.Context;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public abstract class BaseStore {

    private Context mContext;

    protected Context getContext() {
        return mContext;
    }

    protected BaseStore(Context context) {
        mContext = context;
    }
}
