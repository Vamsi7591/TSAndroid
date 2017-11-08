package com.android.timesheet.shared.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vijay on 20.07.2017
 */

public class ProjectSummary {

    @SerializedName("common_flag")
    private String common_flag;

    @SerializedName("duration")
    private String duration;

    @SerializedName("month")
    private String month;

    public ProjectSummary(String common_flag, String duration, String month) {
        this.common_flag = common_flag;
        this.duration = duration;
        this.month = month;
    }

    public String getCommon_flag() {
        return common_flag;
    }

    public void setCommon_flag(String common_flag) {
        this.common_flag = common_flag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
