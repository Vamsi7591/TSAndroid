package com.android.timesheet.common_operations.gmail;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IAdminService;

import rx.Observable;

/**
 * Created by Vijay on 20.07.2017
 */

public class GmailServices extends BaseService<IAdminService> {

    @Override
    protected IAdminService prepare() {
        return super.prepare(IAdminService.class);
    }

    public Observable<AllEmployeesResponse> getAllEmployees() {

        return observe(prepare().getEmployee());
    }
}