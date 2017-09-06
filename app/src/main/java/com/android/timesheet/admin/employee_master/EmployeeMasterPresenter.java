package com.android.timesheet.admin.employee_master;

import android.content.Context;
import android.util.Log;

import com.android.timesheet.auth.AuthPresenter;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.List;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeMasterPresenter extends BasePresenter<BaseViewBehavior, EmployeeMasterInteractor, EmployeeMasterRouter> {

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

    public void fetchEmployees() {
        interactor().getEmployees(new ServiceCallback<AllEmployeesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(AllEmployeesResponse data) {

//                if (data.size() != 0)

                    viewBehaviour().onSuccess(data.getEmployeeList());

            }
        });
    }
}
