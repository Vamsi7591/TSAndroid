package com.android.timesheet.admin.employee_master;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AllEmployeesResponse;
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

                /*List<Employee> uniqueResult = new ArrayList<>();

                if (recommendation.code == 200)
                    uniqueResult = recommendation.getEmployeeList();


                callback.onSuccess(uniqueResult);*/
                callback.onSuccess(recommendation);
            }
        });
    }
}
