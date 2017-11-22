package com.android.timesheet.admin_operations.summary;

import android.content.Context;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.models.ProjectSummary;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * Created by vamsi on 11/22/2017.
 */

public class BarChartMarkerView extends MarkerView {

    private TextView tvContent;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public BarChartMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        ProjectSummary summary = new ProjectSummary();
        summary = (ProjectSummary) e.getData();

        tvContent.setText(String.format("%s hours in \n%s", String.valueOf(e.getVal()).replace(".", ":"), summary.getMonth())); // set the entry-value as the display text
    }

    @Override
    public int getXOffset(float xpos) {
        if (xpos < 105) {
            return -(getWidth() / 2) + 60;
        } else if (xpos > 915) {
            return -(getWidth() / 2) - 60;
        }
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset(float ypos) {
        return -getHeight();
    }
}
