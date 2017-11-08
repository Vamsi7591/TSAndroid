package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by vamsikonanki on 8/29/2017.
 */

@Parcel
public class Project implements Serializable {

    @SerializedName("projectCode")
    public String projectCode;

    @SerializedName("projectName")
    public String projectName;

    @Nullable
    @SerializedName("commonFlag")
    public String commonFlag;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Nullable
    public String getCommonFlag() {
        return commonFlag;
    }

    public void setCommonFlag(@Nullable String commonFlag) {
        this.commonFlag = commonFlag;
    }

    public Project(String projectCode, String projectName, @Nullable String commonFlag) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.commonFlag = commonFlag;
    }

    public Project() {
    }
}
