// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.employee_master.list_employee;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EmployeeMaster_ViewBinding extends BaseActivity_ViewBinding {
  private EmployeeMaster target;

  @UiThread
  public EmployeeMaster_ViewBinding(EmployeeMaster target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EmployeeMaster_ViewBinding(EmployeeMaster target, View source) {
    super(target, source);

    this.target = target;

    target.emptyStateLL = Utils.findRequiredViewAsType(source, R.id.emptyStateLL, "field 'emptyStateLL'", LinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.general_recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    EmployeeMaster target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.emptyStateLL = null;
    target.recyclerView = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
