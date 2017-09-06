package com.android.timesheet.admin.project_master;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.ProjectNamesResponse;
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
}
