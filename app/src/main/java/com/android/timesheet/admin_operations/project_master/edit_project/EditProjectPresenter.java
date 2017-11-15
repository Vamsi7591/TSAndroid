package com.android.timesheet.admin_operations.project_master.edit_project;

import android.content.Context;

import com.android.timesheet.shared.models.AddProjectParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by Vijay on 20.07.2017
 */

public class EditProjectPresenter extends BasePresenter<BaseViewBehavior,EditProjectInteractor,EditProjectRouter> {

    public EditProjectPresenter(Context context) {

        super(context);
    }

    public EditProjectPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected EditProjectInteractor provideInteractor() {
        return new EditProjectInteractor(context);
    }

    @Override
    protected EditProjectRouter provideRouter() {
        return new EditProjectRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void updateEmp(AddProjectParams addProjectParams) {

        interactor().updProj(addProjectParams, new ServiceCallback<String>() {

            @Override
            public void onSuccess(String data) {
                viewBehaviour().onSuccess(data);
            }

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }


        });
    }

}
