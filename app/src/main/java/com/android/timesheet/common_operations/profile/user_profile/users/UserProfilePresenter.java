package com.android.timesheet.common_operations.profile.user_profile.users;

import android.content.Context;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by Vijay on 20.07.2017
 */

public class UserProfilePresenter extends BasePresenter<BaseViewBehavior, UserProfileInteractor,
        UserProfileRouter> {

    public UserProfilePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected UserProfileInteractor provideInteractor() {
        return new UserProfileInteractor(context);
    }

    @Override
    protected UserProfileRouter provideRouter() {
        return new UserProfileRouter(context);
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

    public User getCurrentUser() {
        return interactor().currentUser();
    }

    public void clearUser(){

        interactor().clearUser();

    }
}