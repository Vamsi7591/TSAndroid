package com.android.timesheet.user_operations.report_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.common_operations.landing.LandingActivity;
import com.android.timesheet.common_operations.landing.LandingPresenter;
import com.android.timesheet.shared.adapters.TabbedFragmentPagerAdapter;
import com.android.timesheet.shared.events.TabPositionEvent;
import com.android.timesheet.shared.events.TimeSheetValidEvent;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.NonSwipeableViewPager;
import com.android.timesheet.user_operations.reports.monthly.MonthlyFragment;
import com.android.timesheet.user_operations.reports.weekly.WeeklyFragment;
import com.android.timesheet.user_operations.reports.yearly.Yearly_Fragment;
import com.squareup.otto.Bus;

import java.util.List;

import butterknife.BindView;

public class ReportFragmentActivity extends BaseFragment<LandingPresenter> {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewPagerHome)
    NonSwipeableViewPager viewPager;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;

    @Nullable
    @BindView(R.id.mLeftDrawer)
    LinearLayout linearmLeftDrawer;

    @Nullable
    @BindView(R.id.mainDrawerLayout)
    DrawerLayout mDrawerLayout;

//    public static final int KEY_PROFILE = 3;

    WeeklyFragment weeklyFragment;
    MonthlyFragment monthlyFragment;
    Yearly_Fragment yearly_fragment;

//    private FirebaseAnalytics firebaseAnalytics;

    TabbedFragmentPagerAdapter mTabAdapter;
    User user = new User();

    @Override
    protected String title() {
        return "Reports";
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_report_fragment;
    }

    @Override
    protected LandingPresenter providePresenter() {
        return new LandingPresenter(getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        user = presenter().getCurrentUser();

        weeklyFragment = new WeeklyFragment();
        monthlyFragment = new MonthlyFragment();
        yearly_fragment = new Yearly_Fragment();

        setupTabViewPager();

    }


    private void setupTabViewPager() {

        mTabAdapter = new TabbedFragmentPagerAdapter(getChildFragmentManager());

        mTabAdapter.addFragment(weeklyFragment, R.string.tab_week,
                R.drawable.ic_pie_chart_black, R.drawable.ic_pie_chart_black);

        mTabAdapter.addFragment(monthlyFragment, R.string.tab_month,
                R.drawable.ic_line_chart, R.drawable.ic_line_chart);

        mTabAdapter.addFragment(yearly_fragment, R.string.tab_year,
                R.drawable.ic_year, R.drawable.ic_year);

        viewPager.setOffscreenPageLimit(1);

        viewPager.setAdapter(mTabAdapter);
        viewPager.setCurrentItem(0);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setVisibility(View.VISIBLE);

        List<TabbedFragmentPagerAdapter.TabInfo> tabInfo = mTabAdapter.getAllTabs();

        for (int i = 0; i < tabInfo.size(); i++) {

            tabLayout.getTabAt(i).setCustomView(R.layout.base_tabs_customtab);//.setTag(i);

            if (tabLayout.getTabAt(i).isSelected()) {

                tabLayout.getTabAt(i).setIcon(tabInfo.get(i).activeIcon);
            }

            else {
                tabLayout.getTabAt(i).setIcon(tabInfo.get(i).icon);
            }
        }

        tabLayout.getTabAt(0).getCustomView().setSelected(true);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //int position = (int) tab.getTag();
                int position = (int) tab.getPosition();
                tab.setIcon(tabInfo.get(position).activeIcon);
                viewPager.setCurrentItem(position);

                App.getInstance().getBus().post(new TabPositionEvent(position));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //int position = (int) tab.getTag();
                int position = (int) tab.getPosition();
                tab.setIcon(tabInfo.get(position).icon);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = (int) tab.getPosition();
            }
        });
    }

    @Override
    public void onResume() {

        App.getInstance().getBus().register(this);
        super.onResume();
    }

    @Override
    public void onPause() {

        App.getInstance().getBus().unregister(this);
        super.onPause();
    }
}
