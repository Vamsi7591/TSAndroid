package com.android.timesheet.common_operations.login;

import com.android.timesheet.shared.models.ValidationError;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.HashMap;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public interface LoginViewBehavior<U> extends BaseViewBehavior<U> {

    void validationError(HashMap<ValidationError, Integer> errors);
}
