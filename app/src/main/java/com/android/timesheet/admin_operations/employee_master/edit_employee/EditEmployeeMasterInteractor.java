package com.android.timesheet.admin_operations.employee_master.edit_employee;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.UpdateEmployeeParams;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by wks-p02-012 on 9/20/2017.
 */

public class EditEmployeeMasterInteractor extends BaseInteractor<EditEmployeeMasterServices> {

    public EditEmployeeMasterInteractor(Context context) {
        super(context);
    }

    @Override
    protected EditEmployeeMasterServices provideService() {
        return new EditEmployeeMasterServices();
    }


    public void update(UpdateEmployeeParams updateEmployeeParams, ServiceCallback<String> callback) {

        service().updateEMP(updateEmployeeParams).subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

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
