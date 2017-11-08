package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */




public class AddProjectParams {

    public String projectcode;
    public String projectname;
    public Boolean commonflag;

    public AddProjectParams( String projectcode,String projectname, Boolean commonflag) {
        this.projectcode = projectcode;
        this.projectname = projectname;
        this.commonflag = commonflag;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
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