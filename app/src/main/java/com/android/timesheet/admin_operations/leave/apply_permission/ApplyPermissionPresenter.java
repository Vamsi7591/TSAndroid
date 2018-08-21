package com.android.timesheet.admin_operations.leave.apply_permission;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class ApplyPermissionPresenter extends BasePresenter<BaseViewBehavior, ApplyPermissionInteractor, ApplyPermissionRouter>{

    public ApplyPermissionPresenter(Context context) {
        super(context);
    }

    public ApplyPermissionPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ApplyPermissionInteractor provideInteractor() {
        return new ApplyPermissionInteractor(context);
    }

    @Override
    protected ApplyPermissionRouter provideRouter() {
        return new ApplyPermissionRouter(context);
    }
}
