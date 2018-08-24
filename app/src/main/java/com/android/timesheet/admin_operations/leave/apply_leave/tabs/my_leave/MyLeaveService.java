package com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave;

import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.ILeaveService;

public class MyLeaveService extends BaseService<ILeaveService> {
    @Override
    protected ILeaveService prepare() {
        return super.prepare(ILeaveService.class);
    }
}
