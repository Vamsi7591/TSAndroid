package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wks-p02-012 on 9/14/2017.
 */

public class WeekResponse {

    @SerializedName("status")
    public boolean status;

    @SerializedName("code")
    public int code;

    @Nullable
    @SerializedName("message")
    public String message;

    @Nullable
    @SerializedName("data")
    public List<Week> weekSheet = new ArrayList<>();

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
    public List<Week> getWeekSheet() {
        return weekSheet;
    }

    public void setWeekSheet(@Nullable List<Week> weekSheet) {
        this.weekSheet = weekSheet;
    }
}
