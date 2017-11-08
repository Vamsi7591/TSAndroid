package com.android.timesheet.shared.models;

/**
 * Created by Vijay on 20.07.2017
 */

public class AddEmployeeParams {

    public String adminempcode;
    public String empName;
    public String empemailid;
    public String password;
    public String emprole;


    public AddEmployeeParams(String adminempcode, String empName, String empemailid, String password, String emprole) {
        this.adminempcode = adminempcode;
        this.empName = empName;
        this.empemailid = empemailid;
        this.password = password;
        this.emprole = emprole;
    }


    public String getAdminempcode() {
        return adminempcode;
    }

    public void setAdminempcode(String adminempcode) {
        this.adminempcode = adminempcode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpemailid() {
        return empemailid;
    }

    public void setEmpemailid(String empemailid) {
        this.empemailid = empemailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmprole() {
        return emprole;
    }

    public void setEmprole(String emprole) {
        this.emprole = emprole;
    }
}
