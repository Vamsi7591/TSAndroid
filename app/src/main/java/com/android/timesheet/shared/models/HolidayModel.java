package com.android.timesheet.shared.models;

public class HolidayModel {

    String holiday;
    String dateOfHoliday;
    boolean isFestival;
    String description;

    public static final int TYPE_HEADER = 0x01;
    public static final int TYPE_BODY = 0x02;

    public String header;
    public int rowType;


    public HolidayModel(String holiday, String dateOfHoliday, boolean isFestival, String description) {
        this.holiday = holiday;
        this.dateOfHoliday = dateOfHoliday;
        this.isFestival = isFestival;
        this.description = description;
        setRowType(TYPE_BODY);
    }

    public HolidayModel(String header) {
        this.header = header;
        setRowType(TYPE_HEADER);
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getDateOfHoliday() {
        return dateOfHoliday;
    }

    public void setDateOfHoliday(String dateOfHoliday) {
        this.dateOfHoliday = dateOfHoliday;
    }

    public boolean isFestival() {
        return isFestival;
    }

    public void setFestival(boolean festival) {
        isFestival = festival;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getRowType() {
        return rowType;
    }

    public void setRowType(int rowType) {
        this.rowType = rowType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
