package com.android.timesheet.user_operations.timesheet.sheet_fragment;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetInteractor extends BaseInteractor<BaseService>{

    public TimeSheetInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return null;
    }
}
