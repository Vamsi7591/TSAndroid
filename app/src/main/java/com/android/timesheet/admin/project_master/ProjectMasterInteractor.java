package com.android.timesheet.admin.project_master;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.RemoveProjectParams;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class ProjectMasterInteractor extends BaseInteractor<ProjectMasterServices> {

    public ProjectMasterInteractor(Context context) {
        super(context);
    }

    @Override
    protected ProjectMasterServices provideService() {
        return new ProjectMasterServices();
    }

    public void getAllProjectNames(ServiceCallback<ProjectNamesResponse> callback) {

        service().allProjectNames().subscribe(new ServiceSubscriber<ProjectNamesResponse>() {
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

    public void removedProj(RemoveProjectParams removeProjectParams, ServiceCallback<String> callback) {

        service().removeProject(removeProjectParams).subscribe(new ServiceSubscriber<TimeSheetResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(TimeSheetResponse recommendation) {

                if (recommendation!=null) {

                    String uniqueResult;
                    uniqueResult = recommendation.getMessage();

                    callback.onSuccess(uniqueResult);

                }
            }
        });
    }

}
