package com.android.timesheet.common_operations.profile.admin_profile;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class MyProfileInteractor extends BaseInteractor<MyProfileServices>{

    public MyProfileInteractor(Context context) {
        super(context);
    }

    @Override
    protected MyProfileServices provideService() {
        return new MyProfileServices();
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
