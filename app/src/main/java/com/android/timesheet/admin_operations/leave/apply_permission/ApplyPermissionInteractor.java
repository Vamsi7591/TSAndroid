package com.android.timesheet.admin_operations.leave.apply_permission;

import android.content.Context;

import com.android.timesheet.admin_operations.leave.apply_leave.ApplyLeaveService;
import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

public class ApplyPermissionInteractor extends BaseInteractor<ApplyPermissionService>{

    public ApplyPermissionInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return new ApplyLeaveService();
    }
}
