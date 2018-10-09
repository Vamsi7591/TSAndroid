package com.android.timesheet.admin_operations.holiday_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverviewAdapter;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.HolidayModel;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class HolidaysList extends BaseActivity<HolidaysPresenter> implements BaseViewBehavior<List<HolidayModel>>, OnItemClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    CircularProgressBar progressBar;


    List<HolidayModel> holidayModelList;
    LinearLayoutManager linearLayoutManager;
    HolidaysListAdapter holidaysListAdapter;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_holiday_list;
    }

    @Override
    protected String title() {
        return "Holiday Calendar";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected HolidaysPresenter providePresenter() {
        return new HolidaysPresenter(this, this);
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


        holidayModelList = new ArrayList<>();
        holidayModelList.add(new HolidayModel("January"));
        holidayModelList.add(new HolidayModel("Happy New Year", "01-01-2018", false, ""));
        holidayModelList.add(new HolidayModel("Pongal", "14-01-2019", true, ""));
        holidayModelList.add(new HolidayModel("Thiruvalluvar Day", "15-01-2019", false, ""));
        holidayModelList.add(new HolidayModel("Republic Day", "26-01-2019", false, ""));

        holidayModelList.add(new HolidayModel("April"));
        holidayModelList.add(new HolidayModel("Tamil New Year", "04-04-2018", false, ""));

        holidayModelList.add(new HolidayModel("May"));
        holidayModelList.add(new HolidayModel("Labour Year", "01-05-2018", false, ""));

        holidayModelList.add(new HolidayModel("June"));
        holidayModelList.add(new HolidayModel("Ramzan", "15-06-2018", true, ""));

        holidayModelList.add(new HolidayModel("August"));
        holidayModelList.add(new HolidayModel("Independence Day", "15-08-2018", false, ""));
        holidayModelList.add(new HolidayModel("Bakrid", "22-08-2018", true, ""));

        holidayModelList.add(new HolidayModel("September"));
        holidayModelList.add(new HolidayModel("Vinayaka Chaturthi", "13-09-2018", true, ""));

        holidayModelList.add(new HolidayModel("October"));
        holidayModelList.add(new HolidayModel("Gandhi Jayanthi", "02-10-2018", false, ""));
        holidayModelList.add(new HolidayModel("Ayudha Puja", "18-10-2018", true, ""));

        holidayModelList.add(new HolidayModel("November"));
        holidayModelList.add(new HolidayModel("Diwali", "06-11-2018", true, ""));

        holidayModelList.add(new HolidayModel("December"));
        holidayModelList.add(new HolidayModel("Christmas Day", "25-12-2018", true, ""));


        holidaysListAdapter = new HolidaysListAdapter(this, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        //Returning the layout file after inflating
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(holidaysListAdapter);

        holidaysListAdapter.setItems(holidayModelList);

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(List<HolidayModel> data) {

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
