// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin.project_master.addProject;

import android.support.annotation.UiThread;
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

    target.projName = Utils.findRequiredViewAsType(source, R.id.project_Name, "field 'projName'", EditText.class);
    target.toggleButton = Utils.findRequiredViewAsType(source, R.id.toggle, "field 'toggleButton'", ToggleButton.class);
    target.submitBtn = Utils.findRequiredViewAsType(source, R.id.submit_Button, "field 'submitBtn'", Button.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    AddProject target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.projName = null;
    target.toggleButton = null;
    target.submitBtn = null;
    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
