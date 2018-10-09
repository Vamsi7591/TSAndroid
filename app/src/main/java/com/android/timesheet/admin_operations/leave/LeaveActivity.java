package com.android.timesheet.admin_operations.leave;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverview;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_request.LeaveRequestActivity;
import com.android.timesheet.admin_operations.leave.approve_leave.ApproveLeave;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

public class LeaveActivity extends BaseActivity<LeavePresenter> {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.gridView)
    GridView gridView;

    User user = new User();

    private ArrayList<String> strings;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_leave;
    }

    @Override
    protected String title() {
        return "Leave";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected LeavePresenter providePresenter() {
        return new LeavePresenter(this);
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

        loadGrid();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (user.getEmpRole().equalsIgnoreCase("Admin"))
                    switch (position) {
                        case 0:
                            presenter().openActivity(LeaveRequestActivity.class);
                            break;

                        case 1:
                            presenter().openActivity(ApproveLeave.class);
                            break;

                        case 2:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 3:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 4:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 5:
                            presenter().openActivity(LeaveOverview.class);
                            break;
                    }
                else
                    switch (position) {
                        case 0:
                            presenter().openActivity(LeaveRequestActivity.class);
                            break;

                        case 1:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 2:
                            presenter().openActivity(LeaveOverview.class);
                            break;
                    }
            }
        });

    }

    private void loadGrid() {

        strings = new ArrayList<>();
        if (user.getEmpRole().equalsIgnoreCase("Admin")) {
            strings.add("Apply Leave");//Leave Requests
            strings.add("Approve Leave");
            strings.add("Apply On Duty");
            strings.add("Approve On Duty");
            strings.add("Apply Permission");
            strings.add("Approve Permission");
        } else {
            strings.add("Apply Leave");//
            strings.add("Apply On Duty");
            strings.add("Apply Permission");
        }

        LeaveAdapter booksAdapter = new LeaveAdapter(this, strings);
        gridView.setAdapter(booksAdapter);
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
}
