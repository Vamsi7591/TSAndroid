package com.android.timesheet.auth;

import com.android.timesheet.shared.models.ValidationError;
import com.android.timesheet.shared.views.BaseViewBehavior;

import java.util.HashMap;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public interface AuthViewBehavior<U> extends BaseViewBehavior<U> {

    void validationError(HashMap<ValidationError, Integer> errors);
}
