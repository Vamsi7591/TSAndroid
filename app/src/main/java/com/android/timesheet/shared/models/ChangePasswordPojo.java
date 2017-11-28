package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wks-p02-012 on 9/19/2017.
 */

public class ChangePasswordPojo implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @Nullable
    @SerializedName("message")
    private String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public void setMessage( @Nullable String message) {
        this.message = message;
    }
}
