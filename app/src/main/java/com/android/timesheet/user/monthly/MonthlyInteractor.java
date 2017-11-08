package com.android.timesheet.user.monthly;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.Month;
import com.android.timesheet.shared.models.MonthParams;
import com.android.timesheet.shared.models.MonthResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class MonthlyInteractor extends BaseInteractor<MonthlyService> {

    public MonthlyInteractor(Context context) {
        super(context);
    }

    @Override
    protected MonthlyService provideService() {
        return new MonthlyService();
    }

    public void getMonthReport(MonthParams monthParams, ServiceCallback<List<Month>> callback) {

        service().getMonthReport(monthParams).subscribe(new ServiceSubscriber<MonthResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(MonthResponse recommendation) {

                List<Month> uniqueResult = new ArrayList<>();

                if (recommendation.getCode() == 200)
                    uniqueResult = recommendation.getData();

                callback.onSuccess(uniqueResult);
            }
        });
    }

}
