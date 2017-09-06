package com.android.timesheet.main;

import android.content.Context;

import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class MainPresenter extends BasePresenter<BaseViewBehavior, MainInteractor, MainRouter> {

    public MainPresenter(Context context) {
        super(context);
    }

    public MainPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected MainInteractor provideInteractor() {
        return new MainInteractor(context);
    }

    @Override
    protected MainRouter provideRouter() {
        return new MainRouter(context);
    }


    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openTimeSheet() {
        router().openTimeSheet();
    }

}
