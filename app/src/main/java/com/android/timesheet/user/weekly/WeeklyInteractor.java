package com.android.timesheet.user.weekly;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class WeeklyInteractor extends BaseInteractor<WeeklyService> {


    public WeeklyInteractor(Context context) {
        super(context);
    }

    protected WeeklyService provideService() {
        return new WeeklyService();
    }
}
