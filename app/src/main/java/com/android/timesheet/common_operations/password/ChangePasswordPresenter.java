package com.android.timesheet.common_operations.password;

import android.content.Context;

import com.android.timesheet.shared.models.ChangePasswordParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class ChangePasswordPresenter extends BasePresenter<BaseViewBehavior,ChangePasswordInteractor ,ChangePasswordRouter>
{

    public ChangePasswordPresenter(Context context) {
        super(context);
    }

    public ChangePasswordPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected ChangePasswordInteractor provideInteractor() {

        return new ChangePasswordInteractor(context);
    }

    @Override
    protected ChangePasswordRouter provideRouter() {

        return new ChangePasswordRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void clearUser(){
        interactor().clearUser();

    }

    public void changePwd(ChangePasswordParams changePasswordParams) {

        interactor().getChangePwd(changePasswordParams, new ServiceCallback<String>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(String data) {
                viewBehaviour().onSuccess(data);
            }


        });
    }

}
