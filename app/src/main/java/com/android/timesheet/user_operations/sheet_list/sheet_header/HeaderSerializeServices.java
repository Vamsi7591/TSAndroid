package com.android.timesheet.user_operations.sheet_list.sheet_header;

import com.android.timesheet.shared.models.HeaderParams;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.services.rest.IUserService;

import rx.Observable;

/**
 * Created by Vijay on 20.07.2017
 */

public class HeaderSerializeServices extends BaseService<IUserService> {

    @Override
    protected IUserService prepare() {
        return super.prepare(IUserService.class);
    }


    Observable getDayRep(HeaderParams headerParams) {
        return observe(prepare().getDayReport(headerParams.empcode, headerParams.getDate()));
    }
}
