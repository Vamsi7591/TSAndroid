package com.android.timesheet.user_operations.sheet_list.sheet_entry;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class TimeSheetEntryInteractor extends BaseInteractor<TimeSheetEntryServices> {

    public TimeSheetEntryInteractor(Context context) {
        super(context);
    }

    @Override
    protected TimeSheetEntryServices provideService() {
        return new TimeSheetEntryServices();
    }

    public void submitTimeSheet(TimeSheet sheet, ServiceCallback<TimeSheetResponse> callback) {

        service().submitTimeSheet(sheet).subscribe(new ServiceSubscriber<TimeSheetResponse>() {
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

    public void updateTS(TimeSheet sheet, ServiceCallback<TimeSheetResponse> callback) {

        service().updateTimeSheet(sheet).subscribe(new ServiceSubscriber<TimeSheetResponse>() {
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


    public void getProjectNames(String empCode, ServiceCallback<ProjectNamesResponse> callback) {

        service().getProjectNames(empCode).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {
                callback.onSuccess(data);
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
