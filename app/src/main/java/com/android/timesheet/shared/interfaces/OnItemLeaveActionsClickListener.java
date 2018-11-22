package com.android.timesheet.shared.interfaces;

import android.view.View;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public interface OnItemLeaveActionsClickListener {

    void onItemClick(View view, int position);

    abstract void onItemClickToAccept(View view, int position);
    abstract void onItemClickToReject(View view, int position);
}
