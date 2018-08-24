// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave;

import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment_ViewBinding;
import com.android.timesheet.shared.widget.CircularProgressBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyLeave_ViewBinding extends BaseFragment_ViewBinding {
  private MyLeave target;

  private View view2131296296;

  @UiThread
  public MyLeave_ViewBinding(final MyLeave target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.my_leaves_recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", CircularProgressBar.class);
    target.my_leaves_SV = Utils.findRequiredViewAsType(source, R.id.my_leaves_SV, "field 'my_leaves_SV'", SearchView.class);
    target.noDataFound = Utils.findRequiredViewAsType(source, R.id.noDataFound, "field 'noDataFound'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.applyLeave, "field 'applyLeave' and method 'onClick'");
    target.applyLeave = Utils.castView(view, R.id.applyLeave, "field 'applyLeave'", FloatingActionButton.class);
    view2131296296 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  public void unbind() {
    MyLeave target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.progressBar = null;
    target.my_leaves_SV = null;
    target.noDataFound = null;
    target.applyLeave = null;

    view2131296296.setOnClickListener(null);
    view2131296296 = null;

    super.unbind();
  }
}
