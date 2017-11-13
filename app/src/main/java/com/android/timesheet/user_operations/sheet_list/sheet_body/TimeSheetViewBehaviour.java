package com.android.timesheet.user_operations.sheet_list.sheet_body;

import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.List;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public interface TimeSheetViewBehaviour extends BaseViewBehavior<List<TimeSheet>>{

    void fetchDayToDayTimeSheet();

    void onDisconnected();

    void reloadTimeSheet();

    void onTimeSheetDeleted(int position);

    void removedTimeSheet(TimeSheetResponse response);
}
