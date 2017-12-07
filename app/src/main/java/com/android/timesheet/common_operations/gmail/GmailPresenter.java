package com.android.timesheet.common_operations.gmail;

import android.content.Context;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by Vijay on 20.07.2017
 */

public class GmailPresenter extends BasePresenter<BaseViewBehavior,
        GmailInteractor, GmailRouter> {


    public GmailPresenter(Context context) {
        super(context);
    }

    public GmailPresenter(Context context, BaseViewBehavior<Object> mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected GmailInteractor provideInteractor() {
        return new GmailInteractor(context);
    }

    @Override
    protected GmailRouter provideRouter() {
        return new GmailRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void fetchAllEmployees() {
        interactor().getEmployeesAll(new ServiceCallback<AllEmployeesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(AllEmployeesResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }
}
