package com.android.timesheet.admin_operations.employee_master.list_employee;

import android.content.Context;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.RemoveEmployeeParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeMasterPresenter extends BasePresenter<BaseViewBehavior,
        EmployeeMasterInteractor, EmployeeMasterRouter> {

    public EmployeeMasterPresenter(Context context) {
        super(context);
    }

    public EmployeeMasterPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected EmployeeMasterInteractor provideInteractor() {
        return new EmployeeMasterInteractor(context);
    }

    @Override
    protected EmployeeMasterRouter provideRouter() {
        return new EmployeeMasterRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void fetchEmployees() {
        interactor().getEmployees(new ServiceCallback<AllEmployeesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(AllEmployeesResponse data) {

                    viewBehaviour().onSuccess(data.getEmployeeList());

            }
        });
    }


    public void removeEmp(RemoveEmployeeParams removeEmployeeParams) {

        interactor().removedEmp(removeEmployeeParams, new ServiceCallback<String>() {

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
