package com.android.timesheet.user_operations.report_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.common_operations.landing.LandingPresenter;
import com.android.timesheet.common_operations.slider.Slider;
import com.android.timesheet.shared.adapters.TabbedFragmentPagerAdapter;
import com.android.timesheet.shared.events.TimeSheetValidEvent;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.NonSwipeableViewPager;
import com.android.timesheet.user_operations.reports.monthly.MonthlyFragment;
import com.android.timesheet.user_operations.reports.weekly.WeeklyFragment;
import com.android.timesheet.user_operations.reports.yearly.Yearly_Fragment;

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

    String TAG = "ReportFragmentActivity";

    private ActionBarDrawerToggle mDrawerToggle;

    int currentTab = 0;

    public static final int KEY_WEEKLY = 1;
    public static final int KEY_MONTHLY = 2;
    public static final int KEY_YEARLY = 3;

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

        App.getInstance().getBus().register(this);



        if (user != null) {
            setUpDrawerLayout();
            new Slider(getActivity(), user);

            mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                    toolbar, R.string.app_name, R.string.app_name) {
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                }

                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                public void onDrawerSlide(View drawerView, float slideOffset) {
                    float min = 0.7f;
                    float max = 1.0f;
                    float scaleFactor = (max - ((max - min) * slideOffset));
                    float moveFactor = (linearmLeftDrawer.getWidth() * slideOffset);

                    coordinatorLayout.setTranslationX(moveFactor);
                    coordinatorLayout.setScaleY(scaleFactor);
                    coordinatorLayout.setScaleX(scaleFactor);
                }
            };
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerLayout.setDrawerElevation(0f);
        }


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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 1:
                        currentTab = KEY_WEEKLY;
                        changeTitle("Weekly Report");
                        break;

                    case 2:
                        currentTab = KEY_MONTHLY;
                        changeTitle("Monthly Report");

                        break;
                    case 3:
                        currentTab = KEY_YEARLY;
                        changeTitle("Yearly Report");

                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setVisibility(View.VISIBLE);

        List<TabbedFragmentPagerAdapter.TabInfo> tabInfo = mTabAdapter.getAllTabs();
        for (int i = 0; i < tabInfo.size(); i++) {
            tabLayout.getTabAt(i).setCustomView(R.layout.base_tabs_customtab);//.setTag(i);

            if (tabLayout.getTabAt(i).isSelected()) {

                tabLayout.getTabAt(i).setIcon(tabInfo.get(i).activeIcon);
            } else {
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
                //setTitle(tab.getText());
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

                if (position==1) {

                    App.getInstance().getBus().post(new TimeSheetValidEvent(true));
                }

            }
        });


    }


    public void changeTitle(String title) {
        textViewToolbarTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)
                textViewToolbarTitle.getLayoutParams();
        if (title.equals("Weekly Report")) {
            textViewToolbarTitle.setText(title);
            textViewToolbarTitle.setTextSize(22);
            textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(getActivity(), getString(R.string.aleo_regular)));
            lp.setMargins(0, 0, 0, 0);
            textViewToolbarTitle.setPadding(0, 0, 0, 0);
            textViewToolbarTitle.setLayoutParams(lp);
        } else {
            textViewToolbarTitle.setText(title);
            textViewToolbarTitle.setTextSize(22);
            textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(getActivity(), getString(R.string.aleo_regular)));

            lp.setMargins(0, 0, 55, 0);
            textViewToolbarTitle.setPadding(0, 0, 55, 0);
            textViewToolbarTitle.setLayoutParams(lp);
        }


    }


    /*Nav drawer*/
    public void setUpDrawerLayout() {

        try {

            mDrawerLayout.setScrimColor(Color.TRANSPARENT);

            mDrawerLayout.setBackgroundColor(getResources().getColor(R.color.white));
            mDrawerLayout.closeDrawer(linearmLeftDrawer);
        } catch (Exception e) {
            Log.d(TAG, "slider exce-->" + e.getMessage());
        }
    }

}
