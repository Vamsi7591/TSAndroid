// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin.employee_project;

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

    target.assign = Utils.findRequiredViewAsType(source, R.id.assignProject, "field 'assign'", Button.class);
    target.remove = Utils.findRequiredViewAsType(source, R.id.removeProject, "field 'remove'", Button.class);
    target.empName = Utils.findRequiredViewAsType(source, R.id.employe_Name, "field 'empName'", Spinner.class);
    target.projName = Utils.findRequiredViewAsType(source, R.id.proj_Name, "field 'projName'", Spinner.class);
    target.realAssign = Utils.findRequiredViewAsType(source, R.id.assignRealProject, "field 'realAssign'", Button.class);
    target.realRemove = Utils.findRequiredViewAsType(source, R.id.removeRealProject, "field 'realRemove'", Button.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    EmployeeProject target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.assign = null;
    target.remove = null;
    target.empName = null;
    target.projName = null;
    target.realAssign = null;
    target.realRemove = null;
    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
