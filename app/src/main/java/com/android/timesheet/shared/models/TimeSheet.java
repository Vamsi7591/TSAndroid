package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.timesheet.R;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by vamsikonanki on 8/22/2017.
 */
@Parcel
public class TimeSheet implements Serializable {

    public static final int TYPE_HEADER = 0x01;
    public static final int TYPE_BODY = 0x02;

    @Nullable
    @SerializedName("timesheetId")
    public long timeSheetId;

    @Nullable
    @SerializedName("date")
    public String date;

    @SerializedName("startTime")
    public String startTime;

    @SerializedName("projectName")
    public String projectName;

    @SerializedName("endTime")
    public String endTime;

    @SerializedName("taskDescription")
    public String taskDescription;

    @SerializedName("totalHours")
    public String totalHours;

    @Nullable
    @SerializedName("empCode")
    public String empCode;

    @Nullable
    @SerializedName("weekNo")
    public String weekNo;

    public String header;

    public int rowType;

    public TimeSheet( @Nullable long timeSheetId,  @Nullable String date, String startTime, String projectName, String endTime, String taskDescription, String totalHours) {
        this.timeSheetId = timeSheetId;
        this.date = date;
        this.startTime = startTime;
        this.projectName = projectName;
        this.endTime = endTime;
        this.taskDescription = taskDescription;
        this.totalHours = totalHours;
    }

    public TimeSheet() {
    }

    public TimeSheet(String header) {
        this.header = header;
        setRowType(TYPE_HEADER);
    }

    public TimeSheet(@Nullable String date, String startTime, String projectName, String endTime, String taskDescription, String empCode, String weekNo) {
        this.date = date;
        this.startTime = startTime;
        this.projectName = projectName;
        this.endTime = endTime;
        this.taskDescription = taskDescription;
        this.empCode = empCode;
        this.weekNo = weekNo;
    }



    @Nullable
    public long getTimeSheetId() {
        return timeSheetId;
    }

    public void setTimeSheetId(@Nullable long timeSheetId) {
        this.timeSheetId = timeSheetId;
    }
    @Nullable
    public String getDate() {
        return date;
    }
    @Nullable
    public void setDate(@Nullable String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public TimeSheet(  @Nullable long timeSheetId, String date, String startTime, String projectName, String endTime, String taskDescription, String totalHours, String header) {
        this.timeSheetId = timeSheetId;
        this.date = date;
        this.startTime = startTime;
        this.projectName = projectName;
        this.endTime = endTime;
        this.taskDescription = taskDescription;
        this.totalHours = totalHours;
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getRowType() {
        return rowType;
    }

    public void setRowType(int rowType) {
        this.rowType = rowType;
    }

    @Nullable
    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(@Nullable String empCode) {
        this.empCode = empCode;
    }

    @Nullable
    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(@Nullable String weekNo) {
        this.weekNo = weekNo;
    }

    public HashMap<ValidationError, Integer> validateTimeSheetEntry() {
        HashMap<ValidationError, Integer> errors = new HashMap<>();

        if (TextUtils.isEmpty(projectName)) {
            errors.put(ValidationError.PROJECT_NAME, R.string.error_project_required);
        }

        if (TextUtils.isEmpty(date)) {
            errors.put(ValidationError.DATE, R.string.error_date_required);
        }

        if (TextUtils.isEmpty(startTime)) {
            errors.put(ValidationError.START_TIME, R.string.error_start_required);
        }

        if (TextUtils.isEmpty(endTime)) {
            errors.put(ValidationError.END_TIME, R.string.error_end_required);
        }

        if (TextUtils.isEmpty(taskDescription)) {
            errors.put(ValidationError.DESCRIPTION, R.string.error_desc_required);
        }


        return errors;
    }




}
