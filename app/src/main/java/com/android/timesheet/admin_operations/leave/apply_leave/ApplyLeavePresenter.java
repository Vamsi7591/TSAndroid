package com.android.timesheet.admin_operations.leave.apply_leave;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class ApplyLeavePresenter extends BasePresenter<BaseViewBehavior, ApplyLeaveInteractor, ApplyLeaveRouter> {


    public ApplyLeavePresenter(Context context) {
        super(context);
    }

    public ApplyLeavePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ApplyLeaveInteractor provideInteractor() {
        return new ApplyLeaveInteractor(context);
    }

    @Override
    protected ApplyLeaveRouter provideRouter() {
        return new ApplyLeaveRouter(context);
    }

    public User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openActivity(Class<?> activityClass) {
        router().openActivityDetails(activityClass);
    }
}
