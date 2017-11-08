package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */

public class RemoveEmployeeParams {

    String adminempcode,empcode;

    public RemoveEmployeeParams(String adminempcode, String empcode) {
        this.adminempcode = adminempcode;
        this.empcode = empcode;
    }

    public String getAdminempcode() {
        return adminempcode;
    }

    public void setAdminempcode(String adminempcode) {
        this.adminempcode = adminempcode;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }
}
