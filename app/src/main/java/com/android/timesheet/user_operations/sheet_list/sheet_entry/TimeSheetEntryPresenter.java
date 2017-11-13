package com.android.timesheet.user_operations.sheet_list.sheet_entry;

import android.content.Context;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class TimeSheetEntryPresenter extends BasePresenter<BaseViewBehavior, TimeSheetEntryInteractor,
        TimeSheetEntryRouter> {

    public TimeSheetEntryPresenter(Context context) {
        super(context);
    }

    public TimeSheetEntryPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected TimeSheetEntryInteractor provideInteractor() {
        return new TimeSheetEntryInteractor(context);
    }

    @Override
    protected TimeSheetEntryRouter provideRouter() {
        return new TimeSheetEntryRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void submitTimeSheet(TimeSheet sheet) {
        interactor().submitTimeSheet(sheet, new ServiceCallback<TimeSheetResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(TimeSheetResponse data) {

                viewBehaviour().onSuccess(data);
            }
        });
    }

    public void updateSheet(TimeSheet sheet) {
        interactor().updateTS(sheet, new ServiceCallback<TimeSheetResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(TimeSheetResponse data) {

                viewBehaviour().onSuccess(data);
            }
        });
    }


    public void getProjectNames(String empCode){

        interactor().getProjectNames(empCode, new ServiceCallback<ProjectNamesResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }

    public void removeTimeSheet(String empCode,String timeSheetId){
        interactor().removeTimeSheet(empCode, timeSheetId, new ServiceCallback<TimeSheetResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(TimeSheetResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });

    }


}
