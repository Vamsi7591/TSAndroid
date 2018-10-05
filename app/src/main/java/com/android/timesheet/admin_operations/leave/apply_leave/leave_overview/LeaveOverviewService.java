package com.android.timesheet.admin_operations.leave.apply_leave.leave_overview;

import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.ILeaveService;

public class LeaveOverviewService extends BaseService<ILeaveService> {
    @Override
    protected ILeaveService prepare() {
        return super.prepare(ILeaveService.class);
    }
}
