package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

@Parcel
public class Employee implements Serializable {

    @SerializedName("empCode")
    public String empCode;

    @SerializedName("empName")
    public String empName;

    @Nullable
    @SerializedName("empRole")
    public String empRole;

    @SerializedName("empEmailId")
    public String empEmailId;

    @SerializedName("password")
    public String password;

    @Nullable
    @SerializedName("createdBy")
    public String createdBy;

    public Employee(String empCode, String empName, String empRole, String empEmailId, String password, String createdBy) {
        this.empCode = empCode;
        this.empName = empName;
        this.empRole = empRole;
        this.empEmailId = empEmailId;
        this.password = password;
        this.createdBy = createdBy;
    }

    public Employee() {
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Nullable
    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(@Nullable String empRole) {
        this.empRole = empRole;
    }

    public String getEmpEmailId() {
        return empEmailId;
    }

    public void setEmpEmailId(String empEmailId) {
        this.empEmailId = empEmailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
    }
}
