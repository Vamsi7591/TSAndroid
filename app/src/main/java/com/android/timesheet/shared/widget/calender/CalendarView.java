package com.android.timesheet.shared.widget.calender;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.models.LeaveCalendarModel;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by VamsyK on 28/01/2017.
 */
public class CalendarView extends LinearLayout {

    //region variables
    private static final String LOGTAG = "Calendar View";
    private static final int DAYS_COUNT = 42;
    private static final String DATE_FORMAT = "MMMM";
    private Calendar currentDate = Calendar.getInstance();
    private Calendar now = Calendar.getInstance();
    private EventHandler eventHandler = null;
    Context context;

    //    HashSet<Date> eventDays;
    HashSet<LeaveCalendarModel> eventDays;
    int _maxDate = 0;
    int _maxMonth = 0;
    int _maxYear = 0;

    int _minMonth = 0;
    int _minYear = 2011;//1950;
    int _selectedYear = 0;

    Date selectedDate;

    private List<String> yearList;
    String displayedposition = "";
    int _width = 0;
    SimpleDateFormat df = null;

    //region adapters
    HorizontalAdapter horizontalAdapter;


    //region Views
    private ImageView btnPrev;
    private ImageView btnNext;

    private TextView txtDate;
    private GridView grid;

    private CenteringRecyclerView horizontal_recycler_view;
    //endregion

    //region constructor
    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }
    //endregion

    //region LocalMethos
    public void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_calendar, this);

        /* Types
         * 0 - future dates disabled (default)
         * 1 - From Date / To Date
         * 2 - For Events only, No click actions
         **/

        _maxDate = now.get(Calendar.DAY_OF_MONTH);
        _maxMonth = now.get(Calendar.MONTH);
        _maxYear = now.get(Calendar.YEAR);

        if (Constant.calenderType == 1) {
            _minYear = now.get(Calendar.YEAR);
            _minMonth = now.get(Calendar.MONTH);
            /*Adding +1 to year for future dates*/
            _maxYear = now.get(Calendar.YEAR) + 1;
        } else if (Constant.calenderType == 2) {
            _minYear = now.get(Calendar.YEAR) - 1;
            _minMonth = 0;
            _maxMonth = 11;
            _maxDate = 31;
            /*Adding +1 to year for future dates*/
            _maxYear = now.get(Calendar.YEAR) + 1;
        }


        if (now.get(Calendar.YEAR) == (currentDate.get(Calendar.YEAR)))
            currentDate.set((currentDate.get(Calendar.YEAR)), currentDate.get(Calendar.MONTH), (currentDate.get(Calendar.DAY_OF_MONTH)));

        if (Constant.calenderType != 0) {
            /*Adding -1 to display current year*/
            displayedposition = String.valueOf(_maxYear - 1);
        } else {
            displayedposition = String.valueOf(_maxYear);
        }
        _width = getDeviceWidth(context);

        df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());//"yyyy/MM/dd"

        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components
        btnNext = (ImageView) findViewById(R.id.calendar_next_button);
        btnPrev = (ImageView) findViewById(R.id.calendar_prev_button);

        txtDate = (TextView) findViewById(R.id.calendar_date_display);
        grid = (GridView) findViewById(R.id.calendar_grid);

        /*horizontal year view*/
        horizontal_recycler_view = (CenteringRecyclerView) findViewById(R.id.yearList);

        yearList = fill_with_data();
        horizontalAdapter = new HorizontalAdapter(yearList, getContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);


        if (!displayedposition.isEmpty()) {
            moveToYear((getYearPos(String.valueOf(displayedposition))));
        } else {
            horizontal_recycler_view.smoothScrollToPosition(getYearPos(""));
        }
        /**/
        /*if (_selectedYear != 0) {
            horizontalAdapter.notifyDataSetChanged();

            LinearLayoutManager llm = (LinearLayoutManager) horizontal_recycler_view.getLayoutManager();
            llm.scrollToPositionWithOffset((getYearPos(String.valueOf(_selectedYear)) - 2), yearList.size());
        }*/
    }

    private void assignClickHandlers() {
        // add one month and refresh UI
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDate.get(Calendar.YEAR)) != _maxYear) {

                    currentDate.add(Calendar.MONTH, 1);

                    updateCalendar();
                    eventHandler.setEvents();

                    int cY = currentDate.get(Calendar.YEAR);
                    if (!displayedposition.equalsIgnoreCase(String.valueOf(cY))) {

                        displayedposition = String.valueOf(cY);
                        horizontalAdapter.notifyDataSetChanged();

                        moveToYear((getYearPos(String.valueOf(displayedposition))));
                    }
                } else {
                    if ((currentDate.get(Calendar.MONTH)) < _maxMonth) {
                        currentDate.add(Calendar.MONTH, 1);
                        updateCalendar();
                        eventHandler.setEvents();
                    } else {
//                        Toast.makeText(getContext(), "Max Date reached", Toast.LENGTH_LONG).show();
                        customToast(grid.getRootView(), "Max Date reached");
                    }
                }
            }
        });

        // subtract one month and refresh UI
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDate.get(Calendar.YEAR)) != _minYear) {
                    currentDate.add(Calendar.MONTH, -1);

                    updateCalendar();
                    eventHandler.setEvents();

                    int cY = currentDate.get(Calendar.YEAR);
                    if (!displayedposition.equalsIgnoreCase(String.valueOf(cY))) {

                        displayedposition = String.valueOf(cY);
                        horizontalAdapter.notifyDataSetChanged();

                        moveToYear((getYearPos(String.valueOf(displayedposition))));
                    }

                } else {
                    int kv = currentDate.get(Calendar.MONTH) - _minMonth;
                    if (kv != 0) {
                        currentDate.add(Calendar.MONTH, -1);
                        updateCalendar();
                        eventHandler.setEvents();
                    } else
                        customToast(grid.getRootView(), "Min Date reached");
//                        Toast.makeText(getContext(), "Min Date reached", Toast.LENGTH_LONG).show();
                }
            }
        });

        // long-pressing a day
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                // handle long-press
                if (eventHandler == null)
                    return false;

                if (Constant.calenderType != 2)
                    eventHandler.onDayLongPress((Date) view.getItemAtPosition(position));

                return true;
            }
        });

        // Just click a day
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // handle long-press
                if (eventHandler != null) {
                    selectedDate = (Date) parent.getItemAtPosition(position);

                    int y = currentDate.get(Calendar.YEAR);
                    if ((selectedDate.getDate() > _maxDate) && (_maxMonth == selectedDate.getMonth()) && (y == _maxYear)) {
                        ((TextView) view).setTextColor(Color.GRAY);
                        selectedDate = null;
                    } else if ((selectedDate.getDate() > _maxDate) && (_maxMonth == selectedDate.getMonth()) && (y == _maxYear)) {
//                        Toast.makeText(getContext(), "Age must be greater than 18 years.", Toast.LENGTH_LONG).show();
                        selectedDate = null;
                    } else if (Constant.calenderType == 1 && (selectedDate.getDate() < _maxDate) && (_maxMonth == selectedDate.getMonth()) && (y == now.get(Calendar.YEAR))) {
//                        past dates disabled
                        selectedDate = null;
                    } else {
                        if (Constant.calenderType != 2) {
                            eventHandler.onDayPress((Date) parent.getItemAtPosition(position));
                            updateCalendar();
                        }
                    }
                }
            }
        });

    }

    /* Increase 1 or 5 years*/
    public void increaseYear(int val) {

        if (((currentDate.get(Calendar.YEAR)) + (val / 12) <= _maxYear)) {

            if (((currentDate.get(Calendar.YEAR) + (val / 12)) != _maxYear)) {
                currentDate.add(Calendar.MONTH, val);
                updateCalendar();
                eventHandler.setEvents();
            } else {
                int cv = 0;

                int cm = currentDate.get(Calendar.MONTH);

                if (cm == _maxMonth) {
                    cv = 0;
                } else if (cm > _maxMonth) {
                    cv = cm - _maxMonth;
                } else {
                    cv = 0;
                }

                val = val - cv;
                currentDate.add(Calendar.MONTH, val);
                updateCalendar();
                eventHandler.setEvents();
            }

        } else {
            int cv = _maxYear - currentDate.get(Calendar.YEAR);

            if (cv == 0) {
//                Toast.makeText(getContext(), "Max Date reached", Toast.LENGTH_LONG).show();
                customToast(grid.getRootView(), "Max Date reached");
            } else {

                cv = cv * 12;
                int kv = currentDate.get(Calendar.MONTH) - _maxMonth;
                cv = cv - kv;
                currentDate.add(Calendar.MONTH, cv);
                updateCalendar();
                eventHandler.setEvents();
//                Toast.makeText(getContext(), "Max Date reached", Toast.LENGTH_LONG).show();
                customToast(grid.getRootView(), "Max Date reached");
            }
        }
    }

    /* Decrease 1 or 5 years*/
    public void decreaseYear(int val) {

        if (((currentDate.get(Calendar.YEAR)) - (val / 12) >= _minYear)) {

            if ((currentDate.get(Calendar.YEAR) - (val / 12)) != _minYear) {
                currentDate.add(Calendar.MONTH, -val);
                updateCalendar();
                eventHandler.setEvents();

            } else {
                currentDate.add(Calendar.MONTH, -val);
                updateCalendar();
                eventHandler.setEvents();
            }
        } else {
            int cv = currentDate.get(Calendar.YEAR) - _minYear;

            if (cv == 0) {
                int cvv = currentDate.get(Calendar.MONTH) - _minMonth;
                if (cvv != 0) {
                    currentDate.add(Calendar.MONTH, -cvv);
                    updateCalendar();
                    eventHandler.setEvents();
                }
//                Toast.makeText(getContext(), "Min Date reached", Toast.LENGTH_LONG).show();
                customToast(grid.getRootView(), "Min Date reached");
            } else {
                cv = cv * 12;
                int kv = currentDate.get(Calendar.MONTH) - _minMonth;
                cv = cv + kv;
                currentDate.add(Calendar.MONTH, -cv);
                updateCalendar();
                eventHandler.setEvents();
//                Toast.makeText(getContext(), "Min Date reached", Toast.LENGTH_LONG).show();
                customToast(grid.getRootView(), "Min Date reached");
            }
        }
    }

    public void updateSelectedDate(Date _currentDate, String _selectedDate) {//

        int dayOfMonth = 0;
        int monthOfYear = 0;
        int year = 0;

        if (!_selectedDate.isEmpty()) {//mUser.dateOfBirth yyyy/MM/dd

            String[] dobList = _selectedDate.split("/");
            dayOfMonth = Integer.parseInt(dobList[2]);
            monthOfYear = (Integer.parseInt(dobList[1]));
            year = Integer.parseInt(dobList[0]);
            selectedDate = _currentDate;
            _selectedYear = year;
            displayedposition = String.valueOf(year);

            Calendar call = Calendar.getInstance();
            call.set(year, (monthOfYear - 1), dayOfMonth);
            currentDate = (Calendar) call.clone();

            updateCalendar();

            moveToYear((getYearPos(displayedposition)));
        }


    }

    public void updateCalendar() {
        updateCalendar(eventDays, null);
    }

    public void setEventHandler(EventHandler eventHandler) {
        try {
            this.eventHandler = eventHandler;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //region UPDATE CALENDAR
    public void updateCalendar(HashSet<LeaveCalendarModel> events, Calendar _currentDate) {
        try {
            eventDays = events;
            ArrayList<Date> cells = new ArrayList<>();

            Calendar calendar = (Calendar) currentDate.clone();

            // determine the cell for current month's beginning
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

            // move calendar backwards to the beginning of the week
            calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

            // fill cells
            while (cells.size() < DAYS_COUNT) {
                cells.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            // update grid
            grid.setAdapter(new CalendarAdapter(getContext(), cells, events));

            // update title
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

            txtDate.setText(sdf.format(currentDate.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCalendar(HashSet<LeaveCalendarModel> events) {
        try {

            eventDays = events;
            ArrayList<Date> cells = new ArrayList<>();

            Calendar calendar = (Calendar) currentDate.clone();

            // determine the cell for current month's beginning
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

            // move calendar backwards to the beginning of the week
            calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

            // fill cells
            while (cells.size() < DAYS_COUNT) {
                cells.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            // update grid
            grid.setAdapter(new CalendarAdapter(getContext(), cells, events));

            // update title
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

            txtDate.setText(sdf.format(currentDate.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region calendar adapter
    private class CalendarAdapter extends ArrayAdapter<Date> {
        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<LeaveCalendarModel> _eventDays) {
            super(context, R.layout.control_calendar_day, days);
            try {
                eventDays = _eventDays;
                inflater = LayoutInflater.from(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @NonNull
        @Override
        public View getView(int position, View view, ViewGroup parent) {

            try {
                // day in question
                Date date = getItem(position);
                int day = date.getDate();
                int month = date.getMonth();
                int year = date.getYear();

                // inflate item if it does not exist yet
                if (view == null)
                    view = inflater.inflate(R.layout.control_calendar_day, parent, false);

                // if this day has an event, specify event image
                view.setBackgroundResource(0);
                // set text
                ((TextView) view).setText(String.valueOf(date.getDate()));

                // clear styling
//                ((TextView) view).setTypeface(null, Typeface.NORMAL);
                //((TextView) view).setTextColor(Color.WHITE);

                if (month != getItem(15).getMonth()) {
                    // if this day is outside current month, then Invisible
                    ((TextView) view).setVisibility(INVISIBLE);
                } else {
                    int y = currentDate.get(Calendar.YEAR);

                    if ((day > _maxDate) && (_maxMonth == getItem(15).getMonth()) && (y == _maxYear)) {
                        ((TextView) view).setTextColor(Color.GRAY);
                    }

                    if (Constant.calenderType == 1) {
                        int d = now.get(Calendar.DAY_OF_MONTH);
                        if ((day < d) && (_maxMonth == getItem(15).getMonth()) && (y == _minYear)) {
                            ((TextView) view).setTextColor(Color.GRAY);
                        }
                    }
                }

                if (selectedDate != null && Constant.calenderType != 2) {

                    if (df == null)
                        df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());//"yyyy/MM/dd"

                    //selectedDate.compareTo(date) == 0
                    if (df.format(date).equalsIgnoreCase(df.format(selectedDate))) {

                        ((TextView) view).setTypeface(null, Typeface.BOLD);
                        ((TextView) view).setTextColor(Color.BLACK);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            ((TextView) view).setBackground(getResources().getDrawable(R.drawable.bg_selected_day));

                            if (_selectedYear != 0) {
                                moveToYear((getYearPos(String.valueOf(_selectedYear))));
                            }
                        }
                    }
                }

                if (!eventDays.isEmpty() && Constant.calenderType == 2) {
                    if (df == null)
                        df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());//"yyyy/MM/dd"

                    Date eventDay = new Date();
//                    for (int i = 0; i < eventDays.size(); i++) {
                    for (LeaveCalendarModel eventDay1 : eventDays) {
                        eventDay = eventDay1.getLeaveDate();

                        if (df.format(date).equalsIgnoreCase(df.format(eventDay))) {
//                        if (day == eventDay.getDay() && month == eventDay.getMonth() && year == eventDay.getYear()) {

                            ((TextView) view).setTypeface(null, Typeface.BOLD);
                            ((TextView) view).setTextColor(Color.WHITE); //BLACK
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                                if (eventDay1.getLeaveType().matches(Constant.SickLeave)) // Red
                                    ((TextView) view).setBackground(getResources().getDrawable(R.drawable.circle_red));
                                else if (eventDay1.getLeaveType().matches(Constant.CasualLeave)) // Green
                                    ((TextView) view).setBackground(getResources().getDrawable(R.drawable.circle_green));
                                else //EarnedLeave // Earned blue
                                    ((TextView) view).setBackground(getResources().getDrawable(R.drawable.circle_blue));

                                if (_selectedYear != 0) {
                                    moveToYear((getYearPos(String.valueOf(_selectedYear))));
                                }
                            }
                            break;
                        }
                    }
                }

                return view;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return view;
        }
    }

    /*new horizontal year view data*/
    public List<String> fill_with_data() {
        List<String> data = new ArrayList<>();
        for (int y = _minYear; y <= _maxYear; y++) {
            data.add("" + y);
        }
        return data;
    }

    /*new horizontal year view adapter*/
    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        List<String> horizontalList = Collections.emptyList();
        Context context;

        HorizontalAdapter(List<String> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            CustomFontTextView yearTV;

            MyViewHolder(View view) {
                super(view);
                yearTV = (CustomFontTextView) view.findViewById(R.id.yearTV);
            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.control_calendar_year, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.yearTV.setText(horizontalList.get(position));

            if (!displayedposition.isEmpty()) {
                if (horizontalList.get(position).equalsIgnoreCase(displayedposition)) {
                    holder.yearTV.setTextColor(context.getResources().getColor(R.color.colorPrimary));//Color.parseColor("#abedd8")
                    holder.yearTV.setTypeface(FontUtils.getTypeFace(context, context.getString(R.string.roboto_light)), Typeface.BOLD);
                } else {
                    holder.yearTV.setTextColor(Color.BLACK);
                    holder.yearTV.setTypeface(FontUtils.getTypeFace(context, context.getString(R.string.roboto_light)), Typeface.NORMAL);
                }
            } else {
                holder.yearTV.setTextColor(Color.BLACK);
                holder.yearTV.setTypeface(FontUtils.getTypeFace(context, context.getString(R.string.roboto_light)), Typeface.NORMAL);
            }

            /*if (position == (horizontalList.size() - 1)) {
                moveToYear((getYearPos(displayedposition)));
            }*/
            holder.yearTV.setOnClickListener(new OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = horizontalList.get(position);
//                    Toast.makeText(getContext(), list, Toast.LENGTH_SHORT).show();

                    int currentYear = currentDate.get(Calendar.YEAR);

                    int selectedYear = Integer.parseInt(list);
                    _selectedYear = selectedYear;

                    int _months = (currentYear - selectedYear);

                    _months = _months * 12;

                    if (currentYear <= selectedYear) {
                        if (_months < 0)
                            _months = -_months;

                        increaseYear(_months);
                    } else {
                        decreaseYear(_months);
                    }

                    displayedposition = list;
                    horizontalAdapter.notifyDataSetChanged();

                    moveToYear(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }

    public void moveToYear(int position) {
        horizontal_recycler_view.center(position);
    }

    public static int getDeviceWidth(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();

        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);

        return (Math.round(displayMetrics.widthPixels / displayMetrics.density)) / 5;
    }

    public int getYearPos(String yearChan) {

        for (int i = 0; i < yearList.size(); i++) {

            if (yearList.get(i).equalsIgnoreCase(yearChan)) {
                return i;
            }
        }

        return yearList.size();
    }

    // My custom toast
    public void customToast(View view, String message) {
        Context context = this.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View customToastRoot = inflater.inflate(R.layout.custom_toast, null);

        TextView messageText = (TextView) customToastRoot.findViewById(R.id.messageTV);
        messageText.setText(message);

        Toast customToast = new Toast(context);
        customToast.setView(customToastRoot);
//        customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
        customToast.setGravity(Gravity.BOTTOM, 0, 50);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.show();
    }
    //endregion

    //region Interface
    public interface EventHandler {
        void onDayLongPress(Date date);

        void onDayPress(Date date);

        void setEvents();
    }
    //endregion
}
