// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.timesheet.sheet_list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TimeSheetAdapter$HeaderViewHolder_ViewBinding implements Unbinder {
  private TimeSheetAdapter.HeaderViewHolder target;

  @UiThread
  public TimeSheetAdapter$HeaderViewHolder_ViewBinding(TimeSheetAdapter.HeaderViewHolder target,
      View source) {
    this.target = target;

    target.headerTextView = Utils.findRequiredViewAsType(source, R.id.header, "field 'headerTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TimeSheetAdapter.HeaderViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.headerTextView = null;
  }
}
