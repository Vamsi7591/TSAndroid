// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin.employee_master;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EmployeeMasterAdapter$EmployeeViewHolder_ViewBinding implements Unbinder {
  private EmployeeMasterAdapter.EmployeeViewHolder target;

  @UiThread
  public EmployeeMasterAdapter$EmployeeViewHolder_ViewBinding(EmployeeMasterAdapter.EmployeeViewHolder target,
      View source) {
    this.target = target;

    target.employeeLL = Utils.findRequiredViewAsType(source, R.id.employeeLL, "field 'employeeLL'", LinearLayout.class);
    target.employeeTV = Utils.findRequiredViewAsType(source, R.id.employee, "field 'employeeTV'", TextView.class);
    target.codeTV = Utils.findRequiredViewAsType(source, R.id.code, "field 'codeTV'", TextView.class);
    target.emailTV = Utils.findRequiredViewAsType(source, R.id.email, "field 'emailTV'", TextView.class);
    target.trash = Utils.findRequiredViewAsType(source, R.id.trashed, "field 'trash'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EmployeeMasterAdapter.EmployeeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.employeeLL = null;
    target.employeeTV = null;
    target.codeTV = null;
    target.emailTV = null;
    target.trash = null;
  }
}
