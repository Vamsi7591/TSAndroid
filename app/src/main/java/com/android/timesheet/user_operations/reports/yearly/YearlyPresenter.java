package com.android.timesheet.user_operations.reports.yearly;

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
 * Created by Vijay on 20.07.2017
 */

public class YearlyPresenter extends BasePresenter<BaseViewBehavior,YearlyInteractor
        ,YearlyRouter> {

    public YearlyPresenter(Context context) {
        super(context);
    }

    public YearlyPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected YearlyInteractor provideInteractor() {
        return new YearlyInteractor(context);
    }

    @Override
    protected YearlyRouter provideRouter() {
        return new YearlyRouter(context);
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
//            public void onSuccess(List<ProjectSum_Response> listOfSummary) {
//                viewBehaviour().onSuccess(listOfSummary);
//            }
        });
    }

    public void fetchEmployees() {
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
