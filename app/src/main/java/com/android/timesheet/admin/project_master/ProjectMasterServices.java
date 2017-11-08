package com.android.timesheet.admin.project_master;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.RemoveProjectParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class ProjectMasterServices extends BaseService<IAdminService>{

    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable<ProjectNamesResponse> allProjectNames() {
        return observe(prepare().allProjectNames());
    }

    public Observable removeProject(RemoveProjectParams removeProjectParams) {
        return observe(prepare().removeProject(removeProjectParams.getProjectName()));
    }

}
