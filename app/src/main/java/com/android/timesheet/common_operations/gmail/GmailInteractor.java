package com.android.timesheet.common_operations.gmail;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by Vijay on 20.07.2017
 */

public class GmailInteractor extends BaseInteractor<GmailServices> {

    public GmailInteractor(Context context) {
        super(context);
    }

    @Override
    protected GmailServices provideService() {
        return new GmailServices();
    }

    public void getEmployeesAll(ServiceCallback<AllEmployeesResponse> callback) {

        service().getAllEmployees().subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(AllEmployeesResponse recommendation) {

//                List<Employee> uniqueResult = new ArrayList<>();

                if (recommendation.code == 200)
//                    uniqueResult = recommendation.getEmployeeList();
                    callback.onSuccess(recommendation);
                else
                    callback.onSuccess(null);
            }
        });
    }
}
