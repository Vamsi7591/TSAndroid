package com.android.timesheet.user.weekly;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class WeeklyPresenter extends BasePresenter<BaseViewBehavior,WeeklyInteractor,WeeklyRouter> {


    public WeeklyPresenter(Context context) {
        super(context);
    }

    public WeeklyPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected WeeklyInteractor provideInteractor() {
        return new WeeklyInteractor(context);
    }

    @Override
    protected WeeklyRouter provideRouter() {
        return new WeeklyRouter(context);
    }
}
