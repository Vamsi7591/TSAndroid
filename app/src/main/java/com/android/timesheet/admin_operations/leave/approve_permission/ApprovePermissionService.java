package com.android.timesheet.admin_operations.leave.approve_permission;

import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.ILeaveService;

public class ApprovePermissionService extends BaseService<ILeaveService> {

    @Override
    protected ILeaveService prepare() {
        return super.prepare(ILeaveService.class);
    }
}
