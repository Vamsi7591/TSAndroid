// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.leave.apply_leave;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.daimajia.swipe.SwipeLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyLeaveAdapter$MyLeaveViewHolder_ViewBinding implements Unbinder {
  private MyLeaveAdapter.MyLeaveViewHolder target;

  @UiThread
  public MyLeaveAdapter$MyLeaveViewHolder_ViewBinding(MyLeaveAdapter.MyLeaveViewHolder target,
      View source) {
    this.target = target;

    target.leaveTypeTV = Utils.findRequiredViewAsType(source, R.id.leaveTypeTV, "field 'leaveTypeTV'", CustomFontTextView.class);
    target.remarksTV = Utils.findRequiredViewAsType(source, R.id.remarksTV, "field 'remarksTV'", CustomFontTextView.class);
    target.noOfDaysTV = Utils.findRequiredViewAsType(source, R.id.noOfDaysTV, "field 'noOfDaysTV'", CustomFontTextView.class);
    target.fromDateTV = Utils.findRequiredViewAsType(source, R.id.fromDateTV, "field 'fromDateTV'", CustomFontTextView.class);
    target.toDateTV = Utils.findRequiredViewAsType(source, R.id.toDateTV, "field 'toDateTV'", CustomFontTextView.class);
    target.trashLL = Utils.findRequiredViewAsType(source, R.id.trashLL, "field 'trashLL'", LinearLayout.class);
    target.swipeSL = Utils.findRequiredViewAsType(source, R.id.swipeSL, "field 'swipeSL'", SwipeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyLeaveAdapter.MyLeaveViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.leaveTypeTV = null;
    target.remarksTV = null;
    target.noOfDaysTV = null;
    target.fromDateTV = null;
    target.toDateTV = null;
    target.trashLL = null;
    target.swipeSL = null;
  }
}
