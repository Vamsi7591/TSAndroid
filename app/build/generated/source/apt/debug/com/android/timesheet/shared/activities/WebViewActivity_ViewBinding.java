// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.shared.activities;

import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.widget.CircularProgressBar;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebViewActivity_ViewBinding extends BaseActivity_ViewBinding {
  private WebViewActivity target;

  @UiThread
  public WebViewActivity_ViewBinding(WebViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebViewActivity_ViewBinding(WebViewActivity target, View source) {
    super(target, source);

    this.target = target;

    target.webView = Utils.findRequiredViewAsType(source, R.id.general_web_view, "field 'webView'", WebView.class);
    target.overlay = Utils.findRequiredView(source, R.id.overlay, "field 'overlay'");
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.circular_progress_bar, "field 'progressBar'", CircularProgressBar.class);
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'textViewToolbarTitle'", CustomFontTextView.class);
  }

  @Override
  public void unbind() {
    WebViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.webView = null;
    target.overlay = null;
    target.progressBar = null;
    target.textViewToolbarTitle = null;

    super.unbind();
  }
}
