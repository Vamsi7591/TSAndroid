package com.android.timesheet.user.sheet;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetPresenter extends BasePresenter<BaseViewBehavior,TimeSheetInteractor,TimeSheetRouter>{

    public TimeSheetPresenter(Context context) {
        super(context);
    }

    public TimeSheetPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected TimeSheetInteractor provideInteractor() {
        return new TimeSheetInteractor(context);
    }

    @Override
    protected TimeSheetRouter provideRouter() {
        return new TimeSheetRouter(context);
    }
}
