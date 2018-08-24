package com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave.popup;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.ILeaveService;

import rx.Observable;

public class LeavePopUpService extends BaseService<ILeaveService> {

    @Override
    protected ILeaveService prepare() {
        return super.prepare(ILeaveService.class);
    }

    public Observable<ProjectNamesResponse> applyLeave(String s) {
        return observe(prepare().applyLeave(s));
    }

}
