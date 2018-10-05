package com.android.timesheet.admin_operations.leave.apply_leave.leave_overview;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;

public class LeaveOverviewInteractor extends BaseInteractor<LeaveOverviewService> {

    public LeaveOverviewInteractor(Context context) {
        super(context);
    }

    protected LeaveOverviewService provideService() {
        return new LeaveOverviewService();
    }

}
