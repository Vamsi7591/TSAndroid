package com.android.timesheet.common_operations.profile.user_profile.users;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by Vijay on 20.07.2017
 */

public class UserProfileServices  extends BaseService<IUserService> {

    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }

    public Observable<ProjectNamesResponse> getProjectNames(String empCode) {
        return observe(prepare().getProjectNames(empCode));
    }

}
