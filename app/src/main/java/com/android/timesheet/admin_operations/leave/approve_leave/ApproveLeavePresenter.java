package com.android.timesheet.admin_operations.leave.approve_leave;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class ApproveLeavePresenter extends BasePresenter<BaseViewBehavior, ApproveLeaveInteractor, ApproveLeaveRouter>{

    public ApproveLeavePresenter(Context context) {
        super(context);
    }

    public ApproveLeavePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ApproveLeaveInteractor provideInteractor() {
        return new ApproveLeaveInteractor(context);
    }

    @Override
    protected ApproveLeaveRouter provideRouter() {
        return new ApproveLeaveRouter(context);
    }
}
