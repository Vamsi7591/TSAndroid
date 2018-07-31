// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.common_operations.profile.admin_profile;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.TokenizeTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyProfile_ViewBinding extends BaseActivity_ViewBinding {
  private MyProfile target;

  private View view2131296611;

  @UiThread
  public MyProfile_ViewBinding(MyProfile target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyProfile_ViewBinding(final MyProfile target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
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
    target.editTextFirstName = Utils.findRequiredViewAsType(source, R.id.editTextFirstName, "field 'editTextFirstName'", EditText.class);
    target.editTextEmpCode = Utils.findRequiredViewAsType(source, R.id.editTextEmpCode, "field 'editTextEmpCode'", EditText.class);
    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.editTextEmail, "field 'editTextEmail'", EditText.class);
    target.textViewChangePassword = Utils.findRequiredViewAsType(source, R.id.textViewChangePassword, "field 'textViewChangePassword'", TextView.class);
    target.textViewContactUs = Utils.findRequiredViewAsType(source, R.id.textViewContactUs, "field 'textViewContactUs'", TextView.class);
    target.textViewLogOut = Utils.findRequiredViewAsType(source, R.id.textViewLogOut, "field 'textViewLogOut'", TextView.class);
  }

  @Override
  public void unbind() {
    MyProfile target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitleTv = null;
    target.tokenizeTextViewProjects = null;
    target.textViewAboutUs = null;
    target.editTextFirstName = null;
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
