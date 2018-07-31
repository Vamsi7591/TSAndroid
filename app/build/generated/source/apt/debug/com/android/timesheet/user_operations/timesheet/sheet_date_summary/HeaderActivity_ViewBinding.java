// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.timesheet.sheet_date_summary;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HeaderActivity_ViewBinding extends BaseActivity_ViewBinding {
  private HeaderActivity target;

  @UiThread
  public HeaderActivity_ViewBinding(HeaderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HeaderActivity_ViewBinding(HeaderActivity target, View source) {
    super(target, source);

    this.target = target;

    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    HeaderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitleTv = null;

    super.unbind();
  }
}
