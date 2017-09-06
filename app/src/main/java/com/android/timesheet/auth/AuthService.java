package com.android.timesheet.auth;

import com.android.timesheet.shared.models.AuthUser;
import com.android.timesheet.shared.models.UserResponse;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAuthService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class AuthService extends BaseService<IAuthService> {

    @Override
    protected IAuthService prepare() {
        return super.prepare(IAuthService.class);
    }

    public Observable<UserResponse> login(AuthUser auth) {
        return observe(prepare().login(auth.empCode, auth.password));
    }
}
