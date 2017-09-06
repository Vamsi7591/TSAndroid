package com.android.timesheet.shared.widget;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vamsikonanki on 7/26/16.
 */
public class TokenizeTextView extends TextView {

    private OnEditListener mListener;

    public TokenizeTextView(Context context) {
        super(context);
    }

    public TokenizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TokenizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TokenizeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    public void remove(ClickableImageSpan imageSpan) {
        List<String> builder = new ArrayList<>();

        SpannableString spannable = ((SpannableString) getText());
        ClickableImageSpan[] span = spannable.getSpans(0, getText().length(), ClickableImageSpan.class);

        for (int i = 0; i < span.length; i++) {
            ClickableImageSpan cSpan = span[i];

            boolean deleted = (cSpan.text.equals(imageSpan.text) && cSpan.isSelected);
            if (!deleted) {
                builder.add(cSpan.text);
            }
        }

        if (mListener != null) {
            mListener.onRedraw(this, builder);
        }
    }

    public void setSelection(ClickableImageSpan imageSpan) {
        CharSequence charSequence = getText();
        ClickableImageSpan[] span = ((SpannableString) charSequence).getSpans(0, charSequence.length(),
                ClickableImageSpan.class);

        for (int i = 0; i < span.length; i++) {
            ClickableImageSpan cSpan = span[i];

            boolean selected = (cSpan.text.equals(imageSpan.text));
            cSpan.setSelected(selected);

            cSpan.getDrawable().setEdit(selected);
        }

        requestLayout();
    }

    public void clearSelection() {
        CharSequence charSequence = getText();
        ClickableImageSpan[] span = ((SpannableString) charSequence).getSpans(0, charSequence.length(),
                ClickableImageSpan.class);

        for (int i = 0; i < span.length; i++) {
            ClickableImageSpan cSpan = span[i];
            cSpan.setSelected(false);
            cSpan.getDrawable().setEdit(false);
        }

        requestLayout();

    }

    public void onEdited(OnEditListener listener) {
        mListener = listener;
    }

    public interface OnEditListener {

        void onRedraw(TokenizeTextView textView, List<String> list);
    }
}
