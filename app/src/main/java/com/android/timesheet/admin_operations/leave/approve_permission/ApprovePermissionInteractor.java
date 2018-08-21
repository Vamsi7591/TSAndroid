package com.android.timesheet.admin_operations.leave.approve_permission;

import android.content.Context;

import com.android.timesheet.admin_operations.leave.apply_leave.ApplyLeaveService;
import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class ApprovePermissionInteractor extends BaseInteractor<ApprovePermissionService>{

    public ApprovePermissionInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return new ApplyLeaveService();
    }
}
