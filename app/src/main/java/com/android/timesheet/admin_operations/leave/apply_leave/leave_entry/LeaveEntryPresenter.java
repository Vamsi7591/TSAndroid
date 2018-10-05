package com.android.timesheet.admin_operations.leave.apply_leave.leave_entry;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class LeaveEntryPresenter extends BasePresenter<BaseViewBehavior, LeaveEntryInteractor, LeaveEntryRouter> {


    public LeaveEntryPresenter(Context context) {
        super(context);
    }

    public LeaveEntryPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected LeaveEntryInteractor provideInteractor() {
        return new LeaveEntryInteractor(context);
    }

    @Override
    protected LeaveEntryRouter provideRouter() {
        return new LeaveEntryRouter(context);
    }

    public User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openActivity(Class<?> activityClass) {
        router().openActivityDetails(activityClass);
    }
}
