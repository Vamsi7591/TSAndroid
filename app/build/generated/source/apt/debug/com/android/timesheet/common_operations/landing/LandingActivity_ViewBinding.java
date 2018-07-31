// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.common_operations.landing;

import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.NonSwipeableViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LandingActivity_ViewBinding extends BaseActivity_ViewBinding {
  private LandingActivity target;

  @UiThread
  public LandingActivity_ViewBinding(LandingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LandingActivity_ViewBinding(LandingActivity target, View source) {
    super(target, source);

    this.target = target;

    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabs, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPagerHome, "field 'viewPager'", NonSwipeableViewPager.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'textViewToolbarTitle'", CustomFontTextView.class);
    target.linearmLeftDrawer = Utils.findRequiredViewAsType(source, R.id.mLeftDrawer, "field 'linearmLeftDrawer'", LinearLayout.class);
    target.mDrawerLayout = Utils.findRequiredViewAsType(source, R.id.mainDrawerLayout, "field 'mDrawerLayout'", DrawerLayout.class);
  }

  @Override
  public void unbind() {
    LandingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
    target.tabLayout = null;
    target.viewPager = null;
    target.textViewToolbarTitle = null;
    target.linearmLeftDrawer = null;
    target.mDrawerLayout = null;

    super.unbind();
  }
}
