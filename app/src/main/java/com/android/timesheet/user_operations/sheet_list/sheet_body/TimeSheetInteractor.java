package com.android.timesheet.user_operations.sheet_list.sheet_body;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetInteractor extends BaseInteractor<TimeSheetService> {

    public TimeSheetInteractor(Context context) {
        super(context);
    }

    @Override
    protected TimeSheetService provideService() {
        return new TimeSheetService();
    }

    public void getEmployeeTimeSheet(User user, ServiceCallback<List<TimeSheet>> callback) {

        service().getEmployeeTimeSheet(user).subscribe(new ServiceSubscriber<TimeSheetResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(TimeSheetResponse recommendation) {

                List<TimeSheet> uniqueResult = new ArrayList<>();

                if (recommendation.code == 200)
                    uniqueResult = recommendation.getTimeSheetList();

                callback.onSuccess(uniqueResult);
            }
        });
    }

    public void removeTimeSheet(String empCode,String timeSheetId, ServiceCallback<TimeSheetResponse> callback){
        service().removeTimeSheet(empCode,timeSheetId).subscribe(new ServiceSubscriber<TimeSheetResponse>() {
            @Override
            public void onFailure(Throwable e) {
                    callback.onFailure(e);
            }

            @Override
            public void onSuccess(TimeSheetResponse data) {
                callback.onSuccess(data);
            }
        });
    }

}
