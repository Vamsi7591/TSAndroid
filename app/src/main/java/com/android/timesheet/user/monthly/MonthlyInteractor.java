package com.android.timesheet.user.monthly;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class MonthlyInteractor extends BaseInteractor<MonthlyService> {

    public MonthlyInteractor(Context context) {
        super(context);
    }

    @Override
    protected MonthlyService provideService() {
        return new MonthlyService();
    }
}
