package com.android.timesheet.admin_operations.project_master.edit_project;

import com.android.timesheet.shared.models.AddProjectParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by Vijay on 20.07.2017
 */

public class EditProjectServices extends BaseService<IAdminService> {
    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable updateProject(AddProjectParams addProjectParams)
     {
        return observe(prepare().updateProject(addProjectParams.projectcode,addProjectParams.projectname, addProjectParams.commonflag));
    }
}
