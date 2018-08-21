package com.android.timesheet.shared.models;

import android.net.ParseException;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Parcel
public class LeaveEntry implements Serializable {

    @Nullable
    @SerializedName("leaveEntryId")
    public long leaveEntryId;

    @Nullable
    @SerializedName("fromDate")
    public String fromDate;

    @SerializedName("toDate")
    public String toDate;

    @SerializedName("leaveType")
    public String leaveType;

    @SerializedName("remarks")
    public String remarks;

    @SerializedName("approvedDate")
    public String approvedDate;

    @SerializedName("approvedRemarks")
    public String approvedRemarks;

    @Nullable
    @SerializedName("empCode")
    public String empCode;

    @Nullable
    @SerializedName("status")
    public String status;

    @Nullable
    @SerializedName("noOfDays")
    public String noOfDays;

    public LeaveEntry(String fromDate, String toDate, String leaveType, String remarks, String empCode, String noOfDays) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.leaveType = leaveType;
        this.remarks = remarks;
        this.empCode = empCode;
        this.noOfDays = noOfDays;
    }

    public LeaveEntry() {
    }

    public HashMap<ValidationError, Integer> validateLeaveEntry() throws java.text.ParseException {
        HashMap<ValidationError, Integer> errors = new HashMap<>();

        if (TextUtils.isEmpty(leaveType)) {
            errors.put(ValidationError.LEAVE_TYPE, R.string.error_leave_type);
        }

        if (TextUtils.isEmpty(fromDate)) {
            errors.put(ValidationError.FROM_DATE, R.string.error_from_date);
        }

        if (TextUtils.isEmpty(toDate)) {
            errors.put(ValidationError.TO_DATE, R.string.error_to_date);
        }

        if (TextUtils.isEmpty(remarks)) {
            errors.put(ValidationError.REMARKS, R.string.error_remarks);
        }

        if (!TextUtils.isEmpty(fromDate) && !TextUtils.isEmpty(toDate)) {
            int error = validDate(fromDate, toDate);
            if (error == 1) {
                errors.put(ValidationError.TO_DATE, R.string.error_invalid_date_1);
            } else if (error == 2) {
                errors.put(ValidationError.FROM_DATE, R.string.error_invalid_date_2);
            }
        }

        return errors;
    }

    private int validDate(String fromDate, String toDate) throws java.text.ParseException {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.DataFormat);//"yyyy/MM/dd"
            Date date1 = sdf.parse(fromDate);
            Date date2 = sdf.parse(toDate);
            int i = date1.compareTo(date2);
            switch (i) {
                case -1: //date1<date2 = -1
                    return i;
                case 1: //date1>date2 = 1
                    return i;
                case 0: //date1==date2= 0
                default:
                    return i;
            }
        } catch (ParseException e) {
            return -1;
        }
    }

    @Nullable
    public long getLeaveEntryId() {
        return leaveEntryId;
    }

    public void setLeaveEntryId(@Nullable long leaveEntryId) {
        this.leaveEntryId = leaveEntryId;
    }

    @Nullable
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(@Nullable String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedRemarks() {
        return approvedRemarks;
    }

    public void setApprovedRemarks(String approvedRemarks) {
        this.approvedRemarks = approvedRemarks;
    }

    @Nullable
    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(@Nullable String empCode) {
        this.empCode = empCode;
    }

    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(@Nullable String noOfDays) {
        this.noOfDays = noOfDays;
    }
}
