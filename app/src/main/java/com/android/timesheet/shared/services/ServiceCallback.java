package com.android.timesheet.shared.services;

/**
 * Created by vamsikonanki on 5/21/16.
 */
public interface ServiceCallback<U> {

    /*U means Data model/pojo */
    void onFailure(Throwable e);

    void onSuccess(U data);
}