package com.android.timesheet.admin_operations.leave.apply_leave.leave_entry;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;

public class LeaveEntryInteractor extends BaseInteractor<LeaveEntryService> {

    public LeaveEntryInteractor(Context context) {
        super(context);
    }

    @Override
    protected LeaveEntryService provideService() {
        return new LeaveEntryService();
    }
}
