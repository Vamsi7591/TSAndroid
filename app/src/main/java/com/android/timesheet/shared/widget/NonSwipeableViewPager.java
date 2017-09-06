package com.android.timesheet.shared.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by vamsikonanki on 1/10/2017.
 */

public class NonSwipeableViewPager extends ViewPager {
    private boolean mSwipeable = false;

    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mSwipeable) {
            return super.onInterceptTouchEvent(event);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mSwipeable) {
            return super.onTouchEvent(event);
        } else {
            return false;
        }
    }

    public void setSwipeable(boolean swipeable) {
        mSwipeable = swipeable;
    }
}
