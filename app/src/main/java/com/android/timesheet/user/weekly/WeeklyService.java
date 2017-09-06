package com.android.timesheet.user.weekly;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class WeeklyService extends BaseService<IUserService>{

    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }

    public Observable getWeekReport(User user) {
        return observe(prepare().getWeekReport(user.empCode,"",""));
    }
}
