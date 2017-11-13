package com.android.timesheet.admin_operations.employee_project;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.AssignEmpParams;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeProjectInteractor extends BaseInteractor<EmployeeProjectServices> {


    public EmployeeProjectInteractor(Context context) {
        super(context);
    }

    @Override
    protected EmployeeProjectServices provideService() {
        return new EmployeeProjectServices();
    }


    public void assignEmployee(AssignEmpParams assignEmpParams, ServiceCallback<String> callback) {

        service().getAssignEmployees(assignEmpParams).subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(AllEmployeesResponse response) {
                if (response.code == 200)
                    callback.onSuccess(response.getMessage());
                else
                    callback.onFailure(new Throwable(response.getMessage()));
            }
        });
    }


    public void removeEmployee(AssignEmpParams assignEmpParams, ServiceCallback<String> callback) {

        service().getRemoveEmployees(assignEmpParams).subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(AllEmployeesResponse response) {
                if (response.code == 200)
                    callback.onSuccess(response.getMessage());
                else
                    callback.onFailure(new Throwable(response.getMessage()));
            }
        });
    }

    public void unAssignedProj(String empCode, ServiceCallback<ProjectNamesResponse> callback) {

        service().getUnAssigned(empCode).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(ProjectNamesResponse response) {
                if (response.code == 200)
                    callback.onSuccess(response);
                else
                    callback.onFailure(new Throwable(response.getMessage()));
            }
        });
    }

    public void getProjectNames(String empCode, ServiceCallback<ProjectNamesResponse> callback) {

        service().getProjectNames(empCode).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {
                callback.onSuccess(data);
            }
        });
    }

    public void getAllEmp( ServiceCallback<AllEmployeesResponse> callback) {

        service().getAllEmployees().subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(AllEmployeesResponse data) {
                callback.onSuccess(data);
            }
        });
    }


    }



