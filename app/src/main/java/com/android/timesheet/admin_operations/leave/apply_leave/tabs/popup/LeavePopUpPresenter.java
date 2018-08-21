package com.android.timesheet.admin_operations.leave.apply_leave.tabs.popup;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class LeavePopUpPresenter extends BasePresenter<BaseViewBehavior, LeavePopUpInteractor, LeavePopUpRouter> {


    public LeavePopUpPresenter(Context context) {
        super(context);
    }

    public LeavePopUpPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected LeavePopUpInteractor provideInteractor() {
        return new LeavePopUpInteractor(context);
    }

    @Override
    protected LeavePopUpRouter provideRouter() {
        return new LeavePopUpRouter(context);
    }

    public User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openActivity(Class<?> activityClass) {
        router().openActivityDetails(activityClass);
    }
}
