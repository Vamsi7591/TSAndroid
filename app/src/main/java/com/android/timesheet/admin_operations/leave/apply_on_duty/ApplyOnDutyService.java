package com.android.timesheet.admin_operations.leave.apply_on_duty;

import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.ILeaveService;

public class ApplyOnDutyService extends BaseService<ILeaveService> {

    @Override
    protected ILeaveService prepare() {
        return super.prepare(ILeaveService.class);
    }
}
