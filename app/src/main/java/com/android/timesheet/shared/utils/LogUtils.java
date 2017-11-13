package com.android.timesheet.shared.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vamsikonanki on 11/23/2016.
 */

public class LogUtils {

    private static final String LOG_PREFIX = "TimeSheet:";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    private static String tagLogger(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            // Returns end of class name
            return LOG_PREFIX + str.substring(MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH);
        }

        return LOG_PREFIX + str;
    }

    public static void LOG(Object message) {
        String className = new Throwable().getStackTrace()[1].getClassName();
        String tag = tagLogger(className);

        if (message != null) {
            Log.d(tag, message.toString());
        }
    }

    public static void WTF(Object message) {
        String className = new Throwable().getStackTrace()[1].getClassName();
        String tag = tagLogger(className);

        if (message != null) {
            Log.wtf(tag, message.toString());
        }
    }


    public static void logEvent(String name) {
        logEvent(name, null);
    }

    public static void logEvent(String name, String message) {
        if (message != null) {
            Map<String, Object> args = new HashMap();
            if (message != null) {
                args.put("msg", message);
            }
//            Appsee.addEvent(name, args);

            LogUtils.LOG(name + "\n" + message);
        } else {
//            Appsee.addEvent(name);
        }
    }
}
