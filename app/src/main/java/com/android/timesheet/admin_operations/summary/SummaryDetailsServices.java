package com.android.timesheet.admin_operations.summary;

import com.android.timesheet.shared.models.ProjectSum_Params;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by vijay 09/26/2017.
 */

public class SummaryDetailsServices extends BaseService<IAdminService> {

    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable summaryData(ProjectSum_Params projectSum_params) {

    return observe(prepare().getSummaryDetails(projectSum_params.getEmpCode(), projectSum_params.getProjectname(), projectSum_params.getYear()));

}

//    public Observable getUnAssigned(String empCode) {
//        return observe(prepare().getUnassignedProjectNames( empCode));
//    }

    public Observable getEmployees(){

        return observe(prepare().getEmployee());
    }

    public Observable getProjectNames(String empCode){
        return observe(prepare().getProjectNames(empCode));
    }

   }