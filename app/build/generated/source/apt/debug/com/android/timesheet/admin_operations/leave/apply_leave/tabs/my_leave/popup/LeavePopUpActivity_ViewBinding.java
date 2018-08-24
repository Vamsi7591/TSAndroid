// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.admin_operations.leave.apply_leave.tabs.my_leave.popup;

import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity_ViewBinding;
import com.android.timesheet.shared.widget.CustomFontTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeavePopUpActivity_ViewBinding extends BaseActivity_ViewBinding {
  private LeavePopUpActivity target;

  private View view2131296411;

  private View view2131296680;

  private View view2131296639;

  @UiThread
  public LeavePopUpActivity_ViewBinding(LeavePopUpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LeavePopUpActivity_ViewBinding(final LeavePopUpActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.leaveTypeSP = Utils.findRequiredViewAsType(source, R.id.leaveTypeSP, "field 'leaveTypeSP'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.fromDate, "field 'fromDate' and method 'showFromDatePicker'");
    target.fromDate = Utils.castView(view, R.id.fromDate, "field 'fromDate'", TextView.class);
    view2131296411 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showFromDatePicker();
      }
    });
    view = Utils.findRequiredView(source, R.id.toDate, "field 'toDate' and method 'showToDatePicker'");
    target.toDate = Utils.castView(view, R.id.toDate, "field 'toDate'", TextView.class);
    view2131296680 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showToDatePicker();
      }
    });
    target.remarksET = Utils.findRequiredViewAsType(source, R.id.remarksET, "field 'remarksET'", EditText.class);
    target.remarks_count = Utils.findOptionalViewAsType(source, R.id.remarks_count, "field 'remarks_count'", CustomFontTextView.class);
    view = Utils.findRequiredView(source, R.id.submitBtn, "field 'submitBtn' and method 'save'");
    target.submitBtn = Utils.castView(view, R.id.submitBtn, "field 'submitBtn'", CustomFontTextView.class);
    view2131296639 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.save();
      }
    });
    target.error_leave_type = Utils.findRequiredViewAsType(source, R.id.error_leave_type, "field 'error_leave_type'", TextView.class);
    target.error_from_date = Utils.findRequiredViewAsType(source, R.id.error_from_date, "field 'error_from_date'", TextView.class);
    target.error_to_date = Utils.findRequiredViewAsType(source, R.id.error_to_date, "field 'error_to_date'", TextView.class);
    target.error_remarks = Utils.findRequiredViewAsType(source, R.id.error_remarks, "field 'error_remarks'", TextView.class);
  }

  @Override
  public void unbind() {
    LeavePopUpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.leaveTypeSP = null;
    target.fromDate = null;
    target.toDate = null;
    target.remarksET = null;
    target.remarks_count = null;
    target.submitBtn = null;
    target.error_leave_type = null;
    target.error_from_date = null;
    target.error_to_date = null;
    target.error_remarks = null;

    view2131296411.setOnClickListener(null);
    view2131296411 = null;
    view2131296680.setOnClickListener(null);
    view2131296680 = null;
    view2131296639.setOnClickListener(null);
    view2131296639 = null;

    super.unbind();
  }
}
