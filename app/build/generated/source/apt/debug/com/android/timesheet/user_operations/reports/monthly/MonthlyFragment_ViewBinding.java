// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.reports.monthly;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment_ViewBinding;
import com.github.mikephil.charting.charts.BarChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MonthlyFragment_ViewBinding extends BaseFragment_ViewBinding {
  private MonthlyFragment target;

  @UiThread
  public MonthlyFragment_ViewBinding(MonthlyFragment target, View source) {
    super(target, source);

    this.target = target;

    target.monthSpinner = Utils.findRequiredViewAsType(source, R.id.spinner_Month, "field 'monthSpinner'", Spinner.class);
    target.yearSpinner = Utils.findRequiredViewAsType(source, R.id.spinnerYear, "field 'yearSpinner'", Spinner.class);
    target.barChart = Utils.findRequiredViewAsType(source, R.id.lineChart, "field 'barChart'", BarChart.class);
    target.loadLine = Utils.findRequiredViewAsType(source, R.id.line_Arrow, "field 'loadLine'", ImageView.class);
    target.noDataFound = Utils.findRequiredViewAsType(source, R.id.noDataFoundRL, "field 'noDataFound'", LinearLayout.class);
  }

  @Override
  public void unbind() {
    MonthlyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.monthSpinner = null;
    target.yearSpinner = null;
    target.barChart = null;
    target.loadLine = null;
    target.noDataFound = null;

    super.unbind();
  }
}
