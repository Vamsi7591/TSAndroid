package com.android.timesheet.admin_operations.leave.apply_leave.tabs.popup;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.ValidationError;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.user_operations.timesheet.sheet_entry.ProjectsSpinnerAdapter;
import com.android.timesheet.user_operations.timesheet.sheet_entry.TimeSheetEntry;

import org.parceler.Parcels;

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

public class LeavePopUpActivity extends BaseActivity<LeavePopUpPresenter> implements BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.leaveTypeSP)
    Spinner leaveTypeSP;

    @BindView(R.id.fromDate)
    TextView fromDate;

    @BindView(R.id.toDate)
    TextView toDate;

    @BindView(R.id.remarksET)
    EditText remarksET;

    @Nullable
    @BindView(R.id.remarks_count)
    CustomFontTextView remarks_count;

    @BindView(R.id.submitBtn)
    CustomFontTextView submitBtn;

    /*Error text views*/
    @BindView(R.id.error_leave_type)
    TextView error_leave_type;

    @BindView(R.id.error_from_date)
    TextView error_from_date;

    @BindView(R.id.error_to_date)
    TextView error_to_date;

    @BindView(R.id.error_remarks)
    TextView error_remarks;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_apply_leave_pop_up;
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
    protected LeavePopUpPresenter providePresenter() {
        return new LeavePopUpPresenter(this, this);
    }

    String TAG = "LeavePopUpActivity";
    LeaveEntry intentLeaveEntry;
    User loggedInUser;
    ArrayList<String> leaveTypesForSpinner = new ArrayList<>();
    private int height;
    private int width;
    Animation animationRL, animationLR, animationFOut, animationFIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Parcelable parcelable = getIntent().getParcelableExtra(Constant.KEYS.LEAVE_ENTRY_KEY);
        intentLeaveEntry = Parcels.unwrap(parcelable);

        boolean fromLeaveEntryList = intentLeaveEntry != null;

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


        /*Apply Leave - Entry*/
        if (intentLeaveEntry != null) {
            Log.d(TAG, "TS : " + intentLeaveEntry.fromDate);
        } else {
            intentLeaveEntry = new LeaveEntry();

            leaveTypesForSpinner.add("Annual Leave");
            leaveTypesForSpinner.add("Medical Leave");

            ProjectsSpinnerAdapter projectsSpinnerAdapter = new ProjectsSpinnerAdapter(LeavePopUpActivity.this, leaveTypesForSpinner);
            leaveTypeSP.setAdapter(projectsSpinnerAdapter);

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

    @OnClick(R.id.fromDate)
    void showFromDatePicker() {
        showBottomSheet("From Date", height / 2 + 150, fromDate, error_from_date, true);
    }

    @OnClick(R.id.toDate)
    void showToDatePicker() {
        showBottomSheet("To Date", height / 2 + 150, toDate, error_to_date, false);
    }

    @OnClick(R.id.submitBtn)
    void save() {
        clearErrors();

        if (remarksET.getText() != null)
            intentLeaveEntry.setRemarks(remarksET.getText().toString());

        /*Validation required*/
        try {
            HashMap<ValidationError, Integer> errors = intentLeaveEntry.validateLeaveEntry();

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

    @Override
    protected void onResume() {
        super.onResume();

        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
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
     * fltrue for From date
     * false for To date*/
    private void showBottomSheet(String titleText, Integer peekHeight, TextView setValue, TextView clearValue, boolean flag) {

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
                Log.v("Click", " : " + df.format(date));
            }

            @Override
            public void onDayPress(Date date) {
                SimpleDateFormat df = new SimpleDateFormat(Constant.DataFormat, Locale.getDefault());//"yyyy/MM/dd"
                selectedDate = df.format(date);
                dateSelected = date;
//                dateSelected = convertCalFormat(selectedDate);
                Log.v("Click", " : " + df.format(date));
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


//                Calendar calDe = Calendar.getInstance(Locale.getDefault());
//                calDe.setTime(new Date());
//                cWeek = calDe.get(Calendar.WEEK_OF_YEAR);
//                cYear =calDe.get(Calendar.YEAR);
                // Calendar cal = new java.util.GregorianCalendar();

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
//                calendar_view.updateCalendar(null, null);
            }
        });

        if (!selectedDate.isEmpty()) {
            if (dateSelected == null)
                dateSelected = convertDateFormat(selectedDate);

            calendarView.updateSelectedDate(dateSelected, selectedDate);
        }
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
