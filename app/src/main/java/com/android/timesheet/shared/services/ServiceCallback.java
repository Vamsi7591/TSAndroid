package com.android.timesheet.shared.services;

/**
 * Created by vamsikonanki on 5/21/16.
 */
public interface ServiceCallback<U> {

    void onFailure(Throwable e);

    void onSuccess(U data);
}