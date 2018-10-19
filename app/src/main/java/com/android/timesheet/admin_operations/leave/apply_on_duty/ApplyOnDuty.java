package com.android.timesheet.admin_operations.leave.apply_on_duty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_entry.LeaveEntryActivity;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverviewAdapter;
import com.android.timesheet.admin_operations.leave.apply_on_duty.on_duty_entry.OnDutyEntryActivity;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CircularProgressBar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyOnDuty extends BaseActivity<ApplyOnDutyPresenter> implements BaseViewBehavior<List<LeaveEntry>>, OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    CircularProgressBar progressBar;

    @BindView(R.id.my_leaves_SV)
    SearchView myLeavesSV;

    @BindView(R.id.addEntry)
    ImageView addEntry;

    @BindView(R.id.noDataFound)
    LinearLayout noDataFound;

    String TAG = "Apply On Duty";
    List<LeaveEntry> leaveEntryList;
    LinearLayoutManager linearLayoutManager;
    LeaveOverviewAdapter leaveOverviewAdapter;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_apply_on_duty;
    }

    @Override
    protected String title() {
        return "My On Duty";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected ApplyOnDutyPresenter providePresenter() {
        return new ApplyOnDutyPresenter(this,this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        leaveEntryList = new ArrayList<>();
        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "5-Jan-2018",
                Constant.WorkOnDuty, "laptop Purchase", "1-Jan-2018",
                "", "5", "", "Geetha", "1-Jan-2018", Constant.Approved,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "4-Jan-2018",
                Constant.WorkFromVendorSide, "Tech Support", "",
                "", "4", "", "Radha", "1-Jan-2018", Constant.OnHold,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "3-Jan-2018",
                Constant.WorkFromClientSide, "KT", "1-Jan-2018",
                "", "2.5", "", "Sudha", "1-Jan-2018", Constant.Approved,
                "1:00 PM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "2-Jan-2018",
                Constant.WorkOnDuty, "Mobile Purchase", "1-Jan-2018",
                "", "2", "", "Pradha", "1-Jan-2018", Constant.Rejected,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "5-Jan-2018",
                Constant.WorkFromVendorSide, "New Change requests support", "",
                "", "4.5", "", "Jatha", "1-Jan-2018", Constant.OnHold,
                "9:00 AM", "1:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "5-Jan-2018",
                Constant.WorkFromVendorSide, "Project review", "1-Jan-2018",
                "", "5", "", "Geetha", "1-Jan-2018", Constant.Approved,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "5-Jan-2018",
                Constant.WorkOnDuty, "laptop Purchase", "",
                "", "4.5", "", "Jatha", "1-Jan-2018", Constant.OnHold,
                "9:00 AM", "1:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "4-Jan-2018",
                Constant.WorkFromClientSide, "New requirement discussion", "",
                "", "4", "", "Radha", "1-Jan-2018", Constant.OnHold,
                "9:00 AM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "3-Jan-2018",
                Constant.WorkFromVendorSide, "Review confirmation", "1-Jan-2018",
                "", "2.5", "", "Sudha", "1-Jan-2018", Constant.Approved,
                "1:00 PM", "7:00 PM"));

        leaveEntryList.add(new LeaveEntry(1313, "1-Jan-2018", "2-Jan-2018",
                Constant.WorkOnDuty, "laptop service center", "1-Jan-2018",
                "", "2", "", "Pradha", "1-Jan-2018", Constant.Rejected,
                "9:00 AM", "7:00 PM"));



        leaveOverviewAdapter = new LeaveOverviewAdapter(this, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        //Returning the layout file after inflating
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(leaveOverviewAdapter);

        leaveOverviewAdapter.setItems(leaveEntryList);
    }

    @OnClick(R.id.addEntry)
    public void onClick() {
        Intent intent = new Intent(this, OnDutyEntryActivity.class);
        startActivity(intent);
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
    public void onSuccess(List<LeaveEntry> data) {
        if (data.size() != 0) {
            this.leaveEntryList = data;

            leaveOverviewAdapter.setItems(data);
            noDataFound.setVisibility(View.GONE);
        } else
            noDataFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    public void onItemClick(View view, int position) {
        Gson gson = new Gson();
        String leaveEntry = gson.toJson(leaveEntryList.get(position));

        Intent leaveIntent = new Intent(this, OnDutyEntryActivity.class);
        leaveIntent.putExtra(AppConfig.MY_LEAVE_OBJECT, leaveEntry);
        startActivity(leaveIntent);
    }

    @Override
    public void onItemClickToDelete(View view, int position) {

    }
}
