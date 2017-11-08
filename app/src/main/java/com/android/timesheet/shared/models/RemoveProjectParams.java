package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */

public class RemoveProjectParams {

    String projectName;

    public RemoveProjectParams(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
