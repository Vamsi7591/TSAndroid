package com.android.timesheet.shared.events;

import com.android.timesheet.shared.utils.LogUtils;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class TimeSheetValidEvent extends BusEvent {

    public TimeSheetValidEvent(boolean dataAvailable) {
        super(dataAvailable);

        if (dataAvailable) {
            LogUtils.LOG("Data Available");
        }
        else {
            LogUtils.LOG("Data Not Available");
        }
    }
}
