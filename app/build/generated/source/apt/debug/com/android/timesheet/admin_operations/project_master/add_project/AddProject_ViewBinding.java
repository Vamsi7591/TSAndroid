// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.project_master.add_project;

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

public class AddProject_ViewBinding extends BaseActivity_ViewBinding {
  private AddProject target;

  @UiThread
  public AddProject_ViewBinding(AddProject target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddProject_ViewBinding(AddProject target, View source) {
    super(target, source);

    this.target = target;

    target.projectNameTIL = Utils.findRequiredViewAsType(source, R.id.projectNameTIL, "field 'projectNameTIL'", TextInputLayout.class);
    target.projectNameET = Utils.findRequiredViewAsType(source, R.id.projectNameET, "field 'projectNameET'", EditText.class);
    target.projectTBtn = Utils.findRequiredViewAsType(source, R.id.projectTBtn, "field 'projectTBtn'", ToggleButton.class);
    target.submitBtn = Utils.findRequiredViewAsType(source, R.id.submitBtn, "field 'submitBtn'", CustomFontTextView.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    AddProject target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.projectNameTIL = null;
    target.projectNameET = null;
    target.projectTBtn = null;
    target.submitBtn = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
