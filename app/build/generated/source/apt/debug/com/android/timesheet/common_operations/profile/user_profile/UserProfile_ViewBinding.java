// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.common_operations.profile.user_profile;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment_ViewBinding;
import com.android.timesheet.shared.widget.TokenizeTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserProfile_ViewBinding extends BaseFragment_ViewBinding {
  private UserProfile target;

  private View view2131296611;

  @UiThread
  public UserProfile_ViewBinding(final UserProfile target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.tokenizeTextViewProjects = Utils.findRequiredViewAsType(source, R.id.textViewProjects, "field 'tokenizeTextViewProjects'", TokenizeTextView.class);
    view = Utils.findRequiredView(source, R.id.textViewAboutUs, "field 'textViewAboutUs' and method 'openWebView'");
    target.textViewAboutUs = Utils.castView(view, R.id.textViewAboutUs, "field 'textViewAboutUs'", TextView.class);
    view2131296611 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openWebView();
      }
    });
    target.editTexttName = Utils.findRequiredViewAsType(source, R.id.editTextName, "field 'editTexttName'", EditText.class);
    target.editTextEmpCode = Utils.findRequiredViewAsType(source, R.id.editTextEmpCode, "field 'editTextEmpCode'", EditText.class);
    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.editTextEmail, "field 'editTextEmail'", EditText.class);
    target.textViewChangePassword = Utils.findRequiredViewAsType(source, R.id.textViewChangePassword, "field 'textViewChangePassword'", TextView.class);
    target.textViewContactUs = Utils.findRequiredViewAsType(source, R.id.textViewContactUs, "field 'textViewContactUs'", TextView.class);
    target.textViewLogOut = Utils.findRequiredViewAsType(source, R.id.textViewLogOut, "field 'textViewLogOut'", TextView.class);
  }

  @Override
  public void unbind() {
    UserProfile target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tokenizeTextViewProjects = null;
    target.textViewAboutUs = null;
    target.editTexttName = null;
    target.editTextEmpCode = null;
    target.editTextEmail = null;
    target.textViewChangePassword = null;
    target.textViewContactUs = null;
    target.textViewLogOut = null;

    view2131296611.setOnClickListener(null);
    view2131296611 = null;

    super.unbind();
  }
}
