package com.android.timesheet.user_operations.reports.monthly;

import android.content.Context;
import android.widget.TextView;

import com.android.timesheet.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * Created by Vijay on 20.07.2017
 */

public class LineChartMarkerView extends MarkerView {

    private TextView tvContent;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public LineChartMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(String.format("%s hours", String.valueOf(e.getVal()).replace(".", ":"))); // set the entry-value as the display text
    }

    @Override
    public int getXOffset(float xpos) {
        if (xpos < 80) {
            return -(getWidth() / 2) + 80;
        }else if(xpos > 950){
            return -(getWidth() / 2) - 80;
        }
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset(float ypos) {
        return -getHeight();
    }
}
