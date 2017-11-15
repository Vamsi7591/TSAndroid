package com.android.timesheet.user_operations.reports.monthly;

import com.android.timesheet.shared.models.MonthParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class MonthlyService extends BaseService<IUserService> {
    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }

    public Observable getMonthReport(MonthParams monthParams) {
        return observe(prepare().getMonthReport(monthParams.getEmpCode(),monthParams.getMonthNo(),monthParams.getYear()));
    }
}
