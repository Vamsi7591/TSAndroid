package com.android.timesheet.user_operations.sheet_list.sheet_entry;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.ValidationError;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetEntry extends BaseActivity<TimeSheetEntryPresenter> implements
        BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;

    @BindView(R.id.spinnerProjects)
    Spinner spinnerProjects;

    @BindView(R.id.pickerDate)
    TextView pickerDate;

    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.endTime)
    TextView endTime;

    @BindView(R.id.descriptionET)
    EditText descriptionET;

    @Nullable
    @BindView(R.id.description_count)
    CustomFontTextView description_count;

    @BindView(R.id.modifyBtn)
    Button modifyBtn;

    @BindView(R.id.saveBtn)
    Button saveBtn;

    /*Error text views*/
    @BindView(R.id.error_project_name)
    TextView error_project_name;

    @BindView(R.id.error_date)
    TextView error_date;

    @BindView(R.id.error_start_time)
    TextView error_start_time;

    @BindView(R.id.error_end_time)
    TextView error_end_time;

    @BindView(R.id.error_description)
    TextView error_description;

    private boolean fromTimeSheetList = false;

    String TAG = "TimeSheetEntry";

    // Pojo
    TimeSheet sheet;
    User user;
    ProjectNamesResponse projectsListResponse;

    //    List<String> projectNames = new ArrayList<>();
    ArrayList<String> projectNames = new ArrayList<>();
    private int height;
    private int width;
    Animation animationRL, animationLR,animationFOut,animationFIn;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_timesheet_details;
    }

    @Override
    protected int menuResID() {
        return R.menu.delete_menu;
    }

    @Override
    protected String title() {
        return "Time SheetEntry";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected TimeSheetEntryPresenter providePresenter() {
        return new TimeSheetEntryPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Parcelable parcelable = getIntent().getParcelableExtra(Constant.KEYS.TIME_SHEET_DETAIL_KEY);
        sheet = Parcels.unwrap(parcelable);

        if (sheet != null)
            fromTimeSheetList = true;
        else
            fromTimeSheetList = false;

        textViewToolbarTitle.setText(title());
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        spinnerProjects.setOnItemSelectedListener(this);
        user = presenter().getCurrentUser();

        /*Source :
        * https://www.101apps.co.za/articles/using-view-animations-in-your-apps-a-tutorial.html
        * https://www.androidhive.info/2013/06/android-working-with-xml-animations/#move
        * */
        animationRL = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.text_anim_rl);

        animationLR = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.text_anim_lr);

        if (sheet != null) {
            Log.d(TAG, "TS : " + sheet.date);
            if (mMenu == null) {
                showMenu();
            }

            projectNames.add(sheet.projectName);
            //Custom spinner
            ProjectsSpinnerAdapter projectsSpinnerAdapter = new ProjectsSpinnerAdapter(TimeSheetEntry.this, projectNames);
            spinnerProjects.setAdapter(projectsSpinnerAdapter);
            pickerDate.setText(sheet.date);
//            startTime.setText(sheet.startTime);
            showTime(Integer.parseInt(sheet.startTime.substring(0, 2)), Integer.parseInt(sheet.startTime.substring(3, 5)), true);
//            endTime.setText(sheet.endTime);
            showTime(Integer.parseInt(sheet.endTime.substring(0, 2)), Integer.parseInt(sheet.endTime.substring(3, 5)), false);

            if (!sheet.taskDescription.isEmpty()) {
                descriptionET.setText(sheet.taskDescription);
                description_count.setText(String.format("%d/500", sheet.taskDescription.length()));
            }
            disableViews(true, 0);
        } else {
            sheet = new TimeSheet();

            if (user != null)
                presenter().getProjectNames(user.empCode);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
            lp.setMargins(0, 0, 75, 0);
            textViewToolbarTitle.setPadding(0, 0, 75, 0);
            textViewToolbarTitle.setLayoutParams(lp);
            disableViews(false, 0);
        }

        if (user != null)
            sheet.setEmpCode(user.empCode);

        if (descriptionET != null)
            descriptionET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE ||
                            (event.getAction() == KeyEvent.ACTION_DOWN)) {
                        closeKeyBoard();
                        return true;
                    }

                    return false;
                }
            });

        descriptionET.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (500 != s.toString().length()) {
                    description_count.setText(String.format("%d/500", s.toString().length()));

                    if (s.toString().length() > 0) {
                        clearSpecificError(error_description);
                    } else
                        showError(error_description, getResources().getString(R.string.error_desc_required));
                }

                description_count.setFitsSystemWindows(true);
                description_count.requestFocus();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        clearErrors();

    }

    @OnClick(R.id.modifyBtn)
    void modify() {
        disableViews(true, 1);
    }

    @OnClick(R.id.saveBtn)
    void save() {
        clearErrors();

        if (descriptionET.getText() != null)
            sheet.setTaskDescription(descriptionET.getText().toString());

        /*Validation required*/
        HashMap<ValidationError, Integer> errors = sheet.validateTimeSheetEntry();

        if (errors != null && errors.size() > 0) {
            /*Display errors*/
            handleError(errors);

        } else {
            /*submit time sheet entry*/
            if (fromTimeSheetList) {
                presenter().updateSheet(sheet);
            } else
                presenter().submitTimeSheet(sheet);
        }
    }

    void handleError(HashMap<ValidationError, Integer> errors) {
        for (ValidationError error : errors.keySet()) {
            String errorStr = getString(errors.get(error));

            if (error.equals(ValidationError.PROJECT_NAME)) {
                showError(error_project_name, errorStr);
            } else if (error.equals(ValidationError.DATE)) {
                showError(error_date, errorStr);
            } else if (error.equals(ValidationError.START_TIME)) {
                showError(error_start_time, errorStr);
            } else if (error.equals(ValidationError.END_TIME)) {
                showError(error_end_time, errorStr);
            } else if (error.equals(ValidationError.DESCRIPTION)) {
                showError(error_description, errorStr);
            }
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
        error_project_name.setVisibility(View.INVISIBLE);
        error_date.setVisibility(View.INVISIBLE);
        error_start_time.setVisibility(View.INVISIBLE);
        error_end_time.setVisibility(View.INVISIBLE);
        error_description.setVisibility(View.INVISIBLE);
    }


    @OnClick(R.id.pickerDate)
    void showCustomDatePicker() {
        showBottomSheet("Date", height / 2 + 150);
    }


    @OnClick(R.id.startTime)
    void showStartTimeClock() {
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.view_title, null);
        TextView texts = (TextView) dialogView.findViewById(R.id.title);
        texts.setText("Select Start Time");

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(TimeSheetEntry.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                showTime(selectedHour, selectedMinute, true);
            }
        }, hour, minute, false);//Yes 12 hour time

        mTimePicker.setCustomTitle(dialogView);
        mTimePicker.show();
    }

    @OnClick(R.id.endTime)
    void showEndTimeClock() {

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.view_title, null);
        TextView texts = (TextView) dialogView.findViewById(R.id.title);
        texts.setText("Select End Time");

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(TimeSheetEntry.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                showTime(selectedHour, selectedMinute, false);
            }
        }, hour, minute, false);//Yes 12 hour time

        mTimePicker.setCustomTitle(dialogView);

        mTimePicker.show();
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
            sheet.setStartTime(startTime.getText().toString());
            clearSpecificError(error_start_time);
        } else {
            endTime.setText(new StringBuilder().append(hourS).append(":").append(minS)
                    .append(" ").append(format));
            sheet.setEndTime(endTime.getText().toString());
            clearSpecificError(error_end_time);
        }
    }

    private void disableViews(boolean flag, int i) {
        if (flag) {
            spinnerProjects.setEnabled(false);
            pickerDate.setEnabled(false);
            if (i == 0) {
                startTime.setEnabled(false);
                endTime.setEnabled(false);
                descriptionET.setEnabled(false);
                saveBtn.setVisibility(View.GONE);
                modifyBtn.setVisibility(View.VISIBLE);
            } else {
                startTime.setEnabled(true);
                endTime.setEnabled(true);
                descriptionET.setEnabled(true);
                descriptionET.setSelection(descriptionET.getText().length());

                modifyBtn.setVisibility(View.GONE);
                animationFOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
                modifyBtn.setAnimation(animationFOut);

                saveBtn.setVisibility(View.VISIBLE);
                animationFIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                saveBtn.setAnimation(animationFIn);
            }
        } else {
            modifyBtn.setVisibility(View.GONE);

            saveBtn.setVisibility(View.VISIBLE);
            animationFIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            saveBtn.setAnimation(animationFIn);
        }
    }

    private void showMenu() {
        Menu menu = toolbar.getMenu();
        if (menu == null || menu.size() == 0) {
            toolbar.inflateMenu(R.menu.delete_menu);
        }
    }

    protected void clearToolbarMenu() {
        Menu menu = toolbar.getMenu();
        if (menu != null && menu.size() > 0) {
            menu.clear();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuResID = menuResID();
        boolean hasOptionMenu = (menuResID > 0);

        if (hasOptionMenu) {
            getMenuInflater().inflate(menuResID, menu);
        }

        mMenu = menu;

        return hasOptionMenu || showBackButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_menu_delete)
            presenter().removeTimeSheet(user.empCode, String.valueOf(sheet.timeSheetId));

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemCollapse = menu.findItem(R.id.action_menu_delete);
        menuItemCollapse.setVisible(sheet.date != null);
        return true;
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
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof TimeSheetResponse) {
            TimeSheetResponse sheetResponse = (TimeSheetResponse) o;
            App.getInstance().customToast(sheetResponse.getMessage());
            finish();
        } else if (o instanceof ProjectNamesResponse) {
            projectsListResponse = (ProjectNamesResponse) o;
            if (projectsListResponse.status) {
//                projectNames.add("Select Project");
                for (Project project : projectsListResponse.getProjectList()) {
                    projectNames.add(project.getProjectName());
                }
                /*
                //Creating the ArrayAdapter instance having the bank name list
                ArrayAdapter aa = new ArrayAdapter(this, R.layout.view_spinner_dropdown_item, projectNames);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spinnerProjects.setAdapter(aa);
                */

                //Custom spinner
                ProjectsSpinnerAdapter projectsSpinnerAdapter = new ProjectsSpinnerAdapter(TimeSheetEntry.this, projectNames);
                spinnerProjects.setAdapter(projectsSpinnerAdapter);
//                spinnerProjects.setPrompt("Select project");
            }
        } else if (o instanceof String) {
            String query = (String) o;
        }
    }

    @Override
    public void onFailed(Throwable e) {

    }

    public void closeKeyBoard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) TimeSheetEntry.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    boolean userSelect = false;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sheet.setProjectName(projectNames.get(i));
        clearSpecificError(error_project_name);
        userSelect = true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    BottomSheetDialog bottomSheetDialog;
    String selectedDate = "";
    Date dateSelected = null;
    HashSet<Date> events;

    private void showBottomSheet(String titleText, Integer peekHeight) {

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
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                Log.v("Click", " : " + df.format(date));
            }

            @Override
            public void onDayPress(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                selectedDate = df.format(date);
                dateSelected = date;
//                dateSelected = convertCalFormat(selectedDate);
                Log.v("Click", " : " + df.format(date));
                pickerDate.setText(df.format(date));
                sheet.setDate(df.format(date));
                clearSpecificError(error_date);

//                customToast(bottomSheetDialog.getCurrentFocus(),df.format(date));

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

                Calendar cal = new java.util.GregorianCalendar();
//                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear - 1);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                sheet.setWeekNo(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));

                selectedDate = year + "/" + monthOfYear + "/" + dayOfMonth;
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            Date date = simpleDateFormat.parse(data);
            Log.d(TAG, " test==>" + date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
