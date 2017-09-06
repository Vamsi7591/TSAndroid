package com.android.timesheet.user.sheet_entry;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.timesheet.R;
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
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetEntry extends BaseActivity<TimeSheetEntryPresenter> implements BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {


    @BindView(R.id.textViewToolbarTitle)
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

    @BindView(R.id.modifyB)
    Button modifyB;

    @BindView(R.id.saveB)
    Button saveB;

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


    String TAG = "TimeSheetEntry";

    TimeSheet sheet;
    User user;
    ProjectNamesResponse projectList;

    //    List<String> projectNames = new ArrayList<>();
    ArrayList<String> projectNames = new ArrayList<>();
    private int height;
    private int width;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_time_sheet_details;
    }

    @Override
    protected int menuResID() {
        return R.menu.delete_menu;
    }

    @Override
    protected String title() {
        return "Time Sheet";
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

        textViewToolbarTitle.setText(title());
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        spinnerProjects.setOnItemSelectedListener(this);

        user = presenter().getCurrentUser();

        if (sheet != null) {
            Log.d(TAG, "TS : " + sheet.date);
            if (mMenu == null) {
                showMenu();
            }

            projectNames.add(sheet.projectName);
            //Custom spinner
            CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(TimeSheetEntry.this, projectNames);
            spinnerProjects.setAdapter(customSpinnerAdapter);

            pickerDate.setText(sheet.date);

//            startTime.setText(sheet.startTime);
            showTime(Integer.parseInt(sheet.startTime.substring(0, 2)), Integer.parseInt(sheet.startTime.substring(3, 5)), true);

//            endTime.setText(sheet.endTime);
            showTime(Integer.parseInt(sheet.endTime.substring(0, 2)), Integer.parseInt(sheet.endTime.substring(3, 5)), false);

            descriptionET.setText(sheet.taskDescription);
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

        closeKeyBoard();
    }

    @OnClick(R.id.modifyB)
    void modify() {
        disableViews(true, 1);
    }

    @OnClick(R.id.saveB)
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
    }

    void clearErrors() {
        error_project_name.setVisibility(View.GONE);
        error_date.setVisibility(View.GONE);
        error_start_time.setVisibility(View.GONE);
        error_end_time.setVisibility(View.GONE);
        error_description.setVisibility(View.GONE);
    }


    @OnClick(R.id.pickerDate)
    void showCustomDatePicker() {
        showBottomSheet("Date", height / 2 + 150);
    }


    @OnClick(R.id.startTime)
    void showStartTimeClock() {
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.title_layout, null);
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
        View dialogView = inflater.inflate(R.layout.title_layout, null);
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
        } else {
            endTime.setText(new StringBuilder().append(hourS).append(":").append(minS)
                    .append(" ").append(format));
            sheet.setEndTime(endTime.getText().toString());
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
                saveB.setVisibility(View.GONE);
                modifyB.setVisibility(View.VISIBLE);
            } else {
                startTime.setEnabled(true);
                endTime.setEnabled(true);
                descriptionET.setEnabled(true);
                saveB.setVisibility(View.VISIBLE);
                modifyB.setVisibility(View.GONE);
            }
        } else {
            saveB.setVisibility(View.VISIBLE);
            modifyB.setVisibility(View.GONE);
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
            //Instance of TimeSheetResponse
            TimeSheetResponse sheetResponse = (TimeSheetResponse) o;
            Toast.makeText(this, sheetResponse.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        } else if (o instanceof ProjectNamesResponse) {
            //Instance of TimeSheetResponse
            projectList = (ProjectNamesResponse) o;
            if (projectList.status) {
//                projectNames.add("Select Project");
                for (Project project : projectList.getProjectList()) {
                    projectNames.add(project.getProjectName());
                }

                /*
                //Creating the ArrayAdapter instance having the bank name list
                ArrayAdapter aa = new ArrayAdapter(this, R.layout.project_name_spinner_item, projectNames);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spinnerProjects.setAdapter(aa);
                */

                //Custom spinner
                CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(TimeSheetEntry.this, projectNames);
                spinnerProjects.setAdapter(customSpinnerAdapter);

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
        View view = TimeSheetEntry.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    boolean userSelect = false;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(), projectNames.get(i), Toast.LENGTH_LONG).show();

//        if (userSelect)
        sheet.setProjectName(projectNames.get(i));

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

        View view = this.getLayoutInflater().inflate(R.layout.bottom_sheet_modal, null);
        com.android.timesheet.shared.widget.calender.CalendarView
                calendarView = (com.android.timesheet.shared.widget.calender.CalendarView) view.findViewById(R.id.calenderView);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(titleText);

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
//        if (peekHeight != 0)
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
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Log.v("Click", " : " + df.format(date));
            }

            @Override
            public void onDayPress(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                selectedDate = df.format(date);
                dateSelected = date;
//                dateSelected = convertCalFormat(selectedDate);
                Log.v("Click", " : " + df.format(date));
                pickerDate.setText(df.format(date));
                sheet.setDate(df.format(date));
//                customToast(bottomSheetDialog.getCurrentFocus(),df.format(date));

                int dayOfMonth = 0;
                int monthOfYear = 0;
                int year = 0;

                if (!selectedDate.isEmpty()) {//mUser.dateOfBirth

                    String[] dobList = selectedDate.split("/");
                    dayOfMonth = Integer.parseInt(dobList[0]);
                    monthOfYear = (Integer.parseInt(dobList[1]));
                    year = Integer.parseInt(dobList[2]);

                } else {
                    return;
                }

                Calendar cal = new java.util.GregorianCalendar();
//                Calendar cal = Calendar.getInstance();
                cal.clear();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear -1);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                sheet.setWeekNo(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));

                /*int _m = monthOfYear;
                String strDob = dayOfMonth + "/" + _m + "/" + year;

                Calendar calendar = Calendar.getInstance();

                int mAge = calendar.get(Calendar.YEAR) - cal.get(Calendar.YEAR);*/

                selectedDate = dayOfMonth + "/" + monthOfYear + "/" + year;

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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = simpleDateFormat.parse(data);
            Log.d(TAG, " test==>" + date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // My custom toast
    public void customToast(View view, String message) {
        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();

        View customToastRoot = inflater.inflate(R.layout.custom_toast, null);

        TextView messageText = (TextView) customToastRoot.findViewById(R.id.messageTV);
        messageText.setText(message);

        Toast customToast = new Toast(context);
        customToast.setView(customToastRoot);
        customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.show();
    }

}
