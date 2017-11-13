package com.android.timesheet.user_operations.weekly;

import android.content.Context;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.Week;
import com.android.timesheet.shared.models.WeekParams;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.List;

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

    protected User getCurrentUser() {
        return interactor().currentUser();
    }


    public void fetchWeekData(WeekParams weekParams) {

        interactor().getWeekReport(weekParams, new ServiceCallback<List<Week>>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(List<Week> data) {
                viewBehaviour().onSuccess(data);
            }
        });
}

    }