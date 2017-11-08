package com.android.timesheet.admin.project_master.addProject;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AddProjParams;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddProjectInteractor extends BaseInteractor<AddProjectServices> {

    public AddProjectInteractor(Context context) {
        super(context);
    }

    @Override
    protected AddProjectServices provideService() {
        return new AddProjectServices();
    }

    public void addProj(AddProjParams addProjParams, ServiceCallback<String> callback) {

        service().addProject(addProjParams).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {

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
