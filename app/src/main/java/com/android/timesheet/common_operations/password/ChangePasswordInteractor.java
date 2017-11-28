package com.android.timesheet.common_operations.password;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.ChangePasswordParams;
import com.android.timesheet.shared.models.ChangePasswordPojo;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class ChangePasswordInteractor extends BaseInteractor<ChangePasswordServices> {

    public ChangePasswordInteractor(Context context) {
        super(context);
    }

    @Override
    protected ChangePasswordServices provideService() {
        return new ChangePasswordServices();
    }


    public void getChangePwd(ChangePasswordParams changePasswordParams, ServiceCallback<ChangePasswordPojo> callback) {

        service().changePwd(changePasswordParams).subscribe(new ServiceSubscriber<ChangePasswordPojo>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(ChangePasswordPojo recommendation) {
                    callback.onSuccess(recommendation);


            }
        });
    }


}

