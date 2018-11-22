package com.android.timesheet.admin_operations.leave.apply_leave.leave_entry;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.ValidationError;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.user_operations.timesheet.sheet_entry.ProjectsSpinnerAdapter;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class LeaveEntryActivity extends BaseActivity<LeaveEntryPresenter> implements BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.leaveTypeSP)
    Spinner leaveTypeSP;

    @BindView(R.id.availableLeavesTV)
    TextView availableLeavesTV;

    @BindView(R.id.usedLeaveTV)
    TextView usedLeaveTV;

    @BindView(R.id.fromDate)
    TextView fromDate;

    @BindView(R.id.toDate)
    TextView toDate;

    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.endTime)
    TextView endTime;

    @BindView(R.id.remarksET)
    EditText remarksET;

    @Nullable
    @BindView(R.id.remarks_count)
    CustomFontTextView remarks_count;

    @BindView(R.id.submitBtn)
    CustomFontTextView submitBtn;

    @BindView(R.id.submitRL)
    RelativeLayout submitRL;

    @BindView(R.id.leaveTypeLL)
    LinearLayout leaveTypeLL;

    /*Error text views*/
    @BindView(R.id.error_leave_type)
    TextView error_leave_type;

    @BindView(R.id.error_from_date)
    TextView error_from_date;

    @BindView(R.id.error_to_date)
    TextView error_to_date;

    @BindView(R.id.error_remarks)
    TextView error_remarks;

    /*Leave Approve widgets*/
    @BindView(R.id.employeeLL)
    LinearLayout employeeLL;

    @BindView(R.id.employeeIV)
    ImageView employeeIV;

    @BindView(R.id.employeeNameTV)
    CustomFontTextView employeeNameTV;

    @BindView(R.id.leaveTypeTV)
    CustomFontTextView leaveTypeTV;

    @BindView(R.id.leaveTypeIV)
    ImageView leaveTypeIV;

    @BindView(R.id.noOfDaysTV)
    CustomFontTextView noOfDaysTV;

    @BindView(R.id.daysTV)
    CustomFontTextView daysTV;

    @BindView(R.id.appliedOnLL)
    LinearLayout appliedOnLL;

    @BindView(R.id.appliedDate)
    TextView appliedDate;

    @BindView(R.id.actionsLL)
    LinearLayout actionsLL;

    @BindView(R.id.approveBtn)
    CustomFontTextView approveBtn;

    @BindView(R.id.rejectBtn)
    CustomFontTextView rejectBtn;
    /*Leave Approve widgets*/

    @Override
    protected int layoutRestID() {
        return R.layout.activity_apply_leave_request;
    }

    @Override
    protected String title() {
        return "Leave Entry";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected LeaveEntryPresenter providePresenter() {
        return new LeaveEntryPresenter(this, this);
    }

    String TAG = "OnDutyEntryActivity";
    LeaveEntry intentLeaveEntry;
    User loggedInUser;
    ArrayList<String> leaveTypesForSpinner = new ArrayList<>();
    private int height;
    Animation animationRL, animationLR, animationFOut, animationFIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String entryString = intent.getStringExtra(AppConfig.MY_LEAVE_OBJECT);
        Gson gson = new Gson();
        LeaveEntry entry = gson.fromJson(entryString, LeaveEntry.class);

        intentLeaveEntry = entry;

        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        leaveTypeSP.setOnItemSelectedListener(this);
        loggedInUser = presenter().getCurrentUser();

        /*Source :
         * https://www.101apps.co.za/articles/using-view-animations-in-your-apps-a-tutorial.html
         * https://www.androidhive.info/2013/06/android-working-with-xml-animations/#move
         * */

        animationRL = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.text_anim_rl);

        animationLR = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.text_anim_lr);


        leaveTypesForSpinner.add(Constant.CasualLeave);
        leaveTypesForSpinner.add(Constant.EarnedLeave);
        leaveTypesForSpinner.add(Constant.SickLeave);


        ProjectsSpinnerAdapter projectsSpinnerAdapter = new ProjectsSpinnerAdapter(LeaveEntryActivity.this, leaveTypesForSpinner);
        leaveTypeSP.setAdapter(projectsSpinnerAdapter);


        /*Apply Leave - Entry*/
        if (intentLeaveEntry != null && intentLeaveEntry.getEmployeeName() != null) {
            Log.d(TAG, "TS getEmployeeName: " + intentLeaveEntry.getEmployeeName());

            toolbarTitleTv.setText("Leave Details");
            leaveAction(intentLeaveEntry);

        } else if (intentLeaveEntry != null) {
            Log.d(TAG, "TS fromDate: " + intentLeaveEntry.fromDate);

            for (int i = 0; i < leaveTypesForSpinner.size(); i++) {
                if (leaveTypesForSpinner.get(i).equalsIgnoreCase(intentLeaveEntry.getLeaveType())) {
                    leaveTypeSP.setSelection(i);
                }
            }

            remarksET.setText(intentLeaveEntry.getRemarks());
            remarks_count.setText(String.format("%d/500", intentLeaveEntry.getRemarks().length()));
            fromDate.setText(intentLeaveEntry.getFromDate());
            toDate.setText(intentLeaveEntry.getToDate());

            leaveEntryWidgets(false);
        } else {
            intentLeaveEntry = new LeaveEntry();
        }

        remarksET.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() <= 500) {
                    remarks_count.setText(String.format("%d/500", s.toString().length()));

                    if (s.toString().length() > 0) {
                        clearSpecificError(error_remarks);
                    } else
                        showError(error_remarks, getResources().getString(R.string.error_remarks));
                }

                remarks_count.setFitsSystemWindows(true);
                remarks_count.requestFocus();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }

    private void leaveAction(LeaveEntry intentLeaveEntry) {

        remarksET.setText(intentLeaveEntry.getRemarks());
        fromDate.setText(intentLeaveEntry.getFromDate());
        toDate.setText(intentLeaveEntry.getToDate());
        startTime.setText(intentLeaveEntry.getStartTime());
        endTime.setText(intentLeaveEntry.getEndTime());

        employeeNameTV.setText(intentLeaveEntry.getEmployeeName());
        leaveTypeTV.setText(intentLeaveEntry.getLeaveType());

        if (intentLeaveEntry.getLeaveType().matches(Constant.CasualLeave))
            leaveTypeIV.setBackground(getResources().getDrawable(R.drawable.ic_casual));
        else if (intentLeaveEntry.getLeaveType().matches(Constant.EarnedLeave))
            leaveTypeIV.setBackground(getResources().getDrawable(R.drawable.ic_earned));
        else
            leaveTypeIV.setBackground(getResources().getDrawable(R.drawable.ic_sick));

        noOfDaysTV.setText(intentLeaveEntry.getNoOfDays());
        appliedDate.setText(intentLeaveEntry.getAppliedDate());

        leaveActionWidgets();
        leaveEntryWidgets(false);
    }

    private void leaveEntryWidgets(boolean flag) {

        // flag true for visible
        if (flag) {
            submitRL.setVisibility(View.VISIBLE);
            remarks_count.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);

            remarksET.setFocusableInTouchMode(true);
            fromDate.setClickable(true);
            toDate.setClickable(true);
            startTime.setClickable(true);
            endTime.setClickable(true);
        } else {
            submitRL.setVisibility(View.GONE);
            remarks_count.setVisibility(View.GONE);
            submitBtn.setVisibility(View.GONE);
            leaveTypeLL.setVisibility(View.GONE);

            remarksET.setFocusable(false);
            fromDate.setClickable(false);
            toDate.setClickable(false);
            startTime.setClickable(false);
            endTime.setClickable(false);
        }

        if (!flag && intentLeaveEntry.getLeaveStatus() != null) {
            if (intentLeaveEntry.getLeaveStatus().matches(Constant.OnHold)) {
                actionsLL.setVisibility(View.VISIBLE);

                approveBtn.setText("Modify");
                rejectBtn.setText("Delete");
            } else {
                leaveActionWidgets();
            }
        }

    }

    private void leaveActionWidgets() {
        employeeLL.setVisibility(View.VISIBLE);
        appliedOnLL.setVisibility(View.VISIBLE);
        actionsLL.setVisibility(View.VISIBLE);

        if (intentLeaveEntry.getLeaveStatus().matches(Constant.Approved)) {
            rejectBtn.setVisibility(View.GONE);
            approveBtn.setText(getResources().getString(R.string.lb_approved));
            noOfDaysTV.setBackground(getResources().getDrawable(R.drawable.circle_green));
            noOfDaysTV.setTextColor(getResources().getColor(R.color.white));
        } else if (intentLeaveEntry.getLeaveStatus().matches(Constant.Rejected)) {
            approveBtn.setVisibility(View.GONE);
            rejectBtn.setText(getResources().getString(R.string.lb_rejected));
            noOfDaysTV.setBackground(getResources().getDrawable(R.drawable.circle_red));
            noOfDaysTV.setTextColor(getResources().getColor(R.color.white));
        } else {
            noOfDaysTV.setBackground(getResources().getDrawable(R.drawable.circle_grey));
            noOfDaysTV.setTextColor(getResources().getColor(R.color.black));
        }
    }

    void showError(TextView textView, String errorStr) {
        textView.setVisibility(View.VISIBLE);
//        textView.setError(errorStr);
        textView.setText(errorStr);
//        textView.setTextColor(Color.BLACK);
        textView.startAnimation(animationLR);
    }

    void clearSpecificError(TextView textView) {
        if (textView.getVisibility() == View.VISIBLE) {
            textView.startAnimation(animationRL);
            textView.setVisibility(View.INVISIBLE);
        }
    }

    void clearErrors() {
        error_from_date.setVisibility(View.INVISIBLE);
        error_to_date.setVisibility(View.INVISIBLE);
        error_leave_type.setVisibility(View.INVISIBLE);
        error_remarks.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.approveBtn)
    void approveBtn() {
        if (approveBtn.getText().toString().matches("modify")) {
            leaveEntryWidgets(true);
        } else {

        }
    }

    @OnClick(R.id.rejectBtn)
    void rejectBtn() {
        if (rejectBtn.getText().toString().toLowerCase().matches("delete")) {
            finish();
        } else {

        }
    }

    @OnClick(R.id.fromDate)
    void showFromDatePicker() {
        showBottomSheet("From Date", height / 2 + 150, fromDate, error_from_date, true);
    }

    @OnClick(R.id.toDate)
    void showToDatePicker() {
        showBottomSheet("To Date", height / 2 + 150, toDate, error_to_date, false);
    }

    @OnClick(R.id.startTime)
    void showStartTimeClock() {
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.view_title, null);

        TextView texts = dialogView.findViewById(R.id.title);
        texts.setText("Select Start Time");

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(LeaveEntryActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                showTime(selectedHour, selectedMinute, true);
            }
        }, hour, minute, false);//Yes 12 hour time

        mTimePicker.setTitle("Select Start Time");
        mTimePicker.setCustomTitle(dialogView);
        mTimePicker.show();
    }

    @OnClick(R.id.endTime)
    void showEndTimeClock() {

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.view_title, null);
        TextView texts = dialogView.findViewById(R.id.title);
        texts.setText("Select End Time");

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(LeaveEntryActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                showTime(selectedHour, selectedMinute, false);
            }
        }, hour, minute, false);//Yes 12 hour time

        mTimePicker.setTitle("Select End Time");
        mTimePicker.setCustomTitle(dialogView);

        mTimePicker.show();
    }

    @OnClick(R.id.submitBtn)
    void save() {
        clearErrors();

        if (remarksET.getText() != null)
            intentLeaveEntry.setRemarks(remarksET.getText().toString());

        /*Validation required*/
        try {
            HashMap<ValidationError, Integer> errors = intentLeaveEntry.validateEntry();

            if (errors != null && errors.size() > 0) {
                /*Display errors*/
                handleError(errors);

                Animation animationShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_animation);
                submitBtn.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                submitBtn.setAnimation(animationShake);
            } else {
                finish();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void showTime(int hour, int min, boolean isStartTime) {
        String format = "";
        String minS = "";
        String hourS = "";

        if (hour == 0) {
            hour += 12;
            hourS = "" + hour;
            format = "AM";
        } else if (hour == 12) {
            hourS = "" + hour;
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            if (hour < 10)
                hourS = "0" + hour;
            else
                hourS = "" + hour;

            format = "PM";
        } else {
            format = "AM";
            if (hour < 10)
                hourS = "0" + hour;
            else
                hourS = "" + hour;
        }

        if (min < 10)
            minS = "0" + min;
        else
            minS = "" + min;

        if (isStartTime) {
            startTime.setText(new StringBuilder().append(hourS).append(":").append(minS)
                    .append(" ").append(format));
            clearSpecificError(error_from_date);
        } else {
            endTime.setText(new StringBuilder().append(hourS).append(":").append(minS)
                    .append(" ").append(format));
            clearSpecificError(error_to_date);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
            bottomSheetDialog = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onFailed(Throwable e) {

    }

    boolean userSelect = false;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        intentLeaveEntry.setLeaveType(leaveTypesForSpinner.get(i));
        clearSpecificError(error_leave_type);
        userSelect = true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    BottomSheetDialog bottomSheetDialog;
    String selectedDate = "";
    Date dateSelected = null;
    HashSet<Date> events;

    /*
     * future for From date
     * false for To date*/
    private void showBottomSheet(String titleText, Integer peekHeight, TextView setValue, TextView clearValue, boolean flag) {

        Constant.calenderType = 1;

        View view = this.getLayoutInflater().inflate(R.layout.view_bottom_sheet, null);
        com.android.timesheet.shared.widget.calender.CalendarView
                calendarView = (com.android.timesheet.shared.widget.calender.CalendarView) view.findViewById(R.id.calenderView);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(titleText);

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(475 * 3);
        mBehavior.setState(3);
        bottomSheetDialog.show();

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.hide();
            }
        });

        /*Custom calender view*/
        calendarView.setEventHandler(new com.android.timesheet.shared.widget.calender.CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                SimpleDateFormat df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());
                Log.v(TAG, "onDayLongPress : " + df.format(date));
            }

            @Override
            public void onDayPress(Date date) {
                SimpleDateFormat df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());//"yyyy/MM/dd"
                selectedDate = df.format(date);
                dateSelected = date;

                setValue.setTag(selectedDate);
                clearValue.setTag(dateSelected);

//                dateSelected = convertCalFormat(selectedDate);
                Log.v(TAG, "onDayPress : " + df.format(date));
                setValue.setText(df.format(date));

                if (flag) {
                    intentLeaveEntry.setFromDate(df.format(date));
                } else {
                    intentLeaveEntry.setToDate(df.format(date));
                }

                clearSpecificError(clearValue);

                int dayOfMonth = 0;
                int monthOfYear = 0;
                int year = 0;

                if (!selectedDate.isEmpty()) {//mUser.dateOfBirth

                    String[] dobList = selectedDate.split("/");
                    dayOfMonth = Integer.parseInt(dobList[2]);
                    monthOfYear = (Integer.parseInt(dobList[1]));
                    year = Integer.parseInt(dobList[0]);

                } else {
                    return;
                }

                Calendar cal = Calendar.getInstance(Locale.getDefault());
                cal.setTime(new Date());

                cal.clear();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear - 1);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//                intentLeaveEntry.setWeekNo(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));

                selectedDate = year + "/" + monthOfYear + "/" + dayOfMonth;
                Log.v(TAG, "Selected week num : " + String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)) + ", Date : " + selectedDate);

                bottomSheetDialog.hide();
            }

            @Override
            public void setEvents() {
//                calendarView.updateCalendar(null, null);
            }
        });

        if (setValue.getTag() != null) {

            if (clearValue.getTag() == null)
                clearValue.setTag(convertDateFormat(setValue.getTag().toString()));

            calendarView.updateSelectedDate((Date) clearValue.getTag(), (String) setValue.getTag());
        }

        /*if (!selectedDate.isEmpty()) {

            if (dateSelected == null)
                dateSelected = convertDateFormat(selectedDate);

            calendarView.updateSelectedDate(dateSelected, selectedDate);
        }*/
    }

    private Date convertDateFormat(String data) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());
            Date date = simpleDateFormat.parse(data);
            Log.d(TAG, " test==>" + date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    void handleError(HashMap<ValidationError, Integer> errors) {
        for (ValidationError error : errors.keySet()) {
            String errorStr = getString(errors.get(error));

            if (error.equals(ValidationError.LEAVE_TYPE)) {
                showError(error_leave_type, errorStr);
            } else if (error.equals(ValidationError.FROM_DATE)) {
                showError(error_from_date, errorStr);
            } else if (error.equals(ValidationError.TO_DATE)) {
                showError(error_to_date, errorStr);
            } else if (error.equals(ValidationError.REMARKS)) {
                showError(error_remarks, errorStr);
            }
        }
    }
}
