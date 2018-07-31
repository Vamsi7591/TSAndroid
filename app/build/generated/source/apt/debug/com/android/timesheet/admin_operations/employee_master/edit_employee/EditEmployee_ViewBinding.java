// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.employee_master.edit_employee;

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

public class EditEmployee_ViewBinding extends BaseActivity_ViewBinding {
  private EditEmployee target;

  @UiThread
  public EditEmployee_ViewBinding(EditEmployee target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditEmployee_ViewBinding(EditEmployee target, View source) {
    super(target, source);

    this.target = target;

    target.empNameEt = Utils.findRequiredViewAsType(source, R.id.empNameEt, "field 'empNameEt'", EditText.class);
    target.empCodeEt = Utils.findRequiredViewAsType(source, R.id.empCodeEt, "field 'empCodeEt'", EditText.class);
    target.eMailEt = Utils.findRequiredViewAsType(source, R.id.eMailEt, "field 'eMailEt'", EditText.class);
    target.passwordTIL = Utils.findRequiredViewAsType(source, R.id.passwordTIL, "field 'passwordTIL'", TextInputLayout.class);
    target.passwordEt = Utils.findRequiredViewAsType(source, R.id.passwordEt, "field 'passwordEt'", EditText.class);
    target.adminAccessTBtn = Utils.findRequiredViewAsType(source, R.id.adminAccessTBtn, "field 'adminAccessTBtn'", ToggleButton.class);
    target.editBtn = Utils.findRequiredViewAsType(source, R.id.editBtn, "field 'editBtn'", CustomFontTextView.class);
    target.submitBtn = Utils.findRequiredViewAsType(source, R.id.submitBtn, "field 'submitBtn'", CustomFontTextView.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    EditEmployee target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.empNameEt = null;
    target.empCodeEt = null;
    target.eMailEt = null;
    target.passwordTIL = null;
    target.passwordEt = null;
    target.adminAccessTBtn = null;
    target.editBtn = null;
    target.submitBtn = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
