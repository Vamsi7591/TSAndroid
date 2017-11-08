package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */

public class AssignEmpParams {

    String empcode,projectname;

    public AssignEmpParams(String empcode, String projectname) {
        this.empcode = empcode;
        this.projectname = projectname;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
}
