package com.android.timesheet.admin_operations.leave.leave_request;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class LeaveRequestPresenter extends BasePresenter<BaseViewBehavior, LeaveRequestInteractor, LeaveRequestRouter> {


    public LeaveRequestPresenter(Context context) {
        super(context);
    }

    public LeaveRequestPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected LeaveRequestInteractor provideInteractor() {
        return new LeaveRequestInteractor(context);
    }

    @Override
    protected LeaveRequestRouter provideRouter() {
        return new LeaveRequestRouter(context);
    }

    public User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openActivity(Class<?> activityClass) {
        router().openActivityDetails(activityClass);
    }

    public void openActivity(Class<?> activityClass, String s) {
        router().openActivityDetails(activityClass,s);
    }
}
