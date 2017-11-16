package com.android.timesheet.user_operations.timesheet.sheet_list;

import android.content.Context;
import android.content.Intent;

import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.routers.BaseRouter;
import com.android.timesheet.user_operations.timesheet.sheet_date_summary.HeaderActivity;
import com.android.timesheet.user_operations.timesheet.sheet_entry.TimeSheetEntry;

import org.parceler.Parcels;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetRouter extends BaseRouter{

    public TimeSheetRouter(Context context) {
        super(context);
    }

    public void openTimeSheet(TimeSheet sheet) {
        Intent intent = new Intent(context, TimeSheetEntry.class);
        intent.putExtra(Constant.KEYS.TIME_SHEET_DETAIL_KEY, Parcels.wrap(sheet));
        context.startActivity(intent);
    }

    public void dayTimeSheet(String  sheet) {
        Intent intent = new Intent(context, HeaderActivity.class);
        intent.putExtra(Constant.KEYS.TIME_SHEET_HEADER_KEY, sheet);
        context.startActivity(intent);
    }
}
