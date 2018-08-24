package com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.MyLeaveAdapter;
import com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave.popup.LeavePopUpActivity;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.models.Week;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CircularProgressBar;
import com.android.timesheet.user_operations.reports.weekly.WeeklyPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyLeave extends BaseFragment<MyLeavePresenter> implements BaseViewBehavior<List<LeaveEntry>>, OnItemClickListener, Serializable {

    @BindView(R.id.my_leaves_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    CircularProgressBar progressBar;

    @BindView(R.id.my_leaves_SV)
    SearchView my_leaves_SV;

    @BindView(R.id.noDataFound)
    LinearLayout noDataFound;

    @BindView(R.id.applyLeave)
    FloatingActionButton applyLeave;

    String TAG = "MyLeaveFragment";
    List<LeaveEntry> leaveEntryList;
    LinearLayoutManager linearLayoutManager;
    MyLeaveAdapter myLeaveAdapter;

    @Override
    protected int layoutResID() {
        return R.layout.fragment_my_leave;
    }

    @Override
    protected MyLeavePresenter providePresenter() {
        return new MyLeavePresenter(getActivity(), this);
    }

    //Overridden method onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(false);

        leaveEntryList = new ArrayList<>();
//        String fromDate, String toDate, String leaveType, String remarks, String empCode, String noOfDays
        leaveEntryList.add(new LeaveEntry("1-Jan-2018", "5-Jan-2018", "Annual Leave", "My Brothers Marriage" , "", "5" ));
        leaveEntryList.add(new LeaveEntry("4-Feb-2018", "6-Feb-2018", "Annual Leave", "Village Festival" , "", "3" ));
        leaveEntryList.add(new LeaveEntry("17-Mar-2018", "20-Mar-2018", "Medical Leave", "Fever" , "", "4" ));
        leaveEntryList.add(new LeaveEntry("21-May-2018", "1-June-2018", "Annual Leave", "My Marriage" , "", "10" ));

        leaveEntryList.add(new LeaveEntry("1-Jan-2018", "5-Jan-2018", "Annual Leave", "My Brothers Marriage" , "", "5" ));
        leaveEntryList.add(new LeaveEntry("4-Feb-2018", "6-Feb-2018", "Annual Leave", "Village Festival" , "", "3" ));
        leaveEntryList.add(new LeaveEntry("17-Mar-2018", "20-Mar-2018", "Medical Leave", "Fever" , "", "4" ));
        leaveEntryList.add(new LeaveEntry("21-May-2018", "1-June-2018", "Annual Leave", "My Marriage" , "", "10" ));

        leaveEntryList.add(new LeaveEntry("1-Jan-2018", "5-Jan-2018", "Annual Leave", "My Brothers Marriage" , "", "5" ));
        leaveEntryList.add(new LeaveEntry("4-Feb-2018", "6-Feb-2018", "Annual Leave", "Village Festival" , "", "3" ));
        leaveEntryList.add(new LeaveEntry("17-Mar-2018", "20-Mar-2018", "Medical Leave", "Fever" , "", "4" ));
        leaveEntryList.add(new LeaveEntry("21-May-2018", "1-June-2018", "Annual Leave", "My Marriage" , "", "10" ));

        myLeaveAdapter = new MyLeaveAdapter(getActivity(), this);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        //Returning the layout file after inflating
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myLeaveAdapter);

        myLeaveAdapter.setItems(leaveEntryList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @OnClick(R.id.applyLeave)
    public void onClick() {
        Intent intent = new Intent(getActivity(), LeavePopUpActivity.class);
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

    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemClickToDelete(View view, int position) {

    }
}
