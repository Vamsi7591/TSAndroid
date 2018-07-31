// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.shared.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseFragment_ViewBinding implements Unbinder {
  private BaseFragment target;

  @UiThread
  public BaseFragment_ViewBinding(BaseFragment target, View source) {
    this.target = target;

    target.toolbar = Utils.findOptionalViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
  }
}
