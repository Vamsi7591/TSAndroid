package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */

public class ProjectSum_Params {

    String empCode;
    String projectname;
    String year;

    public ProjectSum_Params(String empCode, String projectname, String year) {
        this.empCode = empCode;
        this.projectname = projectname;
        this.year = year;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}