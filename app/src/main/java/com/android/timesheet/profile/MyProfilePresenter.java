package com.android.timesheet.profile;

import android.content.Context;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class MyProfilePresenter extends BasePresenter<BaseViewBehavior, MyProfileInteractor, MyProfileRouter> {

    public MyProfilePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected MyProfileInteractor provideInteractor() {
        return new MyProfileInteractor(context);
    }

    @Override
    protected MyProfileRouter provideRouter() {
        return new MyProfileRouter(context);
    }


    public void getProjectNames(String empCode) {
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

    public User getCurrentUser(){
        return interactor().currentUser();
    }
}
