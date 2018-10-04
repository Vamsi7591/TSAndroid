package com.android.timesheet.shared.models;

import java.util.Date;

public class LeaveCalendarModel {

    private String leaveType;

    private Date leaveDate;

    public LeaveCalendarModel(String leaveType, Date leaveDate) {
        this.leaveType = leaveType;
        this.leaveDate = leaveDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }
}
