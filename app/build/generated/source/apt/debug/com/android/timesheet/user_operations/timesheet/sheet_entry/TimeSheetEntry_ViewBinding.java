// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user_operations.timesheet.sheet_entry;

import android.support.annotation.UiThread;
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

public class TimeSheetEntry_ViewBinding extends BaseActivity_ViewBinding {
  private TimeSheetEntry target;

  private View view2131296520;

  private View view2131296602;

  private View view2131296381;

  private View view2131296493;

  private View view2131296608;

  @UiThread
  public TimeSheetEntry_ViewBinding(TimeSheetEntry target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TimeSheetEntry_ViewBinding(final TimeSheetEntry target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.toolbarTitleTv = Utils.findRequiredViewAsType(source, R.id.toolbarTitleTv, "field 'toolbarTitleTv'", CustomFontTextView.class);
    target.spinnerProjects = Utils.findRequiredViewAsType(source, R.id.spinnerProjects, "field 'spinnerProjects'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.pickerDate, "field 'pickerDate' and method 'showCustomDatePicker'");
    target.pickerDate = Utils.castView(view, R.id.pickerDate, "field 'pickerDate'", TextView.class);
    view2131296520 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showCustomDatePicker();
      }
    });
    view = Utils.findRequiredView(source, R.id.startTime, "field 'startTime' and method 'showStartTimeClock'");
    target.startTime = Utils.castView(view, R.id.startTime, "field 'startTime'", TextView.class);
    view2131296602 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showStartTimeClock();
      }
    });
    view = Utils.findRequiredView(source, R.id.endTime, "field 'endTime' and method 'showEndTimeClock'");
    target.endTime = Utils.castView(view, R.id.endTime, "field 'endTime'", TextView.class);
    view2131296381 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showEndTimeClock();
      }
    });
    target.descriptionET = Utils.findRequiredViewAsType(source, R.id.descriptionET, "field 'descriptionET'", EditText.class);
    target.description_count = Utils.findOptionalViewAsType(source, R.id.description_count, "field 'description_count'", CustomFontTextView.class);
    view = Utils.findRequiredView(source, R.id.modifyBtn, "field 'modifyBtn' and method 'modify'");
    target.modifyBtn = Utils.castView(view, R.id.modifyBtn, "field 'modifyBtn'", CustomFontTextView.class);
    view2131296493 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.modify();
      }
    });
    view = Utils.findRequiredView(source, R.id.submitBtn, "field 'submitBtn' and method 'save'");
    target.submitBtn = Utils.castView(view, R.id.submitBtn, "field 'submitBtn'", CustomFontTextView.class);
    view2131296608 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.save();
      }
    });
    target.error_project_name = Utils.findRequiredViewAsType(source, R.id.error_project_name, "field 'error_project_name'", TextView.class);
    target.error_date = Utils.findRequiredViewAsType(source, R.id.error_date, "field 'error_date'", TextView.class);
    target.error_start_time = Utils.findRequiredViewAsType(source, R.id.error_start_time, "field 'error_start_time'", TextView.class);
    target.error_end_time = Utils.findRequiredViewAsType(source, R.id.error_end_time, "field 'error_end_time'", TextView.class);
    target.error_description = Utils.findRequiredViewAsType(source, R.id.error_description, "field 'error_description'", TextView.class);
  }

  @Override
  public void unbind() {
    TimeSheetEntry target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitleTv = null;
    target.spinnerProjects = null;
    target.pickerDate = null;
    target.startTime = null;
    target.endTime = null;
    target.descriptionET = null;
    target.description_count = null;
    target.modifyBtn = null;
    target.submitBtn = null;
    target.error_project_name = null;
    target.error_date = null;
    target.error_start_time = null;
    target.error_end_time = null;
    target.error_description = null;

    view2131296520.setOnClickListener(null);
    view2131296520 = null;
    view2131296602.setOnClickListener(null);
    view2131296602 = null;
    view2131296381.setOnClickListener(null);
    view2131296381 = null;
    view2131296493.setOnClickListener(null);
    view2131296493 = null;
    view2131296608.setOnClickListener(null);
    view2131296608 = null;

    super.unbind();
  }
}
