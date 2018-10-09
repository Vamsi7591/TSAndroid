package com.android.timesheet.admin_operations.leave.apply_leave.leave_calender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverviewPresenter;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.LeaveCalendarModel;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.calender.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import butterknife.BindView;

public class LeaveCalender extends BaseActivity<LeaveCalenderPresenter> implements BaseViewBehavior<List<LeaveCalendarModel>> {

    String TAG = "LeaveCalendar";

    @BindView(R.id.eventsCalender)
    CalendarView cv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected String title() {
        return "Leave Calendar";
    }

    @Override
    protected int layoutRestID() {
        return R.layout.fragment_leave_calender;
    }

    @Override
    protected LeaveCalenderPresenter providePresenter() {
        return new LeaveCalenderPresenter(this, this);
    }

    public LeaveCalender() {
        Constant.calenderType = 2;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = findViewById(R.id.toolbar);

        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Constant.calenderType = 2;
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

/*        HashSet<Date> events = new HashSet<>();
         events.add(new Date(2017 - 1900, localCalendar.get(Calendar.MONTH), localCalendar.get(Calendar.DATE)));
        events.add(new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 7));
        events.add(new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 10));
        events.add(new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 22));
        events.add(new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 12));
        events.add(new Date(2019 - 1900, localCalendar.get(Calendar.MONTH), 16));*/

        HashSet<LeaveCalendarModel> events = new HashSet<>();
        events.add(new LeaveCalendarModel(Constant.SickLeave, new Date(2017 - 1900, localCalendar.get(Calendar.MONTH), localCalendar.get(Calendar.DATE))));
        events.add(new LeaveCalendarModel(Constant.CasualLeave, new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 7)));
        events.add(new LeaveCalendarModel(Constant.EarnedLeave, new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 10)));
        events.add(new LeaveCalendarModel(Constant.SickLeave, new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 22)));
        events.add(new LeaveCalendarModel(Constant.EarnedLeave, new Date(localCalendar.get(Calendar.YEAR) - 1900, localCalendar.get(Calendar.MONTH), 12)));
        events.add(new LeaveCalendarModel(Constant.SickLeave, new Date(2019 - 1900, localCalendar.get(Calendar.MONTH), 16)));

        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                SimpleDateFormat df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());
                Log.v(TAG, "onDayLongPress : " + df.format(date));
            }

            @Override
            public void onDayPress(Date date) {
                SimpleDateFormat df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());
                Log.v(TAG, "onDayPress : " + df.format(date));
            }

            @Override
            public void setEvents() {

            }
        });
        cv.updateCalendar(events, null);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(List<LeaveCalendarModel> data) {

    }

    @Override
    public void onFailed(Throwable e) {

    }
}
