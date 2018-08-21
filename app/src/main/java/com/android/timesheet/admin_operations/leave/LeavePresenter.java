package com.android.timesheet.admin_operations.leave;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class LeavePresenter extends BasePresenter<BaseViewBehavior, LeaveInteractor, LeaveRouter>{

    public LeavePresenter(Context context) {
        super(context);
    }

    public LeavePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }


    @Override
    protected LeaveInteractor provideInteractor() {
        return new LeaveInteractor(context);
    }

    @Override
    protected LeaveRouter provideRouter() {
        return new LeaveRouter(context);
    }

    public User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openActivity(Class<?> activityClass) {
        router().openActivityDetails(activityClass);
    }
}
