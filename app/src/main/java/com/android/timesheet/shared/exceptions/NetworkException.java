package com.android.timesheet.shared.exceptions;

import org.apache.commons.lang3.StringUtils;


/**
 * Created by vamsikonanki on 8/27/16.
 */
public class NetworkException extends Throwable {

    public int code;

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(int code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return StringUtils.capitalize(super.getMessage());
    }
}
