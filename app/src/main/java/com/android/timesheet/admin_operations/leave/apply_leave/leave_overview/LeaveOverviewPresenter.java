package com.android.timesheet.admin_operations.leave.apply_leave.leave_overview;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class LeaveOverviewPresenter extends BasePresenter<BaseViewBehavior,LeaveOverviewInteractor,LeaveOverviewRouter> {

    public LeaveOverviewPresenter(Context context) {
        super(context);
    }

    public LeaveOverviewPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected LeaveOverviewInteractor provideInteractor() {
        return new LeaveOverviewInteractor(context);
    }

    @Override
    protected LeaveOverviewRouter provideRouter() {
        return new LeaveOverviewRouter(context);
    }
}
