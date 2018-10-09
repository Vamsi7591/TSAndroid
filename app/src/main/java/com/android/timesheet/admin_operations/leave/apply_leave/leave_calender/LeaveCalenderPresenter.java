package com.android.timesheet.admin_operations.leave.apply_leave.leave_calender;

import android.content.Context;

import com.android.timesheet.admin_operations.holiday_list.HolidaysInteractor;
import com.android.timesheet.admin_operations.holiday_list.HolidaysRouter;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class LeaveCalenderPresenter extends BasePresenter<BaseViewBehavior,LeaveCalenderInteractor, LeaveCalenderRouter> {

    public LeaveCalenderPresenter(Context context) {
        super(context);
    }

    public LeaveCalenderPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected LeaveCalenderInteractor provideInteractor() {
        return new LeaveCalenderInteractor(context);
    }

    @Override
    protected LeaveCalenderRouter provideRouter() {
        return new LeaveCalenderRouter(context);
    }
}
