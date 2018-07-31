// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.employee_project;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EmployeeProject_ViewBinding extends BaseActivity_ViewBinding {
  private EmployeeProject target;

  @UiThread
  public EmployeeProject_ViewBinding(EmployeeProject target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EmployeeProject_ViewBinding(EmployeeProject target, View source) {
    super(target, source);

    this.target = target;

    target.assignTabBtn = Utils.findRequiredViewAsType(source, R.id.assignProject, "field 'assignTabBtn'", Button.class);
    target.removeTabBtn = Utils.findRequiredViewAsType(source, R.id.removeProject, "field 'removeTabBtn'", Button.class);
    target.empNameSp = Utils.findRequiredViewAsType(source, R.id.employeeName, "field 'empNameSp'", Spinner.class);
    target.projectNameSp = Utils.findRequiredViewAsType(source, R.id.projectName, "field 'projectNameSp'", Spinner.class);
    target.assignProjectToEmpBtn = Utils.findRequiredViewAsType(source, R.id.assignProjectToEmp, "field 'assignProjectToEmpBtn'", Button.class);
    target.removeProjectFromEmpBtn = Utils.findRequiredViewAsType(source, R.id.removeProjectFromEmp, "field 'removeProjectFromEmpBtn'", Button.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    EmployeeProject target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.assignTabBtn = null;
    target.removeTabBtn = null;
    target.empNameSp = null;
    target.projectNameSp = null;
    target.assignProjectToEmpBtn = null;
    target.removeProjectFromEmpBtn = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
