package com.android.timesheet.user.monthly;

import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class MonthlyFragment extends BaseFragment<MonthlyPresenter>{


    public MonthlyFragment() {
    }

    @Override
    protected MonthlyPresenter providePresenter() {
        return new MonthlyPresenter(getActivity());
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_monthly_report;
    }


}
