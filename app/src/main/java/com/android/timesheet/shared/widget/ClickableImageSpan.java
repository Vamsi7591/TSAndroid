package com.android.timesheet.shared.widget;

import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.view.View;


/**
 * Created by vamsikonanki on 7/18/16.
 */
public class ClickableImageSpan extends ImageSpan {

    private EditableDrawable editable;

    public String text;

    public boolean isSelected;

    public ClickableImageSpan(Drawable drawable, String str) {
        super(drawable);
        editable = (EditableDrawable) drawable;
        text = str;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void onSelect(View view) {
        isSelected = true;

        ((TokenizeTextView) view).setSelection(this);
    }

    public void onClick(View view) {
        if (isSelected) {
            ((TokenizeTextView) view).remove(this);
        }
    }

    @Override
    public EditableDrawable getDrawable() {
        return (EditableDrawable) super.getDrawable();
    }
}
