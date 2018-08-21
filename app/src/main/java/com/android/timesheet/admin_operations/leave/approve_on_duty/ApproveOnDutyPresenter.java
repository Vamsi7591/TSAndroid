package com.android.timesheet.admin_operations.leave.approve_on_duty;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class ApproveOnDutyPresenter extends BasePresenter<BaseViewBehavior, ApproveOnDutyInteractor, ApproveOnDutyRouter>{


    public ApproveOnDutyPresenter(Context context) {
        super(context);
    }

    public ApproveOnDutyPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ApproveOnDutyInteractor provideInteractor() {
        return new ApproveOnDutyInteractor(context);
    }

    @Override
    protected ApproveOnDutyRouter provideRouter() {
        return new ApproveOnDutyRouter(context);
    }

}
