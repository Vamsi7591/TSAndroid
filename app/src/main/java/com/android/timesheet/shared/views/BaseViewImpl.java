package com.android.timesheet.shared.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.android.timesheet.shared.presenters.BasePresenter;


/**
 * Created by vamsikonanki on 7/8/16.
 */
public abstract class BaseViewImpl<P extends BasePresenter> extends RelativeLayout {

    private P mPresenter;

    protected Context context;

    public BaseViewImpl(Context context) {
        super(context);
        this.context = context;

        initialize();
    }

    public BaseViewImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        onAttributes(attrs);
        initialize();
    }

    public BaseViewImpl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        onAttributes(attrs);
        initialize();
    }

    public void onAttributes(AttributeSet attrs) {

    }

    public abstract void initialize();

    public P presenter() {
        if (mPresenter == null) {
            mPresenter = providePresenter();
        }

        return mPresenter;
    }

    protected abstract P providePresenter();
}
