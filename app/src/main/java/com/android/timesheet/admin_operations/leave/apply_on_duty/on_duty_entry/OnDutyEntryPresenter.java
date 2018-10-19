package com.android.timesheet.admin_operations.leave.apply_on_duty.on_duty_entry;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class OnDutyEntryPresenter extends BasePresenter<BaseViewBehavior, OnDutyEntryInteractor, OnDutyEntryRouter> {


    public OnDutyEntryPresenter(Context context) {
        super(context);
    }

    public OnDutyEntryPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected OnDutyEntryInteractor provideInteractor() {
        return new OnDutyEntryInteractor(context);
    }

    @Override
    protected OnDutyEntryRouter provideRouter() {
        return new OnDutyEntryRouter(context);
    }

    public User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openActivity(Class<?> activityClass) {
        router().openActivityDetails(activityClass);
    }
}
