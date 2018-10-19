package com.android.timesheet.admin_operations.leave.apply_on_duty.on_duty_entry;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;

public class OnDutyEntryInteractor extends BaseInteractor<OnDutyEntryService> {

    public OnDutyEntryInteractor(Context context) {
        super(context);
    }

    @Override
    protected OnDutyEntryService provideService() {
        return new OnDutyEntryService();
    }
}
