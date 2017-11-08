// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.auth;

import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding extends BaseActivity_ViewBinding {
  private LoginActivity target;

  private View view2131296573;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.textInputLayoutECode = Utils.findRequiredViewAsType(source, R.id.textInputLayoutECode, "field 'textInputLayoutECode'", TextInputLayout.class);
    target.textInputLayoutPassword = Utils.findRequiredViewAsType(source, R.id.textInputLayoutPassword, "field 'textInputLayoutPassword'", TextInputLayout.class);
    target.editTextECode = Utils.findRequiredViewAsType(source, R.id.editTextECode, "field 'editTextECode'", EditText.class);
    target.editTextPassword = Utils.findRequiredViewAsType(source, R.id.editTextPassword, "field 'editTextPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.textViewLogin, "field 'textViewLogin' and method 'login'");
    target.textViewLogin = Utils.castView(view, R.id.textViewLogin, "field 'textViewLogin'", CustomFontTextView.class);
    view2131296573 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
  }

  @Override
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textInputLayoutECode = null;
    target.textInputLayoutPassword = null;
    target.editTextECode = null;
    target.editTextPassword = null;
    target.textViewLogin = null;

    view2131296573.setOnClickListener(null);
    view2131296573 = null;

    super.unbind();
  }
}
