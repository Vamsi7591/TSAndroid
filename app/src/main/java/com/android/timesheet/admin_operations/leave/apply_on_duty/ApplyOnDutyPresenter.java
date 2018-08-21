package com.android.timesheet.admin_operations.leave.apply_on_duty;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class ApplyOnDutyPresenter extends BasePresenter<BaseViewBehavior, ApplyOnDutyInteractor, ApplyOnDutyRouter>{

    public ApplyOnDutyPresenter(Context context) {
        super(context);
    }

    public ApplyOnDutyPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ApplyOnDutyInteractor provideInteractor() {
        return new ApplyOnDutyInteractor(context);
    }

    @Override
    protected ApplyOnDutyRouter provideRouter() {
        return new ApplyOnDutyRouter(context);
    }
}
