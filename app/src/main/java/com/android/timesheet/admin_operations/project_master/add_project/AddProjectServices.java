package com.android.timesheet.admin_operations.project_master.add_project;

import com.android.timesheet.shared.models.AddProjParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddProjectServices extends BaseService<IAdminService> {

    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable addProject(AddProjParams addProjParams)
    {
        return observe(prepare().addProject(addProjParams.projectname, addProjParams.commonflag));
    }

}
