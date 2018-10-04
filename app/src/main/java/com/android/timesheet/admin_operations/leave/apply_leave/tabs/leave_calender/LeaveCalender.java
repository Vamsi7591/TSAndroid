package com.android.timesheet.admin_operations.leave.apply_leave.tabs.leave_calender;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.models.LeaveCalendarModel;
import com.android.timesheet.shared.widget.calender.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class LeaveCalender extends AppCompatActivity {

//    CalendarView eventsCalendarView;

    /*//Overridden method onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leave_calender, container, false);

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        return view;
    }*/

    String TAG = "LeaveCalendar";


    public LeaveCalender() {
        Constant.calenderType = 2;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_leave_calender);

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

        CalendarView cv = ((CalendarView) findViewById(R.id.eventsCalender));

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

  /*  @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<EventDay> events = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, Calendar.MONTH);
        events.add(new EventDay(cal, R.drawable.circle_blue));

        cal.add(Calendar.DAY_OF_MONTH + 1, Calendar.MONTH);
        events.add(new EventDay(cal, R.drawable.circle_green));

        cal.add(Calendar.DAY_OF_MONTH + 2, Calendar.MONTH);
        events.add(new EventDay(cal, R.drawable.circle_red));

        calendarView.setEvents(events);
    }*/
}
