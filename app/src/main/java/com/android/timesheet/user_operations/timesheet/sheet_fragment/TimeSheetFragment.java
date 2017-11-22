package com.android.timesheet.user_operations.timesheet.sheet_fragment;

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

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.events.TimeSheetValidEvent;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.user_operations.timesheet.sheet_list.TimeSheetList;
import com.squareup.otto.Subscribe;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeSheetFragment extends BaseFragment<TimeSheetPresenter> {
    public TimeSheetFragment() {
    }

    @BindView(R.id.emptyStateLL)
    LinearLayout emptyStateLL;

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
                Log.v(TAG, "onRefresh called");
                timeSheetList.fetchDayToDayTimeSheet();
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        timeSheetList.fetchDayToDayTimeSheet();
    }

    @Override
    public void onResume() {
        super.onResume();
        timeSheetList.fetchDayToDayTimeSheet();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Subscribe
    public void onAppOpenEventReceived(TimeSheetValidEvent event) {

        boolean status = (Boolean) event.getValue();
        if (status) // time sheet list is empty
            emptyStateLL.setVisibility(View.VISIBLE);
        else
            emptyStateLL.setVisibility(View.GONE);

        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        App.getInstance().getBus().unregister(this);

        super.onDestroyView();
    }
}
