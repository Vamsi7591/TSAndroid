package com.android.timesheet.user_operations.sheet_exp_list;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.android.timesheet.app.App;
import com.android.timesheet.R;
import com.android.timesheet.shared.events.TimeSheetValidEvent;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.user_operations.sheet_list.sheet_body.TimeSheetList;
import com.android.timesheet.user_operations.sheet_list.sheet_body.TimeSheetViewBehaviour;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeSheetFragment extends BaseFragment<TimeSheetPresenter> implements TimeSheetViewBehaviour {
//implements SwipeRefreshLayout.OnRefreshListener
    public TimeSheetFragment() {
    }

    @BindView(R.id.empty_state_view)
    LinearLayout empty_state_view;

    @BindView(R.id.room_list)
    TimeSheetList timeSheetList;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.expandableListView)
    ExpandableListView timeSheetELV;

    String TAG = "TimeSheetFragment";

    @Override
    protected int layoutResID() {
        return R.layout.fragment_times_sheet;
    }

    @Override
    protected TimeSheetPresenter providePresenter() {
        return new TimeSheetPresenter(getActivity());
    }

    @Override
    protected boolean isSubscriber() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        App.getInstance().getBus().register(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.v(TAG,"onRefresh called");
                timeSheetList.fetchDayToDayTimeSheet();
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        timeSheetList.fetchDayToDayTimeSheet();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Subscribe
    public void onAppOpenEventReceived(TimeSheetValidEvent event) {

        boolean status = (Boolean) event.getValue();
        if (status)
            empty_state_view.setVisibility(View.VISIBLE);
        else
            empty_state_view.setVisibility(View.GONE);


        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onDestroyView() {
        App.getInstance().getBus().unregister(this);

        super.onDestroyView();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(List<TimeSheet> data) {

    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    public void fetchDayToDayTimeSheet() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void reloadTimeSheet() {

    }

    @Override
    public void onTimeSheetDeleted(int position) {

    }

    @Override
    public void removedTimeSheet(TimeSheetResponse response) {

    }
}
