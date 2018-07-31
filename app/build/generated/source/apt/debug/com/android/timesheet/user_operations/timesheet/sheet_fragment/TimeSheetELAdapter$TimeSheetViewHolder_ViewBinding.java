// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.timesheet.sheet_fragment;

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

public class TimeSheetELAdapter$TimeSheetViewHolder_ViewBinding implements Unbinder {
  private TimeSheetELAdapter.TimeSheetViewHolder target;

  @UiThread
  public TimeSheetELAdapter$TimeSheetViewHolder_ViewBinding(TimeSheetELAdapter.TimeSheetViewHolder target,
      View source) {
    this.target = target;

    target.projectLL = Utils.findRequiredViewAsType(source, R.id.projectLL, "field 'projectLL'", LinearLayout.class);
    target.projectTV = Utils.findRequiredViewAsType(source, R.id.project, "field 'projectTV'", TextView.class);
    target.timeTV = Utils.findRequiredViewAsType(source, R.id.time, "field 'timeTV'", TextView.class);
    target.descriptionTV = Utils.findRequiredViewAsType(source, R.id.description, "field 'descriptionTV'", TextView.class);
    target.trashLL = Utils.findRequiredViewAsType(source, R.id.trashLL, "field 'trashLL'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TimeSheetELAdapter.TimeSheetViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.projectLL = null;
    target.projectTV = null;
    target.timeTV = null;
    target.descriptionTV = null;
    target.trashLL = null;
  }
}
