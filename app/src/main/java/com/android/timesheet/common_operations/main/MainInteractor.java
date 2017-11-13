package com.android.timesheet.common_operations.main;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class MainInteractor extends BaseInteractor<BaseService> {

    public MainInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return null;
    }
}
