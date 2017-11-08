// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin.project_master;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProjectMasterAdapter$ProjectViewHolder_ViewBinding implements Unbinder {
  private ProjectMasterAdapter.ProjectViewHolder target;

  @UiThread
  public ProjectMasterAdapter$ProjectViewHolder_ViewBinding(ProjectMasterAdapter.ProjectViewHolder target,
      View source) {
    this.target = target;

    target.projectTV = Utils.findRequiredViewAsType(source, R.id.project, "field 'projectTV'", TextView.class);
    target.codeTV = Utils.findRequiredViewAsType(source, R.id.code, "field 'codeTV'", TextView.class);
    target.trashin = Utils.findRequiredViewAsType(source, R.id.trashing, "field 'trashin'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProjectMasterAdapter.ProjectViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.projectTV = null;
    target.codeTV = null;
    target.trashin = null;
  }
}
