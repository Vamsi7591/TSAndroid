// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.common_operations.gmail;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.TokenizeTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Gmail_ViewBinding extends BaseActivity_ViewBinding {
  private Gmail target;

  @UiThread
  public Gmail_ViewBinding(Gmail target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Gmail_ViewBinding(Gmail target, View source) {
    super(target, source);

    this.target = target;

    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
    target.userEmail = Utils.findRequiredViewAsType(source, R.id.UserEmail, "field 'userEmail'", TextView.class);
    target.toAddress = Utils.findRequiredViewAsType(source, R.id.toEditTxt, "field 'toAddress'", TokenizeTextView.class);
    target.ccAddress = Utils.findRequiredViewAsType(source, R.id.ccAddress, "field 'ccAddress'", MultiAutoCompleteTextView.class);
    target.bccAddress = Utils.findRequiredViewAsType(source, R.id.bccAddress, "field 'bccAddress'", MultiAutoCompleteTextView.class);
    target.send = Utils.findRequiredViewAsType(source, R.id.sendBtn, "field 'send'", CustomFontTextView.class);
    target.messgge = Utils.findRequiredViewAsType(source, R.id.messageET, "field 'messgge'", EditText.class);
    target.subject = Utils.findRequiredViewAsType(source, R.id.subject_ET, "field 'subject'", EditText.class);
  }

  @Override
  public void unbind() {
    Gmail target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitleTv = null;
    target.userEmail = null;
    target.toAddress = null;
    target.ccAddress = null;
    target.bccAddress = null;
    target.send = null;
    target.messgge = null;
    target.subject = null;

    super.unbind();
  }
}
