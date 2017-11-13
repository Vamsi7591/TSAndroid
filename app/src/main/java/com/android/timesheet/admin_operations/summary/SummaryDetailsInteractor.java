package com.android.timesheet.admin_operations.summary;

import android.content.Context;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.ProjectSum_Params;
import com.android.timesheet.shared.models.ProjectSum_Response;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.services.ServiceSubscriber;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class SummaryDetailsInteractor extends BaseInteractor<SummaryDetailsServices> {

    public SummaryDetailsInteractor(Context context) {
        super(context);
    }

    @Override
    protected SummaryDetailsServices provideService() {
        return new SummaryDetailsServices();
    }

    public void getbarData(ProjectSum_Params projectSum_params, ServiceCallback<ProjectSum_Response> callback) {

        service().summaryData(projectSum_params).subscribe(new ServiceSubscriber<ProjectSum_Response>() {

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override

            public void onSuccess(ProjectSum_Response recommendation) {

//                List<ProjectSummary> uniqueResult = new ArrayList<>();

                if (recommendation.getCode() == 500 ) {

                    callback.onFailure(recommendation );
                }

                else
                callback.onSuccess(recommendation);
            }
        });
    }


    public void getEmployees( ServiceCallback<AllEmployeesResponse> callback) {

        service().getEmployees().subscribe(new ServiceSubscriber<AllEmployeesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(AllEmployeesResponse data) {
                callback.onSuccess(data);
            }
        });
    }

//    public void unAssignedProj(String empCode, ServiceCallback<ProjectNamesResponse> callback) {
//
//        service().getUnAssigned(empCode).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {
//
//            @Override
//            public void onDisconnected() {
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                callback.onFailure(e);
//            }
//
//            @Override
//            public void onSuccess(ProjectNamesResponse response) {
//                if (response.code == 200)
//                    callback.onSuccess(response);
//                else
//                    callback.onFailure(new Throwable(response.getMessage()));
//            }
//        });
//    }
    public void getProjectNames(String empCode, ServiceCallback<ProjectNamesResponse> callback) {

        service().getProjectNames(empCode).subscribe(new ServiceSubscriber<ProjectNamesResponse>() {

            @Override
            public void onFailure(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {
                callback.onSuccess(data);
            }
        });
    }

}
