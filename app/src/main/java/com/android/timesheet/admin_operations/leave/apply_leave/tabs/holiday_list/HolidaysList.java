package com.android.timesheet.admin_operations.leave.apply_leave.tabs.holiday_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.timesheet.R;

public class HolidaysList extends Fragment {

    //Overridden method onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.fragment_holiday_list, container, false);
    }

}
