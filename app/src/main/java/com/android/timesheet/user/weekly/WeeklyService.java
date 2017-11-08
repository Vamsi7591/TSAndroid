package com.android.timesheet.user.weekly;

import com.android.timesheet.shared.models.WeekParams;
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

    public Observable getWeekReport(WeekParams weekParams) {
        return observe(prepare().getWeekReport(weekParams.getEmpCode(),weekParams.getWeekNo(),weekParams.getYear()));
    }
}
