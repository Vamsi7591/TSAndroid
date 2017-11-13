package com.android.timesheet.admin_operations.employee_master.addEmployee;

import android.content.Context;

import com.android.timesheet.shared.models.AddEmployeeParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddEmployeePresenter extends BasePresenter
        <BaseViewBehavior,AddEmployeeInteractor ,AddEmployeeRouter> {

    public AddEmployeePresenter(Context context) {
        super(context);
    }

    public AddEmployeePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }
    @Override
    protected AddEmployeeInteractor provideInteractor() {

        return new AddEmployeeInteractor(context);
    }

    @Override
    protected AddEmployeeRouter provideRouter() {

        return new AddEmployeeRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void updateEmp(AddEmployeeParams addEmployeeParams) {

        interactor().addEmp(addEmployeeParams, new ServiceCallback<String>() {

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
