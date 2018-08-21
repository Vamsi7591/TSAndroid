package com.android.timesheet.admin_operations.leave.apply_on_duty;

import android.content.Context;

import com.android.timesheet.admin_operations.leave.apply_leave.ApplyLeaveService;
import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.ILeaveService;

public class ApplyOnDutyInteractor extends BaseInteractor<ApplyOnDutyService> {

    public ApplyOnDutyInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return new ApplyLeaveService();
    }
}
