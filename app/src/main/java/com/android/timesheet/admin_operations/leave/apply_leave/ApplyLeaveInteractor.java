package com.android.timesheet.admin_operations.leave.apply_leave;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

public class ApplyLeaveInteractor extends BaseInteractor<ApplyLeaveService> {

    public ApplyLeaveInteractor(Context context) {
        super(context);
    }

    @Override
    protected ApplyLeaveService provideService() {
        return new ApplyLeaveService();
    }
}
