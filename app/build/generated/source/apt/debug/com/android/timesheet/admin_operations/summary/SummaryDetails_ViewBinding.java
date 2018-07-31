// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.summary;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.github.mikephil.charting.charts.BarChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SummaryDetails_ViewBinding extends BaseActivity_ViewBinding {
  private SummaryDetails target;

  @UiThread
  public SummaryDetails_ViewBinding(SummaryDetails target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SummaryDetails_ViewBinding(SummaryDetails target, View source) {
    super(target, source);

    this.target = target;

    target.employeeName = Utils.findRequiredViewAsType(source, R.id.empName_spinner, "field 'employeeName'", Spinner.class);
    target.projName = Utils.findRequiredViewAsType(source, R.id.ProjName_spinner, "field 'projName'", Spinner.class);
    target.yearSpinner = Utils.findRequiredViewAsType(source, R.id.yearSpinSum, "field 'yearSpinner'", Spinner.class);
    target.barChart = Utils.findRequiredViewAsType(source, R.id.barChart, "field 'barChart'", BarChart.class);
    target.loadBar = Utils.findRequiredViewAsType(source, R.id.loadBarChart, "field 'loadBar'", ImageView.class);
    target.noDataFound = Utils.findRequiredViewAsType(source, R.id.noDataFoundRL, "field 'noDataFound'", LinearLayout.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    SummaryDetails target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.employeeName = null;
    target.projName = null;
    target.yearSpinner = null;
    target.barChart = null;
    target.loadBar = null;
    target.noDataFound = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
