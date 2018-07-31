// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.reports.yearly;

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

public class Yearly_Fragment_ViewBinding extends BaseFragment_ViewBinding {
  private Yearly_Fragment target;

  @UiThread
  public Yearly_Fragment_ViewBinding(Yearly_Fragment target, View source) {
    super(target, source);

    this.target = target;

    target.projName = Utils.findRequiredViewAsType(source, R.id.project_names, "field 'projName'", Spinner.class);
    target.yearSpinner = Utils.findRequiredViewAsType(source, R.id.yearly_Spinner, "field 'yearSpinner'", Spinner.class);
    target.barChart = Utils.findRequiredViewAsType(source, R.id.idPieChartYearly, "field 'barChart'", BarChart.class);
    target.loadBar = Utils.findRequiredViewAsType(source, R.id.pieChart_Load, "field 'loadBar'", ImageView.class);
    target.noDataFound = Utils.findRequiredViewAsType(source, R.id.noDataFoundRL, "field 'noDataFound'", LinearLayout.class);
  }

  @Override
  public void unbind() {
    Yearly_Fragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.projName = null;
    target.yearSpinner = null;
    target.barChart = null;
    target.loadBar = null;
    target.noDataFound = null;

    super.unbind();
  }
}
