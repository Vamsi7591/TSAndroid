package com.android.timesheet.user.weekly;

import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class WeeklyFragment extends BaseFragment<WeeklyPresenter> {

    public WeeklyFragment() {
    }

    @Override
    protected WeeklyPresenter providePresenter() {
        return new WeeklyPresenter(getActivity());
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_weekly_report;
    }
}
