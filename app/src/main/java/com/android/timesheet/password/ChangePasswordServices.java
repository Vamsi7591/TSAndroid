package com.android.timesheet.password;

import com.android.timesheet.shared.models.ChangePasswordParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class ChangePasswordServices extends BaseService<IUserService> {
    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }

    public Observable changePwd(ChangePasswordParams changePasswordParams) {
        return observe(prepare().changePassword(changePasswordParams.getEmpcode(),changePasswordParams.getOldpwd(),changePasswordParams.getNewpwd()));
    }

}
