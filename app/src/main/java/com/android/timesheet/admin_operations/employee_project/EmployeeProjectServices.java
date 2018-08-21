package com.android.timesheet.admin_operations.employee_project;

import com.android.timesheet.shared.models.AssignEmpParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeProjectServices extends BaseService<IAdminService> {

    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable getAssignEmployees(AssignEmpParams assignEmpParams) {
        return observe(prepare().assignEmployeeToProject(assignEmpParams.getEmpcode(),assignEmpParams.getProjectname()));
    }

    public Observable getRemoveEmployees(AssignEmpParams assignEmpParams) {
        return observe(prepare().removeEmployeeFromProject(assignEmpParams.getEmpcode(),assignEmpParams.getProjectname()));
    }

    public Observable getUnAssigned(String empCode) {
        return observe(prepare().getUnassignedProjectNames( empCode));
    }

    public Observable getProjectNames(String empCode){
        return observe(prepare().getProjectNames(empCode));
    }

    public Observable getAllEmployees(){
        return observe(prepare().getEmployee());
    }

}
