package com.android.timesheet.admin_operations.employee_master.edit_employee;

import com.android.timesheet.shared.models.UpdateEmployeeParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by Vijay on 20.07.2017.
 */

public class EditEmployeeMasterServices extends BaseService<IAdminService> {
    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable updateEMP(UpdateEmployeeParams updateEmployeeParams) {
        return observe(prepare().updateEmployee
                (updateEmployeeParams.getAdminempcode(),updateEmployeeParams.getEmpcode(),updateEmployeeParams.getPassword(),updateEmployeeParams.getEmprole()));
    }
}
