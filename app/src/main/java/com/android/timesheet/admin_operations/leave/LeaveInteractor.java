package com.android.timesheet.admin_operations.leave;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

public class LeaveInteractor extends BaseInteractor<BaseService>{

    public LeaveInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return null;
    }
}
