package com.android.timesheet.shared.models;

/**
 * Created by wks-p02-012 on 9/18/2017.
 */

public class MonthParams
{
    String empCode;
    int monthNo, year;

    public MonthParams(String empCode, int monthNo, int year) {
        this.empCode = empCode;
        this.monthNo = monthNo;
        this.year = year;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public int getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(int monthNo) {
        this.monthNo = monthNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
