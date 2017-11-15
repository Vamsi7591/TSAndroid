package com.android.timesheet.common_operations.landing;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class LandingPresenter extends BasePresenter<BaseViewBehavior, LandingInteractor, LandingRouter> {

    public LandingPresenter(Context context) {
        super(context);
    }

    public LandingPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected LandingInteractor provideInteractor() {
        return new LandingInteractor(context);
    }

    @Override
    protected LandingRouter provideRouter() {
        return new LandingRouter(context);
    }


    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openTimeSheet() {
        router().openTimeSheet();
    }

}
