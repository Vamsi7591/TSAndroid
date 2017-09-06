package com.android.timesheet.shared.fragments;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.timesheet.App;
import com.android.timesheet.R;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.utils.LogUtils;

import butterknife.BindView;


/**
 * Created by vamsikonanki on 3/8/16.
 */
public abstract class BaseRecyclerFragment<T extends BasePresenter> extends BaseFragment<T> {

    @BindView(R.id.swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    public boolean isSwipeRefreshLayout() {
        return false;
    }

    protected void updateSwipeRefresh() {
        if (swipeRefreshLayout != null) {
            if (isSwipeRefreshLayout()) {
                swipeRefreshLayout.setEnabled(true);
                swipeRefreshLayout.setOnRefreshListener(() -> onSwipeRefresh());
            } else {
                swipeRefreshLayout.setEnabled(false);
                swipeRefreshLayout.setOnRefreshListener(null);
            }
        }
    }

    protected abstract RecyclerView.Adapter adapter();

    protected abstract Location getSharedLocation();

    protected RecyclerView.LayoutManager recyclerViewLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        return linearLayoutManager;
    }

    @Override
    protected int layoutResID() {
        return R.layout.base_recycler_view;
    }

    //region Lifecycle

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(recyclerViewLayoutManager());
        recyclerView.setAdapter(adapter());

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setProgressViewOffset(false, 0, getResources().getDimensionPixelSize(R.dimen.refresh_indicator_end));

        updateSwipeRefresh();
    }

    protected SharedPreferences.Editor sharedPrefsEditor() {
        return PreferenceManager.getDefaultSharedPreferences(App.getAppContext()).edit();
    }

    protected SharedPreferences sharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
    }

    //endregion

    //region Public & Private methods

    public void onSwipeRefresh() {
        LogUtils.LOG("REFRESH");
    }

    //endregion
}
