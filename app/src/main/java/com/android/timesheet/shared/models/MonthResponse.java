package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wks-p02-012 on 9/18/2017.
 */

public class MonthResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private int code;

    @Nullable
    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("data")
    private List<Month> data = null;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(  @Nullable String message) {
        this.message = message;
    }

    @Nullable
    public List<Month> getData() {
        return data;
    }

    public void setData(@Nullable List<Month> data) {
        this.data = data;
    }
}
