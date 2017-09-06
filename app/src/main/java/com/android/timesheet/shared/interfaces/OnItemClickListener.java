package com.android.timesheet.shared.interfaces;

import android.view.View;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public interface OnItemClickListener {

    void onItemClick(View view, int position);

    abstract void onItemClickToDelete(View view, int position);
}
