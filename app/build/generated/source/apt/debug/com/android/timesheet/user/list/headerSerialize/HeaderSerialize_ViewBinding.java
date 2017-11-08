// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user.list.headerSerialize;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HeaderSerialize_ViewBinding extends BaseActivity_ViewBinding {
  private HeaderSerialize target;

  @UiThread
  public HeaderSerialize_ViewBinding(HeaderSerialize target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HeaderSerialize_ViewBinding(HeaderSerialize target, View source) {
    super(target, source);

    this.target = target;

    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    HeaderSerialize target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
