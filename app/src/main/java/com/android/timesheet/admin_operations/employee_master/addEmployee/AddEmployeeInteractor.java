package com.android.timesheet.admin_operations.employee_master.addEmployee;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AddEmployeeParams;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddEmployeeInteractor extends BaseInteractor<AddEmployeeServices> {

    public AddEmployeeInteractor(Context context) {
        super(context);
    }

    @Override
    protected AddEmployeeServices provideService() {
        return new AddEmployeeServices();
    }

    public void addEmp(AddEmployeeParams addEmployeeParams, ServiceCallback<String> callback) {

        service().addEmployee(addEmployeeParams).subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(AllEmployeesResponse recommendation) {
                if (recommendation.getCode() ==200) {

                    String uniqueResult;
                    uniqueResult = recommendation.getMessage();

                    callback.onSuccess(uniqueResult);

                }
            }
        });
    }
}
