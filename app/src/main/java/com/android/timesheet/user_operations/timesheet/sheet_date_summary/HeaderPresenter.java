package com.android.timesheet.user_operations.timesheet.sheet_date_summary;

import android.content.Context;

import com.android.timesheet.shared.models.HeaderParams;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by Vijay on 20.07.2017
 */

public class HeaderPresenter extends BasePresenter<BaseViewBehavior, HeaderInteractor,
        HeaderRouter> {


    public HeaderPresenter(Context context) {
        super(context);
    }

    public HeaderPresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, (BaseViewBehavior) mViewBehavior);
    }

    @Override
    protected HeaderInteractor provideInteractor() {
        return new HeaderInteractor(context);
    }

    @Override
    protected HeaderRouter provideRouter() {
        return new HeaderRouter(context);
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }



    public void dayTimeSheet(HeaderParams headerParams) {

        interactor().getDayTimeSheet(headerParams, new ServiceCallback<TimeSheetResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);


            }

            @Override
            public void onSuccess( TimeSheetResponse data) {

                viewBehaviour().onSuccess(data);

            }
        });
    }
}
