// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.timesheet.sheet_fragment;

import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment_ViewBinding;
import com.android.timesheet.user_operations.timesheet.sheet_list.TimeSheetList;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TimeSheetFragment_ViewBinding extends BaseFragment_ViewBinding {
  private TimeSheetFragment target;

  @UiThread
  public TimeSheetFragment_ViewBinding(TimeSheetFragment target, View source) {
    super(target, source);

    this.target = target;

    target.emptyStateLL = Utils.findRequiredViewAsType(source, R.id.emptyStateLL, "field 'emptyStateLL'", LinearLayout.class);
    target.timeSheetList = Utils.findRequiredViewAsType(source, R.id.room_list, "field 'timeSheetList'", TimeSheetList.class);
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeToRefresh, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    target.timeSheetELV = Utils.findRequiredViewAsType(source, R.id.expandableListView, "field 'timeSheetELV'", ExpandableListView.class);
  }

  @Override
  public void unbind() {
    TimeSheetFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.emptyStateLL = null;
    target.timeSheetList = null;
    target.mSwipeRefreshLayout = null;
    target.timeSheetELV = null;

    super.unbind();
  }
}
