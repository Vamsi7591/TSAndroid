package com.android.timesheet.admin.employee_master;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.RemoveEmployeeParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/28/2017.
 */
@SuppressWarnings("unchecked")
public class EmployeeMasterServices extends BaseService<IAdminService> {

    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable<AllEmployeesResponse> getEmployees() {

        return observe(prepare().getEmployee());
    }


    public Observable<AllEmployeesResponse> updateEmployee(Employee employee) {
        return observe(prepare().updateEmployee(employee.createdBy,
                employee.empCode,
                employee.password,
                employee.empRole));
    }
    public Observable removeEmployee(RemoveEmployeeParams removeEmployeeParams) {
        return observe(prepare().removeEmployee(removeEmployeeParams.getAdminempcode(),removeEmployeeParams.getEmpcode()));
    }

}
