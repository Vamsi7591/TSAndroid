package com.android.timesheet.user_operations.list.headerSerialize;

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

public class HeaderSerializePresenter extends BasePresenter<BaseViewBehavior, HeaderSerializeInteractor,
        HeaderSerializeRouter> {


    public HeaderSerializePresenter(Context context) {
        super(context);
    }

    public HeaderSerializePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, (BaseViewBehavior) mViewBehavior);
    }

    @Override
    protected HeaderSerializeInteractor provideInteractor() {
        return new HeaderSerializeInteractor(context);
    }

    @Override
    protected HeaderSerializeRouter provideRouter() {
        return new HeaderSerializeRouter(context);
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
