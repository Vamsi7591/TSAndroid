// Generated code from Butter Knife. Do not modify!
package com.android.timesheet.user.sheet_entry;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
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

  private View view2131296466;

  private View view2131296546;

  private View view2131296371;

  private View view2131296443;

  private View view2131296495;

  @UiThread
  public TimeSheetEntry_ViewBinding(TimeSheetEntry target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TimeSheetEntry_ViewBinding(final TimeSheetEntry target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.textViewToolbarTitle = Utils.findRequiredViewAsType(source, R.id.textViewToolbarTitle, "field 'textViewToolbarTitle'", CustomFontTextView.class);
    target.spinnerProjects = Utils.findRequiredViewAsType(source, R.id.spinnerProjects, "field 'spinnerProjects'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.pickerDate, "field 'pickerDate' and method 'showCustomDatePicker'");
    target.pickerDate = Utils.castView(view, R.id.pickerDate, "field 'pickerDate'", TextView.class);
    view2131296466 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showCustomDatePicker();
      }
    });
    view = Utils.findRequiredView(source, R.id.startTime, "field 'startTime' and method 'showStartTimeClock'");
    target.startTime = Utils.castView(view, R.id.startTime, "field 'startTime'", TextView.class);
    view2131296546 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showStartTimeClock();
      }
    });
    view = Utils.findRequiredView(source, R.id.endTime, "field 'endTime' and method 'showEndTimeClock'");
    target.endTime = Utils.castView(view, R.id.endTime, "field 'endTime'", TextView.class);
    view2131296371 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showEndTimeClock();
      }
    });
    target.descriptionET = Utils.findRequiredViewAsType(source, R.id.descriptionET, "field 'descriptionET'", EditText.class);
    target.description_count = Utils.findOptionalViewAsType(source, R.id.description_count, "field 'description_count'", CustomFontTextView.class);
    view = Utils.findRequiredView(source, R.id.modifyB, "field 'modifyB' and method 'modify'");
    target.modifyB = Utils.castView(view, R.id.modifyB, "field 'modifyB'", Button.class);
    view2131296443 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.modify();
      }
    });
    view = Utils.findRequiredView(source, R.id.saveB, "field 'saveB' and method 'save'");
    target.saveB = Utils.castView(view, R.id.saveB, "field 'saveB'", Button.class);
    view2131296495 = view;
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

    target.textViewToolbarTitle = null;
    target.spinnerProjects = null;
    target.pickerDate = null;
    target.startTime = null;
    target.endTime = null;
    target.descriptionET = null;
    target.description_count = null;
    target.modifyB = null;
    target.saveB = null;
    target.error_project_name = null;
    target.error_date = null;
    target.error_start_time = null;
    target.error_end_time = null;
    target.error_description = null;

    view2131296466.setOnClickListener(null);
    view2131296466 = null;
    view2131296546.setOnClickListener(null);
    view2131296546 = null;
    view2131296371.setOnClickListener(null);
    view2131296371 = null;
    view2131296443.setOnClickListener(null);
    view2131296443 = null;
    view2131296495.setOnClickListener(null);
    view2131296495 = null;

    super.unbind();
  }
}
