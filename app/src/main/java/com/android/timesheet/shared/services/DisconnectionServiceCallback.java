package com.android.timesheet.shared.services;

/**
 * Created by vamsikonanki on 10/1/16.
 */
public interface DisconnectionServiceCallback<U> extends ServiceCallback<U> {

    void onDisconnected();
}
