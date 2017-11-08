package com.android.timesheet.user.list;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.timesheet.App;
import com.android.timesheet.R;
import com.android.timesheet.shared.events.TimeSheetValidEvent;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.views.BaseViewImpl;
import com.android.timesheet.shared.widget.CircularProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetList extends BaseViewImpl<TimeSheetPresenter>
        implements TimeSheetViewBehaviour, OnItemClickListener,
        SearchView.OnQueryTextListener {

     /*
    * It's VIPER design pattern
    * -------------------------
    * V --> View
    * I --> Interactions
    * P --> Presenter
    * E --> Entity Model
    * R --> Router
    * -- For displaying list of data we use following components --
    * A --> Adapters
    * S --> Services
    * */

    Unbinder unbinder;
    String TAG = "TimeSheetFragment";
    User user;

    TimeSheetAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.general_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    CircularProgressBar progressBar;

    @BindView(R.id.idsearch)
    SearchView searchView;

    public TimeSheetList(Context context) {
        super(context);
    }

    public TimeSheetList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeSheetList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected TimeSheetPresenter providePresenter() {
        return new TimeSheetPresenter(context, this);
    }


    @Override
    public void initialize() {

        int resourceLayout = R.layout.fragment_with_recycler_view;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        layoutInflater.inflate(resourceLayout, this);

        unbinder = ButterKnife.bind(this);
        mAdapter = new TimeSheetAdapter(context, this);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

        hideKeyboard();

        searchView.setQueryHint("Search by Project Name");
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public void onLoading() {
        progressBar.start();
    }

    @Override
    public void onComplete() {
        progressBar.stop();
    }

    @Override
    public void onSuccess(List<TimeSheet> data) {
        Log.i(TAG, "size : " + String.valueOf(data.size()));

        if (data.size() != 0) {
            App.getInstance().getBus().post(new TimeSheetValidEvent(false));
        }

        mAdapter.setItems(data);
        mAdapter.notifyDataSetChanged();
        onComplete();
    }

    @Override
    public void onFailed(Throwable e) {
        Log.i(TAG, e.getMessage());
        onComplete();
    }

    @Override
    public void fetchDayToDayTimeSheet() {
        onLoading();
        user = presenter().getCurrentUser();
        if (user != null)
            presenter().fetchTimeSheet(user);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        onComplete();
        super.onRestoreInstanceState(state);
    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void reloadTimeSheet() {
        fetchDayToDayTimeSheet();
    }

    @Override
    public void onTimeSheetDeleted(int position) {
        mAdapter.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void removedTimeSheet(TimeSheetResponse response) {
        if (response != null) {
            infoSnackBar(response.getMessage());
//            Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            reloadTimeSheet();
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        TimeSheet sheet = mAdapter.getItem(position);
        switch (sheet.getRowType()) {
            case TimeSheet.TYPE_HEADER: {

                Log.i(TAG, "onItemClick : " + sheet.getHeader());
                presenter().dayTimeSheet(sheet.getHeader());
//                App.getInstance().removeAuthorization();
                break;
            }
            case TimeSheet.TYPE_BODY: {
                Log.i(TAG, "onItemClick : " + sheet.getTimeSheetId());
                presenter().openTimeSheet(sheet);
                break;
            }
            default: {
                Log.i(TAG, "onItemClick : default");
                break;
            }
        }
    }

    @Override
    public void onItemClickToDelete(View view, int position) {
        TimeSheet sheet = mAdapter.getItem(position);

        user = presenter().getCurrentUser();
        if (user != null) {
            presenter().removeTimeSheet(user.empCode, String.valueOf(sheet.timeSheetId));
            onTimeSheetDeleted(position);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
//        App.getInstance().getBus().unregister(this);
        unbinder.unbind();
        super.onDetachedFromWindow();
    }

    public void infoSnackBar(String msg) {
        if (recyclerView != null) {
            Snackbar snack = Snackbar.make(recyclerView, msg, Snackbar.LENGTH_LONG);
            View view = snack.getView();
            TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            tv.setMaxLines(4);
            tv.setTextSize(18);
            snack.show();
        }
    }

    private void hideKeyboard() {
        View view = new View(context);
        InputMethodManager imm = (InputMethodManager) this.context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if ( TextUtils.isEmpty ( newText ) ) {
            mAdapter.getFilter().filter("");
        } else {
            mAdapter.getFilter().filter(newText.toString());
        }
        return true;
    }
}

