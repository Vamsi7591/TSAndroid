package com.android.timesheet.shared.models;

/**
 * Created by wks-p02-012 on 9/20/2017.
 */

public class UpdateEmployeeParams {

String adminempcode,empcode,password,emprole;

    public UpdateEmployeeParams(String adminempcode, String empcode, String password, String emprole) {
        this.adminempcode = adminempcode;
        this.empcode = empcode;
        this.password = password;
        this.emprole = emprole;
    }

    public String getAdminempcode() {
        return adminempcode;
    }

    public void setAdminempcode(String adminempcode) {
        this.adminempcode = adminempcode;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
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
