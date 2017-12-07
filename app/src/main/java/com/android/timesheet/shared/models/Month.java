package com.android.timesheet.shared.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wks-p02-012 on 9/18/2017.
 */

public class Month {

    @SerializedName("commonFlag")
    private String commonflag;

    @SerializedName("duration")
    private String duration;

    @SerializedName("projectName")
    private String projectname;

    @SerializedName("weekNo")
    private Integer weekno;

    public Month(String commonflag, String duration, String projectname, Integer weekno) {
        this.commonflag = commonflag;
        this.duration = duration;
        this.projectname = projectname;
        this.weekno = weekno;
    }

    public String getCommonflag() {
        return commonflag;
    }

    public void setCommonflag(String commonflag) {
        this.commonflag = commonflag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Integer getWeekno() {
        return weekno;
    }

    public void setWeekno(Integer weekno) {
        this.weekno = weekno;
    }
}
