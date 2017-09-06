package com.android.timesheet.shared.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.util.FontUtils;


/**
 * Created by vamsikonanki on 12/23/2016.
 */

public class CustomFontTextView extends android.support.v7.widget.AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) return;
        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) return;
        applyCustomFont(context, attrs);
    }


    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);

        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_fonts);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName != null)
            if (fontName.contentEquals(context.getString(R.string.roboto_thin_italic))) {
                return FontUtils.getTypeFace(context, "RobotoThinItalic");
            } else if (fontName.contentEquals(context.getString(R.string.roboto_thin))) {
                return FontUtils.getTypeFace(context, "RobotoThin");
            } else if (fontName.contentEquals(context.getString(R.string.roboto_light_italic))) {
                return FontUtils.getTypeFace(context, "RobotoLightItalic");
            } else if (fontName.contentEquals(context.getString(R.string.roboto_light))) {
                return FontUtils.getTypeFace(context, "RobotoLight");
            } else if (fontName.contentEquals(context.getString(R.string.font_lero))) {
                return FontUtils.getTypeFace(context, "FONTLERO");
            } else if (fontName.contentEquals(context.getString(R.string.roboto_regular_italic))) {
                return FontUtils.getTypeFace(context, "RobotoRegularItalic");
            } else if (fontName.contentEquals(context.getString(R.string.aleo_regular))) {
                return FontUtils.getTypeFace(context, "AleoRegular");
            } else {
                // no matching font found
                // return null so Android just uses the standard font (Roboto)
//            return FontUtils.getTypeFace(context, "FONTLERO");
                return FontUtils.getTypeFace(context, "RobotoRegular");
            }
        else
            return FontUtils.getTypeFace(context, "RobotoRegular");
    }
}