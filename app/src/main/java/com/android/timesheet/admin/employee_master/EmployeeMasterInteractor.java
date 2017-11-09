package com.android.timesheet.admin.employee_master;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.RemoveEmployeeParams;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeMasterInteractor extends BaseInteractor<EmployeeMasterServices> {

    public EmployeeMasterInteractor(Context context) {
        super(context);
    }

    @Override
    protected EmployeeMasterServices provideService() {
        return new EmployeeMasterServices();
    }

    public void getEmployees(ServiceCallback<AllEmployeesResponse> callback) {

        service().getEmployees().subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(AllEmployeesResponse recommendation) {

//                List<Employee> uniqueResult = new ArrayList<>();

                if (recommendation.code == 200)
//                    uniqueResult = recommendation.getEmployeeList();
                    callback.onSuccess(recommendation);
                else
                    callback.onSuccess(null);
            }
        });
    }

    public void removedEmp(RemoveEmployeeParams removeEmployeeParams, ServiceCallback<String> callback) {

        service().removeEmployee(removeEmployeeParams).subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(AllEmployeesResponse recommendation) {

                if (recommendation != null) {

                    String uniqueResult;
                    uniqueResult = recommendation.getMessage();

                    callback.onSuccess(uniqueResult);

                }
            }
        });
    }

}
