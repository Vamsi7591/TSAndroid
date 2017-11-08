// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin.project_master.proj_serialize;

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

public class Proj_MasterSerialize_ViewBinding extends BaseActivity_ViewBinding {
  private Proj_MasterSerialize target;

  @UiThread
  public Proj_MasterSerialize_ViewBinding(Proj_MasterSerialize target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Proj_MasterSerialize_ViewBinding(Proj_MasterSerialize target, View source) {
    super(target, source);

    this.target = target;

    target.commonFlag = Utils.findRequiredViewAsType(source, R.id.commonFlag, "field 'commonFlag'", TextView.class);
    target.projName = Utils.findRequiredViewAsType(source, R.id.projNemeEdit, "field 'projName'", EditText.class);
    target.getProjectCode = Utils.findRequiredViewAsType(source, R.id.projCode_Edit, "field 'getProjectCode'", EditText.class);
    target.toggleButton = Utils.findRequiredViewAsType(source, R.id.toggleButton, "field 'toggleButton'", ToggleButton.class);
    target.edit_Button = Utils.findRequiredViewAsType(source, R.id.edit_Btn, "field 'edit_Button'", Button.class);
    target.submit_Btn = Utils.findRequiredViewAsType(source, R.id.submit_Btn, "field 'submit_Btn'", Button.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    Proj_MasterSerialize target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.commonFlag = null;
    target.projName = null;
    target.getProjectCode = null;
    target.toggleButton = null;
    target.edit_Button = null;
    target.submit_Btn = null;
    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
