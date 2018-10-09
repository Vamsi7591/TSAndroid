package com.android.timesheet.admin_operations.leave.apply_leave.leave_request;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;

public class LeaveRequestInteractor extends BaseInteractor<LeaveRequestService> {

    public LeaveRequestInteractor(Context context) {
        super(context);
    }

    @Override
    protected LeaveRequestService provideService() {
        return new LeaveRequestService();
    }
}
