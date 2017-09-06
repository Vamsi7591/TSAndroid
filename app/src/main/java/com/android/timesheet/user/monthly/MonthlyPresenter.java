package com.android.timesheet.user.monthly;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class MonthlyPresenter extends BasePresenter<BaseViewBehavior,MonthlyInteractor,MonthlyRouter>{

    public MonthlyPresenter(Context context) {
        super(context);
    }

    public MonthlyPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected MonthlyInteractor provideInteractor() {
        return new MonthlyInteractor(context);
    }

    @Override
    protected MonthlyRouter provideRouter() {
        return new MonthlyRouter(context);
    }
}
