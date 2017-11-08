package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.android.timesheet.shared.database.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by vamsikonanki on 8/18/2017.
 */
@Table(database = AppDatabase.class, name = "user")
@Parcel(analyze = {User.class})
public class User extends BaseModel implements Serializable {

    @PrimaryKey

    @SerializedName("adminempcode")
    @Column(name = "adminempcode")
    public String adminempcode;

    @SerializedName("empName")
    @Column(name = "empName")
    public String empName;

    @SerializedName("empEmailid")
    @Column(name = "empEmailid")
    public String emailId;

    @SerializedName("password")
    @Column(name = "password")
    public String password;

    @SerializedName("empRole")
    @Column(name = "empRole")
    public String empRole;

    @SerializedName("empCode")
    @Column(name = "empCode")
    public String empCode;

    @SerializedName("date")
    @Column(name = "date")
    public String date;

    @SerializedName("projectName")
    @Column (name = "projectName")
    public String projectName;



    public User() {

    }

    public User(@Nullable  String adminempcode , String empName, String emailId,String password,
                String empRole,String empCode ,String projectName ) {

        this.adminempcode = adminempcode;
        this.empName = empName;
        this.emailId = emailId;
        this.password = password;
        this.empRole = empRole;
        this.empCode = empCode;
        this.projectName = projectName;

    }

    public String getAdminempcode() {
        return adminempcode;
    }

    public void setAdminempcode(String adminempcode) {
        this.adminempcode = adminempcode;
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

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "User{" +
                "empCode='" + empCode + '\'' +
                ", empName='" + empName + '\'' +
                ", empRole='" + empRole + '\'' +
                ", emailId='" + emailId + '}';
    }

    @Override
    public boolean save() {
        return super.save();
    }

    public void updateUser(User user) {
        this.empCode = user.empCode;
        this.empName = user.empName;
        this.empRole = user.empRole;
        this.emailId = user.emailId;
        save();
    }


}
