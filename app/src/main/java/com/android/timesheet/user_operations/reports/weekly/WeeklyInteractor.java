package com.android.timesheet.user_operations.reports.weekly;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.Week;
import com.android.timesheet.shared.models.WeekParams;
import com.android.timesheet.shared.models.WeekResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class WeeklyInteractor extends BaseInteractor<WeeklyService> {


    public WeeklyInteractor(Context context) {
        super(context);
    }

    protected WeeklyService provideService() {
        return new WeeklyService();
    }


    public void getWeekReport(WeekParams weekParams, ServiceCallback<List<Week>> callback) {

        service().getWeekReport(weekParams).subscribe(new ServiceSubscriber<WeekResponse>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(WeekResponse recommendation) {

                List<Week> uniqueResult = new ArrayList<>();

                if (recommendation.code == 200)
                    uniqueResult = recommendation.getWeekSheet();

                callback.onSuccess(uniqueResult);
            }
        });
    }

}

