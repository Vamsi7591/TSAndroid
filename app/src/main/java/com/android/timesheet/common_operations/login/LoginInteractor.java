package com.android.timesheet.common_operations.login;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AuthUser;
import com.android.timesheet.shared.models.UserResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class LoginInteractor extends BaseInteractor<LoginService> {

    public LoginInteractor(Context context) {
        super(context);
    }

    @Override
    protected LoginService provideService() {
        return new LoginService();
    }

    public void submitLogin(AuthUser auth, final ServiceCallback<UserResponse> callback) {

        service().login(auth).subscribe(new ServiceSubscriber<UserResponse>() {
            @Override
            public void onFailure(Throwable e) {
                //App.getInstance().removeAuthorization();
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(UserResponse userResponse) {

//                if (userResponse.status) {//userResponse.code == 200 &&
                    callback.onSuccess(userResponse);
//                }else{
//
//                }
            }
        });
    }
}
