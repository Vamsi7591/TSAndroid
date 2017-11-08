package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetResponse {

    @SerializedName("status")
    public boolean status;

    @SerializedName("code")
    public int code;

    @Nullable
    @SerializedName("message")
    public String message;

    @Nullable
    @SerializedName("data")
    public List<TimeSheet> timeSheetList = new ArrayList<>();


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    @Nullable
    public List<TimeSheet> getTimeSheetList() {
        return timeSheetList;
    }

    public void setTimeSheetList(@Nullable List<TimeSheet> timeSheetList) {
        this.timeSheetList = timeSheetList;
    }
}
