// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.project_master.edit_project;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditProject_ViewBinding extends BaseActivity_ViewBinding {
  private EditProject target;

  @UiThread
  public EditProject_ViewBinding(EditProject target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditProject_ViewBinding(EditProject target, View source) {
    super(target, source);

    this.target = target;

    target.projectNameET = Utils.findRequiredViewAsType(source, R.id.projectNameET, "field 'projectNameET'", EditText.class);
    target.projectCodeET = Utils.findRequiredViewAsType(source, R.id.projectCodeET, "field 'projectCodeET'", EditText.class);
    target.projectTBtn = Utils.findRequiredViewAsType(source, R.id.projectTBtn, "field 'projectTBtn'", ToggleButton.class);
    target.editBtn = Utils.findRequiredViewAsType(source, R.id.editBtn, "field 'editBtn'", CustomFontTextView.class);
    target.submit_Btn = Utils.findRequiredViewAsType(source, R.id.submitBtn, "field 'submit_Btn'", CustomFontTextView.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    EditProject target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.projectNameET = null;
    target.projectCodeET = null;
    target.projectTBtn = null;
    target.editBtn = null;
    target.submit_Btn = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
