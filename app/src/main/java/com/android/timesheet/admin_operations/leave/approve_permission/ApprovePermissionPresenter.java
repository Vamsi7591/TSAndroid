package com.android.timesheet.admin_operations.leave.approve_permission;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class ApprovePermissionPresenter extends BasePresenter<BaseViewBehavior, ApprovePermissionInteractor,ApprovePermissionRouter>{

    public ApprovePermissionPresenter(Context context) {
        super(context);
    }

    public ApprovePermissionPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ApprovePermissionInteractor provideInteractor() {
        return new ApprovePermissionInteractor(context);
    }

    @Override
    protected ApprovePermissionRouter provideRouter() {
        return new ApprovePermissionRouter(context);
    }
}
