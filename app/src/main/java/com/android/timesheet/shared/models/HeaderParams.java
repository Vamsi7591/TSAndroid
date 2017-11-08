package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */

public class HeaderParams extends User {

    public String empcode;
    public String date;

    public HeaderParams(String empcode, String date) {
        this.empcode = empcode;
        this.date = date;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
