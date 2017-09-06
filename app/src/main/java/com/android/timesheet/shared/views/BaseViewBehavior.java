package com.android.timesheet.shared.views;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public interface BaseViewBehavior<U> {

    void onLoading();

    void onComplete();

    void onSuccess(U data);

    void onFailed(Throwable e);
}
