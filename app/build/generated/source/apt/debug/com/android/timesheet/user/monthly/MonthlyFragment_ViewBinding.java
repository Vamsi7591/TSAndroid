// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user.monthly;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.github.mikephil.charting.charts.LineChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MonthlyFragment_ViewBinding implements Unbinder {
  private MonthlyFragment target;

  @UiThread
  public MonthlyFragment_ViewBinding(MonthlyFragment target, View source) {
    this.target = target;

    target.monthSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_Month, "field 'monthSpinner'", Spinner.class);
    target.yearSpinner = Utils.findRequiredViewAsType(source, R.id.spinnerYear, "field 'yearSpinner'", Spinner.class);
    target.lineChart = Utils.findRequiredViewAsType(source, R.id.lineChart, "field 'lineChart'", LineChart.class);
    target.loadLine = Utils.findRequiredViewAsType(source, R.id.line_Arrow, "field 'loadLine'", ImageView.class);
    target.noDataFound = Utils.findRequiredViewAsType(source, R.id.noDataFoundRL, "field 'noDataFound'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MonthlyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.monthSpinner = null;
    target.yearSpinner = null;
    target.lineChart = null;
    target.loadLine = null;
    target.noDataFound = null;
  }
}
