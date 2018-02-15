package com.android.timesheet.common_operations.profile.user_profile.users;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by Vijay on 20.07.2017
 */

public class UserProfileInteractor extends BaseInteractor<UserProfileServices> {

    public UserProfileInteractor(Context context) {
        super(context);
    }

    @Override
    protected UserProfileServices provideService() {
        return new UserProfileServices();
    }

    public void getProjectNames(String empCode,ServiceCallback<ProjectNamesResponse> callback) {

        service().getProjectNames(empCode).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {
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
