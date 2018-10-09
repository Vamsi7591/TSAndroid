package com.android.timesheet.admin_operations.leave.apply_leave.leave_overview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_entry.LeaveEntryActivity;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CircularProgressBar;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class LeaveOverview extends BaseActivity<LeaveOverviewPresenter> implements BaseViewBehavior<List<LeaveEntry>>, OnItemClickListener, Serializable {

    @BindView(R.id.my_leaves_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    CircularProgressBar progressBar;

    @BindView(R.id.my_leaves_SV)
    SearchView myLeavesSV;

    @BindView(R.id.noDataFound)
    LinearLayout noDataFound;

    @BindView(R.id.applyLeave)
    FloatingActionButton applyLeave;

    String TAG = "Leave Overview";
    List<LeaveEntry> leaveEntryList;
    LinearLayoutManager linearLayoutManager;
    LeaveOverviewAdapter leaveOverviewAdapter;

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected String title() {
        return "Leave Overview";
    }

    @Override
    protected int layoutRestID() {
        return R.layout.fragment_my_leave;
    }

    @Override
    protected LeaveOverviewPresenter providePresenter() {
        return new LeaveOverviewPresenter(this, this);
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
//        String fromDate, String toDate, String leaveType, String remarks, String empCode, String noOfDays
        leaveEntryList.add(new LeaveEntry("1-Jan-2018", "5-Jan-2018", "Casual Leave", "My Brothers Marriage", "", "5"));
        leaveEntryList.add(new LeaveEntry("4-Feb-2018", "6-Feb-2018", "Casual Leave", "Village Festival", "", "3"));
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

        leaveOverviewAdapter = new LeaveOverviewAdapter(this, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        //Returning the layout file after inflating
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(leaveOverviewAdapter);

        leaveOverviewAdapter.setItems(leaveEntryList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @OnClick(R.id.applyLeave)
    public void onClick() {
        Intent intent = new Intent(this, LeaveEntryActivity.class);
        startActivity(intent);
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

        Intent leaveIntent = new Intent(this, LeaveEntryActivity.class);
        leaveIntent.putExtra(AppConfig.MY_LEAVE_OBJECT, leaveEntry);
        startActivity(leaveIntent);
    }

    @Override
    public void onItemClickToDelete(View view, int position) {

    }
}
