package com.android.timesheet.admin.employee_project;

import android.content.Context;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.AssignEmpParams;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeProjectPresenter extends BasePresenter<BaseViewBehavior,
        EmployeeProjectInteractor, EmployeeProjectRouter> {


    public EmployeeProjectPresenter(Context context) {
        super(context);
    }

    public EmployeeProjectPresenter(Context context, BaseViewBehavior mViewBehavior) {

        super(context, mViewBehavior);
    }

    @Override
    protected EmployeeProjectInteractor provideInteractor() {
        return new EmployeeProjectInteractor(context);
    }

    @Override
    protected EmployeeProjectRouter provideRouter() {
        return new EmployeeProjectRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }


    public void assignEmp(AssignEmpParams assignEmpParams) {

        interactor().assignEmployee(assignEmpParams, new ServiceCallback<String>() {

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

    public void removeEmp(AssignEmpParams assignEmpParams) {

        interactor().removeEmployee(assignEmpParams, new ServiceCallback<String>() {

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

    public void unAssignProj(String empCode) {

        interactor().unAssignedProj(empCode, new ServiceCallback<ProjectNamesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }

    public void getProjectNames(String empCode){

        interactor().getProjectNames(empCode, new ServiceCallback<ProjectNamesResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }

    public void getAllEmpDetails(){

        interactor().getAllEmp( new ServiceCallback<AllEmployeesResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(AllEmployeesResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }

}