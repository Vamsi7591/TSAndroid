package com.android.timesheet.shared.models;

import android.text.TextUtils;

import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;

import java.util.HashMap;


/**
 * Created by vamsikonanki on 5/21/16.
 */
public class AuthUser {

    public String empCode;

    public String password;

    public AuthUser() {
    }

    public AuthUser(String empCode, String password) {
        this.empCode = empCode;
        this.password = password;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static AuthUser loginUser(String empCode, String password) {//Editable

        AuthUser newUser = new AuthUser();
        newUser.empCode = empCode;
        newUser.password = password;

        return newUser;
    }


    /*bUSINESS lOGIC*/
    public HashMap<ValidationError, Integer> validate() {
        HashMap<ValidationError, Integer> errors = new HashMap<>();

        /*Validate from bottom to top*/
        if (TextUtils.isEmpty(password)) {
            errors.put(ValidationError.PASSWORD, R.string.error_required_password);
        } else if (password.length() < Constant.MINIMUM_PASSWORD_LENGTH) {
            errors.put(ValidationError.PASSWORD, R.string.error_password_minimum_character);
        }

        if (TextUtils.isEmpty(empCode)) {
            errors.put(ValidationError.EMP_CODE, R.string.emp_code_required);
        }

        return errors;
    }

}
