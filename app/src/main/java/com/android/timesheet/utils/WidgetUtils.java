package com.android.timesheet.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.widget.ClickableImageSpan;
import com.android.timesheet.shared.widget.EditableDrawable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vamsikonanki on 11/25/2016.
 */

public class WidgetUtils {

    public static Spannable createSpannableFromList(Context context, List<String> stringList) {
        if (stringList == null || stringList.size() == 0) {
            return new SpannableString("");
        }

        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder();

        for (String str : stringList) {
            TextView tv = WidgetUtils.createRoundedTextView(context, str);
            BitmapDrawable bd = WidgetUtils.createTokenizeDrawable(tv);
            bd.setBounds(5, 5, tv.getWidth(), tv.getHeight());

            spannableBuilder.append(str);
            spannableBuilder.setSpan(new ImageSpan(bd), spannableBuilder.length() - (str.length()), spannableBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableBuilder.append(" ");
        }

        return spannableBuilder;
    }

    public static TextView createRoundedTextView(Context context, String text) {
        TextView tv = new TextView(context);
        tv.setText(WordUtils.capitalize(text));
        tv.setTextSize(16);
        tv.setTypeface(FontUtils.getTypeFace(context, context.getString(R.string.roboto_light)));
        tv.setTextColor(context.getResources().getColor(R.color.black));
        tv.setPadding(5, 2, 5, 2);

        //tv.setBackgroundResource(R.drawable.rounded_token_background);
        //tv.setBackgroundResource(R.drawable.bg_layout_border);
        tv.setBackgroundResource(R.drawable.bg_profile_tiles);
        return tv;
    }


    public static BitmapDrawable createTokenizeDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(5, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();

        return new BitmapDrawable(view.getContext().getResources(), viewBmp);
    }

    public static EditableDrawable createTokenizeEditableDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());


        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();

        return new EditableDrawable(view.getContext().getResources(), viewBmp);
    }

    public static ProgressDialog showProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getResources().getString(R.string.prompt_progress_title));
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    // isValidEmailAddress: Check the email address is OK
    public static boolean isValidEmailAddress(String emailAddress) {
        String emailRegEx;
        Pattern pattern;
        // Regex for a valid email address
        //emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";

        emailRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(emailAddress);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }


    public static void showDialog(Context context,
                                  int layout,
                                  int title,
                                  String content,
                                  int negativeText,
                                  int positiveText,
                                  DialogInterface.OnClickListener positiveListener) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context).setCancelable(false);

        if (title > 0) {
            dialogBuilder.setTitle(title);
        }

        if (negativeText > 0) {
            dialogBuilder.setNegativeButton(negativeText, null);
        }

        if (positiveText > 0) {
            dialogBuilder.setPositiveButton(positiveText, positiveListener);
        }

        if (layout > 0) {
            View contentView = LayoutInflater.from(context).inflate(layout, null, false);
            dialogBuilder.setView(contentView);

            if (!StringUtils.isEmpty(content)) {
//                TextView customView = (TextView) contentView.findViewById(R.id.content);
//                customView.setText(content);
            }
        } else {
            dialogBuilder.setMessage(content);
        }

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(
                ContextCompat.getColor(context, R.color.colorPrimary));

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(
                ContextCompat.getColor(context, R.color.colorAccent));
    }

    public static void showDialog(Context context,
                                  int layout,
                                  int title,
                                  String content,
                                  int negativeText,
                                  int positiveText,
                                  DialogInterface.OnClickListener negativeListener,
                                  DialogInterface.OnClickListener positiveListener) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context).setCancelable(false);
        //AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context, R.style.CustomDialogTheme).setCancelable(false);

        if (title > 0) {
            dialogBuilder.setTitle(title);
        }

        if (negativeText > 0) {
            dialogBuilder.setNegativeButton(negativeText, negativeListener);
        }

        if (positiveText > 0) {
            dialogBuilder.setPositiveButton(positiveText, positiveListener);
        }

        if (layout > 0) {
            View contentView = LayoutInflater.from(context).inflate(layout, null, false);
            dialogBuilder.setView(contentView);

            if (!StringUtils.isEmpty(content)) {
//                TextView customView = (TextView) contentView.findViewById(R.id.content);
//                customView.setText(content);
            }
        } else {
            dialogBuilder.setMessage(content);
        }

        AlertDialog alertDialog = dialogBuilder.create();
        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.colorPrimaryTransparent2)));

        alertDialog.show();

        Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(context, R.color.colorGrayDark));

        Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(context, R.color.colorGrayDark));

    }

    public static void showListDialog(Context context,
                                      int title,
                                      List<String> stringList,
                                      int negativeText,
                                      int positiveText,
                                      DialogInterface.OnClickListener positiveListener) {
        showListDialog(context, 0, title, stringList, negativeText, positiveText, positiveListener);
    }

    public static void showListDialog(Context context,
                                      int layout,
                                      int title,
                                      List<String> stringList,
                                      int negativeText,
                                      int positiveText,
                                      DialogInterface.OnClickListener positiveListener) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context).setCancelable(false);

        if (title > 0) {
            dialogBuilder.setTitle(title);
        }

        if (negativeText > 0) {
            dialogBuilder.setNegativeButton(negativeText, null);
        }

        if (positiveText > 0) {
            dialogBuilder.setPositiveButton(positiveText, positiveListener);
        }

        //Create sequence of items
        final CharSequence[] charSequencesList = stringList.toArray(new String[stringList.size()]);
        dialogBuilder.setItems(charSequencesList, positiveListener);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(
                ContextCompat.getColor(context, R.color.colorPrimary));

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(
                ContextCompat.getColor(context, R.color.colorAccent));

    }


}
