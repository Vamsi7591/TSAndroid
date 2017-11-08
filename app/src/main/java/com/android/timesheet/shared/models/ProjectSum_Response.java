package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vijay on 20.07.2017
 */

public class ProjectSum_Response extends Throwable {

    @SerializedName("status")
    public boolean status;

    @SerializedName("code")
    public int code;

    @Nullable
    @SerializedName("data")
    public List<ProjectSummary> projectSummaries = new ArrayList<>();

    public ProjectSum_Response(boolean status, int code, List<ProjectSummary> projectSummaries) {
        this.status = status;
        this.code = code;
        this.projectSummaries = projectSummaries;
    }

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
    public List<ProjectSummary> getProjectSummaries() {
        return projectSummaries;
    }

    public void setProjectSummaries(@Nullable List<ProjectSummary> projectSummaries) {
        this.projectSummaries = projectSummaries;
    }
}
