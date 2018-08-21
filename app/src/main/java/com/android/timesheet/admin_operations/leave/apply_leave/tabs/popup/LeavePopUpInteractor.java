package com.android.timesheet.admin_operations.leave.apply_leave.tabs.popup;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;

public class LeavePopUpInteractor extends BaseInteractor<LeavePopUpService> {

    public LeavePopUpInteractor(Context context) {
        super(context);
    }

    @Override
    protected LeavePopUpService provideService() {
        return new LeavePopUpService();
    }
}
