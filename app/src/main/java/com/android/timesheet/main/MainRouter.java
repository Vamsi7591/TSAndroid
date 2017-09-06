package com.android.timesheet.main;

import android.content.Context;
import android.content.Intent;

import com.android.timesheet.shared.routers.BaseRouter;
import com.android.timesheet.user.sheet_entry.TimeSheetEntry;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class MainRouter extends BaseRouter {
    public MainRouter(Context context) {
        super(context);
    }

    public void openTimeSheet() {
        Intent intent = new Intent(context, TimeSheetEntry.class);
        context.startActivity(intent);
    }
}
