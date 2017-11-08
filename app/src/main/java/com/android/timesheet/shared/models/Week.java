package com.android.timesheet.shared.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wks-p02-012 on 9/14/2017.
 */

public class Week implements Serializable{

    @SerializedName("commonFlag")
    private String commonflag;

    @SerializedName("duration")
    private String duration;

    @SerializedName("projectName")
    private String projectname;

    public Week(String commonflag, String duration, String projectname) {
        this.commonflag = commonflag;
        this.duration = duration;
        this.projectname = projectname;
    }

    public String getCommonflag() {
        return commonflag;
    }

    public void setCommonflag( String commonflag) {
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
}
