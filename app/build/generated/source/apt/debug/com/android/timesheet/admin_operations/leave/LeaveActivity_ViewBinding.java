// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.leave;

import android.support.annotation.UiThread;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeaveActivity_ViewBinding extends BaseActivity_ViewBinding {
  private LeaveActivity target;

  @UiThread
  public LeaveActivity_ViewBinding(LeaveActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LeaveActivity_ViewBinding(LeaveActivity target, View source) {
    super(target, source);

    this.target = target;

    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.htab_tabs, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.htab_viewpager, "field 'viewPager'", ViewPager.class);
    target.collapsingToolbarLayout = Utils.findRequiredViewAsType(source, R.id.toolbar_layout, "field 'collapsingToolbarLayout'", CollapsingToolbarLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
    target.gridView = Utils.findRequiredViewAsType(source, R.id.gridView, "field 'gridView'", GridView.class);
  }

  @Override
  public void unbind() {
    LeaveActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
    target.tabLayout = null;
    target.viewPager = null;
    target.collapsingToolbarLayout = null;
    target.toolbar = null;
    target.toolbarTitleTv = null;
    target.gridView = null;

    super.unbind();
  }
}
