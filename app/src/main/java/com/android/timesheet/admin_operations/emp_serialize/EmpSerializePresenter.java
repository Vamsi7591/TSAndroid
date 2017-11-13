package com.android.timesheet.admin_operations.emp_serialize;

import android.content.Context;

import com.android.timesheet.shared.models.UpdateEmployeeParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by wks-p02-012 on 9/20/2017.
 */

public class EmpSerializePresenter extends BasePresenter<BaseViewBehavior,
        EmpSrializeInteractor, EmpSerializeRouter> {

    public EmpSerializePresenter(Context context) {
        super(context);
    }

    public EmpSerializePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected EmpSrializeInteractor provideInteractor() {
        return new EmpSrializeInteractor(context);
    }

    @Override
    protected EmpSerializeRouter provideRouter() {
        return new EmpSerializeRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }


    public void updateEmp(UpdateEmployeeParams updateEmployeeParams) {

        interactor().update(updateEmployeeParams, new ServiceCallback<String>() {

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
