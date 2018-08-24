package com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave;

import android.content.Context;

import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class MyLeavePresenter extends BasePresenter<BaseViewBehavior,MyLeaveInteractor,MyLeaveRouter> {

    public MyLeavePresenter(Context context) {
        super(context);
    }

    public MyLeavePresenter(Context context, BaseViewBehavior mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected MyLeaveInteractor provideInteractor() {
        return new MyLeaveInteractor(context);
    }

    @Override
    protected MyLeaveRouter provideRouter() {
        return new MyLeaveRouter(context);
    }
}
