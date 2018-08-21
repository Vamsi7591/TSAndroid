package com.android.timesheet.admin_operations.leave.apply_permission;

import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.List;

public class ApplyPermission extends BaseFragment<ApplyPermissionPresenter> implements BaseViewBehavior<List<Object>> {

    @Override
    protected int layoutResID() {
        return R.layout.activity_apply_permission;
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
