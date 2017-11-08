// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.password;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePassword_ViewBinding extends BaseActivity_ViewBinding {
  private ChangePassword target;

  @UiThread
  public ChangePassword_ViewBinding(ChangePassword target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePassword_ViewBinding(ChangePassword target, View source) {
    super(target, source);

    this.target = target;

    target.oldPwd = Utils.findRequiredViewAsType(source, R.id.oldPassword, "field 'oldPwd'", EditText.class);
    target.newPwd = Utils.findRequiredViewAsType(source, R.id.newPassword, "field 'newPwd'", EditText.class);
    target.confirmPwd = Utils.findRequiredViewAsType(source, R.id.confirmPassword, "field 'confirmPwd'", EditText.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.sbtbtn, "field 'submit'", Button.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    ChangePassword target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.oldPwd = null;
    target.newPwd = null;
    target.confirmPwd = null;
    target.submit = null;
    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
