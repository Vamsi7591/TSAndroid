package com.android.timesheet.common_operations.profile.admin_profile;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class MyProfileServices extends BaseService<IUserService>{

    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }

    public Observable<ProjectNamesResponse> getProjectNames(String empCode) {
        return observe(prepare().getProjectNames(empCode));
    }

}
