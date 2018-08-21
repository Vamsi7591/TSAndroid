package com.android.timesheet.admin_operations.leave.approve_on_duty;

import android.content.Context;

import com.android.timesheet.admin_operations.leave.apply_leave.ApplyLeaveService;
import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

public class ApproveOnDutyInteractor extends BaseInteractor<ApproveOnDutyService>{


    public ApproveOnDutyInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return new ApplyLeaveService();
    }
}
