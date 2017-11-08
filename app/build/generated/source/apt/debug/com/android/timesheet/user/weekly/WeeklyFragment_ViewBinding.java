// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user.weekly;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.github.mikephil.charting.charts.PieChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WeeklyFragment_ViewBinding implements Unbinder {
  private WeeklyFragment target;

  @UiThread
  public WeeklyFragment_ViewBinding(WeeklyFragment target, View source) {
    this.target = target;

    target.weekSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_week, "field 'weekSpinner'", Spinner.class);
    target.yearSpinner = Utils.findRequiredViewAsType(source, R.id.year_Spinner, "field 'yearSpinner'", Spinner.class);
    target.weekChart = Utils.findRequiredViewAsType(source, R.id.idPieChart, "field 'weekChart'", PieChart.class);
    target.loadChart = Utils.findRequiredViewAsType(source, R.id.pieChart_Arrow, "field 'loadChart'", ImageView.class);
    target.noDataFoundRL = Utils.findRequiredViewAsType(source, R.id.noDataFoundRL, "field 'noDataFoundRL'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WeeklyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.weekSpinner = null;
    target.yearSpinner = null;
    target.weekChart = null;
    target.loadChart = null;
    target.noDataFoundRL = null;
  }
}
