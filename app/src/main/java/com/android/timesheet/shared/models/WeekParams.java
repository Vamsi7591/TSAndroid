package com.android.timesheet.shared.models;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by wks-p02-012 on 9/16/2017.
 */

@Parcel
public class WeekParams implements Serializable {

    String empCode;
    int weekNo, year;

    public WeekParams(String empCode, int weekNo, int year) {
        this.empCode = empCode;
        this.weekNo = weekNo;
        this.year = year;
    }

    public WeekParams() {
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
