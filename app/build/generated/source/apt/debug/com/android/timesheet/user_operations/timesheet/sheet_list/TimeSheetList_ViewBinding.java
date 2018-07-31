// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.timesheet.sheet_list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.widget.CircularProgressBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TimeSheetList_ViewBinding implements Unbinder {
  private TimeSheetList target;

  @UiThread
  public TimeSheetList_ViewBinding(TimeSheetList target) {
    this(target, target);
  }

  @UiThread
  public TimeSheetList_ViewBinding(TimeSheetList target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.general_recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", CircularProgressBar.class);
    target.projectSV = Utils.findRequiredViewAsType(source, R.id.projectSV, "field 'projectSV'", SearchView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TimeSheetList target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.progressBar = null;
    target.projectSV = null;
  }
}
