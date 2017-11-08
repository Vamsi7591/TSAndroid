package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddProjParams {

    public String projectname;
    public Boolean commonflag;

    public AddProjParams(String projectname, Boolean commonflag) {
        this.projectname = projectname;
        this.commonflag = commonflag;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Boolean getCommonflag() {
        return commonflag;
    }

    public void setCommonflag(Boolean commonflag) {
        this.commonflag = commonflag;
    }
}
