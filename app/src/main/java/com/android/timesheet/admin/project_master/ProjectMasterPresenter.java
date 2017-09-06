package com.android.timesheet.admin.project_master;

import android.content.Context;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class ProjectMasterPresenter extends BasePresenter<BaseViewBehavior,ProjectMasterInteractor,ProjectMasterRouter>{

    public ProjectMasterPresenter(Context context) {
        super(context);
    }

    public ProjectMasterPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ProjectMasterInteractor provideInteractor() {
        return new ProjectMasterInteractor(context);
    }

    @Override
    protected ProjectMasterRouter provideRouter() {
        return new ProjectMasterRouter(context);
    }

    public void getAllProjectNames(){
        interactor().getAllProjectNames(new ServiceCallback<ProjectNamesResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {

                viewBehaviour().onSuccess(data.getProjectList());
            }
        });
    }
}
