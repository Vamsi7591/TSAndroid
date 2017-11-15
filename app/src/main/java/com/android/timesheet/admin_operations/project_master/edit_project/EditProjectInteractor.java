package com.android.timesheet.admin_operations.project_master.edit_project;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AddProjectParams;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by Vijay on 20.07.2017
 */

public class EditProjectInteractor extends BaseInteractor<EditProjectServices> {

    public EditProjectInteractor(Context context) {
        super(context);
    }

    @Override
    protected EditProjectServices provideService() {
        return new EditProjectServices();
    }

    public void updProj(AddProjectParams addProjectParams, ServiceCallback<String> callback) {

        service().updateProject(addProjectParams).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(ProjectNamesResponse recommendation) {
                if (recommendation.getCode()==200) {

                    String uniqueResult;
                    uniqueResult = recommendation.getMessage();

                    callback.onSuccess(uniqueResult);

                }
            }
        });
    }

}
