package com.android.timesheet.admin_operations.holiday_list;

import android.content.Context;

import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverviewService;
import com.android.timesheet.shared.interactors.BaseInteractor;

public class HolidaysInteractor extends BaseInteractor<HolidaysService> {

    public HolidaysInteractor(Context context) {
        super(context);
    }

    protected HolidaysService provideService() {
        return new HolidaysService();
    }

}
