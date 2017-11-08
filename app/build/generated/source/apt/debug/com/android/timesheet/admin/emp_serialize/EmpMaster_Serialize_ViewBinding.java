// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin.emp_serialize;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EmpMaster_Serialize_ViewBinding extends BaseActivity_ViewBinding {
  private EmpMaster_Serialize target;

  @UiThread
  public EmpMaster_Serialize_ViewBinding(EmpMaster_Serialize target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EmpMaster_Serialize_ViewBinding(EmpMaster_Serialize target, View source) {
    super(target, source);

    this.target = target;

    target.empName = Utils.findRequiredViewAsType(source, R.id.emp_Name, "field 'empName'", EditText.class);
    target.empCode = Utils.findRequiredViewAsType(source, R.id.emp_code, "field 'empCode'", EditText.class);
    target.eMail = Utils.findRequiredViewAsType(source, R.id.input_email, "field 'eMail'", EditText.class);
    target.inputPassword = Utils.findRequiredViewAsType(source, R.id.inputLayout_password, "field 'inputPassword'", TextView.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password_Emp, "field 'password'", EditText.class);
    target.toggleButton = Utils.findRequiredViewAsType(source, R.id.toggleButton, "field 'toggleButton'", ToggleButton.class);
    target.edit = Utils.findRequiredViewAsType(source, R.id.edit_ButtoN, "field 'edit'", Button.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit_Btn, "field 'submit'", Button.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    EmpMaster_Serialize target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.empName = null;
    target.empCode = null;
    target.eMail = null;
    target.inputPassword = null;
    target.password = null;
    target.toggleButton = null;
    target.edit = null;
    target.submit = null;
    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
