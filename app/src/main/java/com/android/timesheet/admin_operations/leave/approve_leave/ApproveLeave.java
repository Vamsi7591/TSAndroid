package com.android.timesheet.admin_operations.leave.approve_leave;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class ApproveLeave extends BaseActivity<ApproveLeavePresenter> implements BaseViewBehavior<List<Object>> {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.overViewTV)
    CustomFontTextView overViewTV;

    @BindView(R.id.leaveCalenderLL)
    LinearLayout leaveCalenderLL;


    @Override
    protected int layoutRestID() {
        return R.layout.activity_approve_leave;
    }

    @Override
    protected String title() {
        return "Approve Leave";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected ApproveLeavePresenter providePresenter() {
        return new ApproveLeavePresenter(this,this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        collapsingToolbarLayout.setTitleEnabled(false);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
