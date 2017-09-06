package com.android.timesheet.user.monthly;

import com.android.timesheet.shared.models.User;
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

    public Observable getMonthReport(User user) {
        return observe(prepare().getMonthReport(user.empCode,"",""));
    }
}
