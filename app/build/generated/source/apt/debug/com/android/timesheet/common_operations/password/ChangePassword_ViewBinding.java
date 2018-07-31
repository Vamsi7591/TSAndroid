// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.common_operations.password;

import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
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
    target.submitBtn = Utils.findRequiredViewAsType(source, R.id.submitBtn, "field 'submitBtn'", CustomFontTextView.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
    target.inputOldPwd = Utils.findRequiredViewAsType(source, R.id.inputLayout_oldpassword, "field 'inputOldPwd'", TextInputLayout.class);
    target.inputNewPwd = Utils.findRequiredViewAsType(source, R.id.textNewPwd, "field 'inputNewPwd'", TextInputLayout.class);
    target.inputConfirmPwd = Utils.findRequiredViewAsType(source, R.id.textConfirmPwd, "field 'inputConfirmPwd'", TextInputLayout.class);
  }

  @Override
  public void unbind() {
    ChangePassword target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.oldPwd = null;
    target.newPwd = null;
    target.confirmPwd = null;
    target.submitBtn = null;
    target.toolbarTitleTv = null;
    target.inputOldPwd = null;
    target.inputNewPwd = null;
    target.inputConfirmPwd = null;

    super.unbind();
  }
}
