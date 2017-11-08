// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user.sheet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.user.list.TimeSheetList;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TimeSheetFragment_ViewBinding implements Unbinder {
  private TimeSheetFragment target;

  @UiThread
  public TimeSheetFragment_ViewBinding(TimeSheetFragment target, View source) {
    this.target = target;

    target.empty_state_view = Utils.findRequiredViewAsType(source, R.id.empty_state_view, "field 'empty_state_view'", LinearLayout.class);
    target.timeSheetList = Utils.findRequiredViewAsType(source, R.id.room_list, "field 'timeSheetList'", TimeSheetList.class);
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeToRefresh, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    target.timeSheetELV = Utils.findRequiredViewAsType(source, R.id.expandableListView, "field 'timeSheetELV'", ExpandableListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TimeSheetFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.empty_state_view = null;
    target.timeSheetList = null;
    target.mSwipeRefreshLayout = null;
    target.timeSheetELV = null;
  }
}
