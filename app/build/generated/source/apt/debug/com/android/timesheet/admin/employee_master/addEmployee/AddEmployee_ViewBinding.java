// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin.employee_master.addEmployee;

import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
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

    target.employeeName = Utils.findRequiredViewAsType(source, R.id.employee_Name, "field 'employeeName'", EditText.class);
    target.eMail_Id = Utils.findRequiredViewAsType(source, R.id.email_ID, "field 'eMail_Id'", EditText.class);
    target.password_Employee = Utils.findRequiredViewAsType(source, R.id.emp_Password, "field 'password_Employee'", EditText.class);
    target.passwordInputLayout = Utils.findRequiredViewAsType(source, R.id.passwordInputLayout, "field 'passwordInputLayout'", TextInputLayout.class);
    target.input_layout_email = Utils.findRequiredViewAsType(source, R.id.email_Input_layout, "field 'input_layout_email'", TextInputLayout.class);
    target.emapInputName = Utils.findRequiredViewAsType(source, R.id.input_Employee_name, "field 'emapInputName'", TextInputLayout.class);
    target.toggleButton = Utils.findRequiredViewAsType(source, R.id.toggleButton, "field 'toggleButton'", ToggleButton.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit_Buttontn, "field 'submit'", Button.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    AddEmployee target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.employeeName = null;
    target.eMail_Id = null;
    target.password_Employee = null;
    target.passwordInputLayout = null;
    target.input_layout_email = null;
    target.emapInputName = null;
    target.toggleButton = null;
    target.submit = null;
    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
