package com.android.timesheet.shared;

/**
 * Created by vamsikonanki on 18/8/17.
 */
public class Constant {

    //region Custom Values
    public static final int MINIMUM_AGE = 18;

    public static final int MAXIMUM_AGE = 45;

    public static final int MINIMUM_PASSWORD_LENGTH = 6;

    public static final String StorageDirectory = "Android";

    public static final String CameraResult = "CameraResult.png";

    public static final String GalleryOutput = "GalleryOutput.png";
    //endregion

    //region Request Codes
    public interface REQUEST_CODE {

        int PICTURE_CHOOSER = 1002;

        int PERMISSIONS_REQUEST_CODE = 1003;

        int PERMISSION_READ_PHONE_STATE = 1018;

        int PERMISSIONS_MULTIPLE_REQUEST = 1020;

        int READ_EXTERNAL_STORAGE = 1021;
    }
    //endregion


    //region Preferences Keys
    public interface PREFS {

        String DEVICE_ID = "PREF_DEVICE_ID";

        String USER_TOKEN = "PREF_USER_TOKEN";

        String APP_OPENED = "APP_OPENED";

        String NOTIFICATION_COUNT = "NOTIFICATION_COUNT";

    }
    //endregion


    //region Custom Keys
    public interface KEYS {

        String X_API_AUTH = "X-API-Auth";

        String X_API_TOKEN = "X-API-Token";

        String X_API_DEVICE = "X-API-Device";

        String PROFILE_PARCEL_KEY = "PROFILE_PARCEL_KEY";

        String CURRENT_POSITION = "CURRENT_POSITION";

        String FRAGMENT_TITLE = "FRAGMENT_TITLE";

        String REQUEST_CODE = "REQUEST_CODE";

        String WEB_VIEW_TITLE = "WEB_VIEW_TITLE";

        String WEB_VIEW_URL = "WEB_VIEW_URL";

        String GCM_TOKEN = "GCM_TOKEN";

        String IS_NOTIFICATION = "IS_NOTIFICATION";

        String NOTIFICATION_KEY = "NOTIFICATION_KEY";

        String TIME_SHEET_DETAIL_KEY = "TIME_SHEET_DETAIL_KEY";

        String TIME_SHEET_HEADER_KEY = "TIME_SHEET_HEADER_KEY";
    }
    //endregion

    public interface NOTIFICATIONS {
        String PUBLIC = "NOTIFICATION_PUBLIC";
        String PRIVATE = "NOTIFICATION_PRIVATE";
    }
}
