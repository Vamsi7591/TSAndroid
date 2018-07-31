package com.android.timesheet.shared.events;

public class TabPositionEvent extends BusEvent {

    public TabPositionEvent(int position) {
        super(position);
    }
}
