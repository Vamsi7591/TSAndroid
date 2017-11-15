package com.android.timesheet.common_operations.landing;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.services.BaseService;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class LandingInteractor extends BaseInteractor<BaseService> {

    public LandingInteractor(Context context) {
        super(context);
    }

    @Override
    protected BaseService provideService() {
        return null;
    }
}
