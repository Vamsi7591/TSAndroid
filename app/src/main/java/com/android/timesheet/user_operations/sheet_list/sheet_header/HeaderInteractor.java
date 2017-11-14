package com.android.timesheet.user_operations.sheet_list.sheet_header;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.HeaderParams;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vijay on 20.07.2017
 */

public class HeaderInteractor extends BaseInteractor<HeaderServices> {

    public HeaderInteractor(Context context) {
        super(context);
    }

    @Override
    protected HeaderServices provideService() {
        return new HeaderServices();
    }

    public void getDayTimeSheet(HeaderParams headerParams, ServiceCallback<TimeSheetResponse> callback) {

        service().getDayRep(headerParams).subscribe(new ServiceSubscriber<TimeSheetResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(TimeSheetResponse recommendation) {

                List<TimeSheet> uniqueResult = new ArrayList<>();

                if (recommendation.code == 200)
                    uniqueResult = recommendation.getTimeSheetList();

                callback.onSuccess(recommendation);
            }
        });
    }

}
