package com.android.timesheet.admin_operations.leave.approve_leave;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_entry.LeaveEntryActivity;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverviewAdapter;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.interfaces.OnItemLeaveActionsClickListener;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class ApproveLeave extends BaseActivity<ApproveLeavePresenter> implements BaseViewBehavior<List<Object>>, OnItemLeaveActionsClickListener {


    List<LeaveEntry> leaveEntryList;
    LinearLayoutManager linearLayoutManager;
    ApproveLeaveAdapter approveLeaveAdapter;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    /*@BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;*/

    /*@BindView(R.id.overViewTV)
    CustomFontTextView overViewTV;

    @BindView(R.id.leaveCalenderLL)
    LinearLayout leaveCalenderLL;*/

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected int layoutRestID() {
        return R.layout.activity_approve_leave;
    }

   /* @Override
    protected String title() {
        return "Approve Leave";
    }*/

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected ApproveLeavePresenter providePresenter() {
        return new ApproveLeavePresenter(this, this);
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

        //initCollapsingToolbar();

//        collapsingToolbarLayout.setTitle("Approve Leave");

        leaveEntryList = new ArrayList<>();
            /*long leaveEntryId,
             String fromDate,
             String toDate,
             String leaveType,
             String remarks,
             String approvedDate,
             String approvedRemarks,
             String noOfDays,
             String employeeURL,
             String employeeName,
             String appliedDate,
             String leaveStatus*/
        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "5-Jan-2018",
                Constant.CasualLeave, "Vacation", "1-Jan-2018",
                "", "5", "", "Geetha", "1-Jan-2018", Constant.LeaveApproved,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "4-Jan-2018",
                Constant.SickLeave, "Headache", "",
                "", "4", "", "Radha", "1-Jan-2018", Constant.LeaveOnHold,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "3-Jan-2018",
                Constant.EarnedLeave, "My Brothers Marriage", "1-Jan-2018",
                "", "2.5", "", "Sudha", "1-Jan-2018", Constant.LeaveApproved,
                "1:00 PM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "2-Jan-2018",
                Constant.SickLeave, "Fever", "1-Jan-2018",
                "", "2", "", "Pradha", "1-Jan-2018", Constant.LeaveRejected,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "5-Jan-2018",
                Constant.CasualLeave, "Long tour", "",
                "", "4.5", "", "Jatha", "1-Jan-2018", Constant.LeaveOnHold,
                "9:00 AM", "1:00 PM"));


        //        String fromDate, String toDate, String leaveType, String remarks, String empCode, String noOfDays
        /*leaveEntryList.add(new LeaveEntry("4-Feb-2018", "6-Feb-2018", "Casual Leave", "Village Festival", "", "3"));
        leaveEntryList.add(new LeaveEntry("17-Mar-2018", "20-Mar-2018", "Sick Leave", "Fever", "", "4"));
        leaveEntryList.add(new LeaveEntry("21-May-2018", "1-June-2018", "Earned Leave", "My Marriage", "", "10"));

        leaveEntryList.add(new LeaveEntry("1-Jan-2018", "5-Jan-2018", "Casual Leave", "My Brothers Marriage", "", "5"));
        leaveEntryList.add(new LeaveEntry("4-Feb-2018", "6-Feb-2018", "Earned Leave", "Village Festival", "", "3"));
        leaveEntryList.add(new LeaveEntry("17-Mar-2018", "20-Mar-2018", "Sick Leave", "Fever", "", "4"));
        leaveEntryList.add(new LeaveEntry("21-May-2018", "1-June-2018", "Casual Leave", "My Marriage", "", "10"));

        leaveEntryList.add(new LeaveEntry("1-Jan-2018", "5-Jan-2018", "Earned Leave", "My Brothers Marriage", "", "5"));
        leaveEntryList.add(new LeaveEntry("4-Feb-2018", "6-Feb-2018", "Casual Leave", "Village Festival", "", "3"));
        leaveEntryList.add(new LeaveEntry("17-Mar-2018", "20-Mar-2018", "Sick Leave", "Fever", "", "4"));
        leaveEntryList.add(new LeaveEntry("21-May-2018", "1-June-2018", "Earned Leave", "My Marriage", "", "10"));
*/
        approveLeaveAdapter = new ApproveLeaveAdapter(this, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        //Returning the layout file after inflating
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(approveLeaveAdapter);

        approveLeaveAdapter.setItems(leaveEntryList);

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {

        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.lb_approve_leave));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
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

    @Override
    public void onItemClick(View view, int position) {
        Gson gson = new Gson();
        String leaveEntry = gson.toJson(leaveEntryList.get(position));

        Intent leaveIntent = new Intent(this, LeaveEntryActivity.class);
        leaveIntent.putExtra(AppConfig.MY_LEAVE_OBJECT, leaveEntry);
//        leaveIntent.putExtra(AppConfig.APPROVE_LEAVE_OBJECT, leaveEntry);
        startActivity(leaveIntent);
    }

    @Override
    public void onItemClickToAccept(View view, int position) {

    }

    @Override
    public void onItemClickToReject(View view, int position) {

    }
}
