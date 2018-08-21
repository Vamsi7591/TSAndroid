package com.android.timesheet.admin_operations.leave.approve_leave;

import android.content.Context;

import com.android.timesheet.admin_operations.leave.apply_leave.ApplyLeaveService;
import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

public class ApproveLeaveInteractor extends BaseInteractor<ApproveLeaveService>{

    public ApproveLeaveInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return new ApplyLeaveService();
    }
}
