package com.android.timesheet.admin_operations.holiday_list;

import android.content.Context;

import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverviewInteractor;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverviewRouter;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class HolidaysPresenter extends BasePresenter<BaseViewBehavior,HolidaysInteractor, HolidaysRouter> {

    public HolidaysPresenter(Context context) {
        super(context);
    }

    public HolidaysPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected HolidaysInteractor provideInteractor() {
        return new HolidaysInteractor(context);
    }

    @Override
    protected HolidaysRouter provideRouter() {
        return new HolidaysRouter(context);
    }
}
