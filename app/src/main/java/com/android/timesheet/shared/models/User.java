package com.android.timesheet.shared.models;

import android.support.annotation.Nullable;

import com.android.timesheet.shared.database.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelView;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by vamsikonanki on 8/18/2017.
 */
@Table(database = AppDatabase.class, name = "user")
@Parcel(analyze={User.class})
public class User extends BaseModel implements Serializable {

    @PrimaryKey
    @SerializedName("empCode")
    @Column(name = "empCode")
    public String empCode;

    @SerializedName("empName")
    @Column(name = "empName")
    public String empName;

    @SerializedName("empRole")
    @Column(name = "empRole")
    public String empRole;

    @SerializedName("empEmailid")
    @Column(name = "empEmailid")
    public String emailId;

    public User() {
    }

    public User(String empCode, String empName, String empRole, String emailId) {
        this.empCode = empCode;
        this.empName = empName;
        this.empRole = empRole;
        this.emailId = emailId;
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
