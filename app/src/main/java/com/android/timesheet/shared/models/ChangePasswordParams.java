package com.android.timesheet.shared.models;

/**
 * Created by wks-p02-012 on 9/19/2017.
 */

public class ChangePasswordParams {

    String empcode, oldpwd, newpwd,adminempcode,password,emprole;

    public ChangePasswordParams(String empcode, String oldpwd, String newpwd ) {
        this.empcode = empcode;
        this.oldpwd = oldpwd;
        this.newpwd = newpwd;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }
}
