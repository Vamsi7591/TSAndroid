package com.android.timesheet.shared.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by vamsikonanki on 11/19/2016.
 */

public enum FontUtils {

    FONTLERO, RobotoLight, RobotoLightItalic, RobotoThin, RobotoThinItalic, RobotoRegular, RobotoRegularItalic, AleoRegular;

    public static Typeface getTypeFace(Context context, String font) {
        if (FONTLERO.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), FONTLERO + ".TTF");
        } else if (RobotoLight.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), RobotoLight + ".ttf");
        } else if (RobotoLightItalic.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), RobotoLightItalic + ".ttf");
        } else if (RobotoThin.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), RobotoThin + ".ttf");
        } else if (RobotoThinItalic.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), RobotoThinItalic + ".ttf");
        } else if (RobotoRegular.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), RobotoRegular + ".ttf");
        } else if (RobotoRegularItalic.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), RobotoRegularItalic + ".ttf");
        } else if (AleoRegular.toString().toLowerCase().equals(font.toLowerCase())) {
            return Typeface.createFromAsset(context.getAssets(), AleoRegular + ".ttf");
        } else {
            return Typeface.SANS_SERIF;
        }
    }

}
