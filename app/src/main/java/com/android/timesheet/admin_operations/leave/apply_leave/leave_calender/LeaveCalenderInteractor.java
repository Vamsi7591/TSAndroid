package com.android.timesheet.admin_operations.leave.apply_leave.leave_calender;

import android.content.Context;

import com.android.timesheet.admin_operations.holiday_list.HolidaysService;
import com.android.timesheet.shared.interactors.BaseInteractor;

public class LeaveCalenderInteractor extends BaseInteractor<LeaveCalenderService> {

    public LeaveCalenderInteractor(Context context) {
        super(context);
    }

    protected LeaveCalenderService provideService() {
        return new LeaveCalenderService();
    }

}
