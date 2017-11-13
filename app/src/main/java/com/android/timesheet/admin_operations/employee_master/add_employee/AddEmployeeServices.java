package com.android.timesheet.admin_operations.employee_master.add_employee;

import com.android.timesheet.shared.models.AddEmployeeParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddEmployeeServices extends BaseService<IAdminService> {

    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    /* addEmployee method for collect employee detils using  AddEmployeeParams Class

    */
    public Observable addEmployee(AddEmployeeParams addEmployeeParams) {
        return observe(prepare().addEmployee(
                addEmployeeParams.getAdminempcode(),
                addEmployeeParams.getEmpName(),
                addEmployeeParams.getEmpemailid(),
                addEmployeeParams.getPassword(),
                String.valueOf(addEmployeeParams.getEmprole())));
    }
}
