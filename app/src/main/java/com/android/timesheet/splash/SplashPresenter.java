package com.android.timesheet.splash;

import android.content.Context;

import com.android.timesheet.auth.LoginActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class SplashPresenter extends BasePresenter<BaseViewBehavior, SplashInteractor, SplashRouter> {

    public SplashPresenter(Context context) {
        super(context);
    }

    public SplashPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected SplashInteractor provideInteractor() {
        return new SplashInteractor(context);
    }

    @Override
    protected SplashRouter provideRouter() {
        return new SplashRouter(context);
    }


    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void dispatchScreen(User user) {
        if (user != null) //.equalsIgnoreCase("Admin")
            router().openMainActivity();
        else
            router().openFreshActivity(LoginActivity.class);
    }


}
