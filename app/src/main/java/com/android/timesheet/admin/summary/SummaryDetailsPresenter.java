package com.android.timesheet.admin.summary;

import android.content.Context;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.ProjectSum_Params;
import com.android.timesheet.shared.models.ProjectSum_Response;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class SummaryDetailsPresenter extends BasePresenter<BaseViewBehavior,SummaryDetailsInteractor
        ,SummaryDetailsRouter> {

    public SummaryDetailsPresenter(Context context) {
        super(context);
    }

    public SummaryDetailsPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected SummaryDetailsInteractor provideInteractor() {
        return new SummaryDetailsInteractor(context);
    }

    @Override
    protected SummaryDetailsRouter provideRouter() {
        return new SummaryDetailsRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();

    }



    public void getProjectNames(String empCode) {

        interactor().getProjectNames(empCode, new ServiceCallback<ProjectNamesResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(ProjectNamesResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }

    public void fetchSummaryData(ProjectSum_Params projectSum_params) {

        interactor().getbarData(projectSum_params, new ServiceCallback<ProjectSum_Response>() {

            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(ProjectSum_Response data) {
                viewBehaviour().onSuccess(data);
            }

//            @Override
//            public void onSuccess(List<ProjectSum_Response> data) {
//                viewBehaviour().onSuccess(data);
//            }
        });
    }

    public void fetchEmployees(){

        interactor().getEmployees( new ServiceCallback<AllEmployeesResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
                viewBehaviour().onComplete();
            }

            @Override
            public void onSuccess(AllEmployeesResponse data) {
                viewBehaviour().onSuccess(data);
            }
        });
    }

    }
