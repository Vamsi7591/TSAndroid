package com.android.timesheet.user.list;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetService extends BaseService<IUserService>{

    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }

    public Observable getEmployeeTimeSheet(User user){
        return observe(prepare().getTimeSheet(user.empCode,user.empRole.substring(0,1)));
    }

    public Observable removeTimeSheet(String empCode,String timeSheetId){
        return observe(prepare().removeTimeSheet(empCode,timeSheetId));
    }
}
