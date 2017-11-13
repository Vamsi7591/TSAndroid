package com.android.timesheet.user_operations.monthly;

import android.content.Context;

import com.android.timesheet.shared.models.Month;
import com.android.timesheet.shared.models.MonthParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.List;

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

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void fetchMonthData(MonthParams monthParams) {

        interactor().getMonthReport(monthParams, new ServiceCallback<List<Month>>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(List<Month> data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }

}
