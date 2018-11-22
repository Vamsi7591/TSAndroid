package com.android.timesheet.admin_operations.holiday_list;

import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.ILeaveService;

public class HolidaysService extends BaseService<HolidaysService> {
    @Override
    protected HolidaysService prepare() {
        return super.prepare(HolidaysService.class);
    }
}
