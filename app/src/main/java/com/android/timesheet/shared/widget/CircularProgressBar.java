package com.android.timesheet.shared.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.android.timesheet.R;


/**
 * Created by vamsikonanki on 12/1/2016.
 */

public class CircularProgressBar extends View {

    private CircularProgressDrawable mDrawable;

    public CircularProgressBar(Context context) {
        this(context, null);
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int circleColor = ContextCompat.getColor(getContext(), android.R.color.darker_gray);
        int borderWidth = 4;

        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CircularProgressBar, 0, 0);
            try {
                circleColor = typedArray.getColor(R.styleable.CircularProgressBar_circleColor, circleColor);
            } finally {
                typedArray.recycle();
            }

            assert (borderWidth > 0);
        }

        mDrawable = new CircularProgressDrawable(circleColor, borderWidth);
        mDrawable.setCallback(this);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (mDrawable == null) {
            return;
        }

        if (visibility == VISIBLE) {
            mDrawable.start();
        }
        else {
            mDrawable.stop();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDrawable.setBounds(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mDrawable.draw(canvas);
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return who == mDrawable || super.verifyDrawable(who);
    }

    public void start() {
        setVisibility(VISIBLE);
        mDrawable.start();
    }

    public void stop() {
        setVisibility(GONE);
        mDrawable.stop();
    }
}