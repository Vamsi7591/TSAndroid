// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.leave.apply_leave;

import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ApplyLeave_ViewBinding extends BaseActivity_ViewBinding {
  private ApplyLeave target;

  @UiThread
  public ApplyLeave_ViewBinding(ApplyLeave target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ApplyLeave_ViewBinding(ApplyLeave target, View source) {
    super(target, source);

    this.target = target;

    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    ApplyLeave target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
    target.tabLayout = null;
    target.viewPager = null;
    target.toolbar = null;
    target.toolbarTitleTv = null;

    super.unbind();
  }
}
