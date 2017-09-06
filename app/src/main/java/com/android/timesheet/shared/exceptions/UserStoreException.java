package com.android.timesheet.shared.exceptions;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class UserStoreException extends RuntimeException{

    public UserStoreException() {
        super("This device is used by more than one account");
    }
}
