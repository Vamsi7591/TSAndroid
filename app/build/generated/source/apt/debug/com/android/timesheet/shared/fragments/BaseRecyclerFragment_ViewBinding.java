// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.shared.fragments;

import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseRecyclerFragment_ViewBinding extends BaseFragment_ViewBinding {
  private BaseRecyclerFragment target;

  @UiThread
  public BaseRecyclerFragment_ViewBinding(BaseRecyclerFragment target, View source) {
    super(target, source);

    this.target = target;

    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipe_refresh_layout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    BaseRecyclerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeRefreshLayout = null;
    target.recyclerView = null;

    super.unbind();
  }
}
