package com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.user_operations.reports.weekly.WeeklyService;

public class MyLeaveInteractor extends BaseInteractor<MyLeaveService> {

    public MyLeaveInteractor(Context context) {
        super(context);
    }

    protected MyLeaveService provideService() {
        return new MyLeaveService();
    }

}
