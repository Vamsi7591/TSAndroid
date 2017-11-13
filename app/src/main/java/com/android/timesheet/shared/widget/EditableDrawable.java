package com.android.timesheet.shared.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.android.timesheet.app.App;
import com.android.timesheet.R;


/**
 * Created by vamsikonanki on 7/24/16.
 */
public class EditableDrawable extends BitmapDrawable {

    Paint mPaint;

    public EditableDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    public void setEdit(boolean select) {
        if (select) {
            setState(new int[]{android.R.attr.state_selected});
        } else {
            setState(new int[]{android.R.attr.state_empty});
        }

        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        boolean selected = (getState().length > 0 && getState()[0] == android.R.attr.state_selected);
        if (selected) {
            Context context = App.getAppContext();

            final int width = getIntrinsicWidth();
            final int height = getIntrinsicHeight();

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            //mPaint.setColor(ContextCompat.getColor(context, R.color.tomato));
            mPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
            mPaint.setStyle(Paint.Style.FILL);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(0, 0, width, height, 300, 300, mPaint);
            } else {
                RectF rectF = new RectF(0, 0, width, height);
                canvas.drawRoundRect(rectF, 300, 300, mPaint);
            }


            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_delete_small);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.delete_white);

            int SIZE = bitmap.getHeight();

            int left = (width - SIZE) / 2;

            int top = (height - SIZE) / 2;

            float scaleX = 1.0f * SIZE / bitmap.getWidth();
            float scaleY = 1.0f * SIZE / bitmap.getHeight();

            Matrix matrix = new Matrix();
            matrix.postScale(scaleX, scaleY);
            matrix.postTranslate(left, top);

            canvas.drawBitmap(bitmap, matrix, mPaint);
        }
    }
}
