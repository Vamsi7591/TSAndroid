package com.android.timesheet.user.sheet_entry;

import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class TimeSheetEntryServices extends BaseService<IUserService> {

    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }

    public Observable submitTimeSheet(TimeSheet sheet) {
        return observe(prepare().submitTimeSheet(
                sheet.empCode,
                sheet.date,
                sheet.weekNo,
                sheet.projectName,
                sheet.taskDescription,
                sheet.startTime,
                sheet.endTime
        ));
    }

    public Observable getProjectNames(String empCode){
        return observe(prepare().getProjectNames(empCode));
    }

    public Observable removeTimeSheet(String empCode,String timeSheetId){
        return observe(prepare().removeTimeSheet(empCode,timeSheetId));
    }


}
