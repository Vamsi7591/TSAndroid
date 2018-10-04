package com.android.timesheet.admin_operations.leave.leave_request;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.LeaveActivity;
import com.android.timesheet.admin_operations.leave.LeavePresenter;
import com.android.timesheet.admin_operations.leave.apply_leave.ApplyLeave;
import com.android.timesheet.admin_operations.leave.apply_leave.tabs.leave_calender.LeaveCalender;
import com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave.popup.LeavePopUpActivity;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class LeaveRequestActivity extends BaseActivity<LeaveRequestPresenter> implements BaseViewBehavior<Object> {


    User user = new User();

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.tvEntitlement)
    CustomFontTextView tvEntitlement;

    @BindView(R.id.overViewTV)
    CustomFontTextView overViewTV;

    @BindView(R.id.entitlementLL)
    LinearLayout entitlementLL;

    @BindView(R.id.leaveRequestLL)
    LinearLayout leaveRequestLL;

    @BindView(R.id.leaveCalenderLL)
    LinearLayout leaveCalenderLL;

    @BindView(R.id.progressBarEntitlement)
    ProgressBar progressBarEntitlement;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_leave_requests;
    }

    @Override
    protected String title() {
        return "Leave Requests";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected LeaveRequestPresenter providePresenter() {
        return new LeaveRequestPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = presenter().getCurrentUser();


        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        collapsingToolbarLayout.setTitleEnabled(false);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBarEntitlement.setProgress(70, true);
        } else {
            progressBarEntitlement.setProgress(70);
        }

        tvEntitlement.setText("70%");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onFailed(Throwable e) {

    }

    PopupWindow popupWindow;

    @OnClick(R.id.entitlementLL)
    void showEntitlementPopUp() {
        //instantiate the popup.xml layout file
        LayoutInflater layoutInflater = (LayoutInflater) LeaveRequestActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.inflator_entitilement_pop_up, null);

        CustomFontTextView closePopupBtn = (CustomFontTextView) customView.findViewById(R.id.title);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(entitlementLL.getRootView(), Gravity.CENTER, 0, 0);

        //close the popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @OnClick(R.id.overViewTV)
    void navToLeaveOverview() {
        presenter().openActivity(ApplyLeave.class, "Leave Overview");
    }

    @OnClick(R.id.leaveRequestLL)
    void navToLeavePopUpActivity() {
        presenter().openActivity(LeavePopUpActivity.class);
    }

    @OnClick(R.id.leaveCalenderLL)
    void navToLeaveCalenderView() {
        presenter().openActivity(LeaveCalender.class);
    }
}
