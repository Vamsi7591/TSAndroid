// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.employee_master.add_employee;

import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddEmployee_ViewBinding extends BaseActivity_ViewBinding {
  private AddEmployee target;

  @UiThread
  public AddEmployee_ViewBinding(AddEmployee target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddEmployee_ViewBinding(AddEmployee target, View source) {
    super(target, source);

    this.target = target;

    target.employeeNameEt = Utils.findRequiredViewAsType(source, R.id.employeeNameEt, "field 'employeeNameEt'", EditText.class);
    target.emailIdEt = Utils.findRequiredViewAsType(source, R.id.emailIdEt, "field 'emailIdEt'", EditText.class);
    target.passwordEt = Utils.findRequiredViewAsType(source, R.id.passwordEt, "field 'passwordEt'", EditText.class);
    target.passwordTIL = Utils.findRequiredViewAsType(source, R.id.passwordTIL, "field 'passwordTIL'", TextInputLayout.class);
    target.emailTIL = Utils.findRequiredViewAsType(source, R.id.emailTIL, "field 'emailTIL'", TextInputLayout.class);
    target.employeeTIL = Utils.findRequiredViewAsType(source, R.id.employeeTIL, "field 'employeeTIL'", TextInputLayout.class);
    target.adminAccessTBtn = Utils.findRequiredViewAsType(source, R.id.adminAccessTBtn, "field 'adminAccessTBtn'", ToggleButton.class);
    target.submitBtn = Utils.findRequiredViewAsType(source, R.id.submitBtn, "field 'submitBtn'", CustomFontTextView.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    AddEmployee target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.employeeNameEt = null;
    target.emailIdEt = null;
    target.passwordEt = null;
    target.passwordTIL = null;
    target.emailTIL = null;
    target.employeeTIL = null;
    target.adminAccessTBtn = null;
    target.submitBtn = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
