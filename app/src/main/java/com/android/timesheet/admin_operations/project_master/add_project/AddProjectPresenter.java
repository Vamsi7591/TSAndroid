package com.android.timesheet.admin_operations.project_master.add_project;

import android.content.Context;

import com.android.timesheet.shared.models.AddProjParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddProjectPresenter extends BasePresenter<BaseViewBehavior,AddProjectInteractor,AddProjectRouter> {

    public AddProjectPresenter(Context context) {

        super(context);
    }

    public AddProjectPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected AddProjectInteractor provideInteractor() {
        return new AddProjectInteractor(context);
    }

    @Override
    protected AddProjectRouter provideRouter() {
        return new AddProjectRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void addProjEmp(AddProjParams addProjParams) {

        interactor().addProj(addProjParams, new ServiceCallback<String>() {

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
