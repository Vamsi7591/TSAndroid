package com.android.timesheet.admin_operations.leave.apply_permission;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_on_duty.ApplyOnDutyPresenter;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.List;

public class ApplyPermission extends BaseActivity<ApplyPermissionPresenter> implements BaseViewBehavior<List<Object>> {


    @Override
    protected int layoutRestID() {
        return R.layout.activity_apply_permission;
    }

    @Override
    protected String title() {
        return "Apply Permission";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected ApplyPermissionPresenter providePresenter() {
        return new ApplyPermissionPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(List<Object> data) {

    }

    @Override
    public void onFailed(Throwable e) {

    }
}
