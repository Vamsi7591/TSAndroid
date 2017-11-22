package com.android.timesheet.common_operations.landing;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.timesheet.R;
import com.android.timesheet.common_operations.slider.Slider;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.adapters.TabbedFragmentPagerAdapter;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.NonSwipeableViewPager;
import com.android.timesheet.user_operations.reports.monthly.MonthlyFragment;
import com.android.timesheet.user_operations.reports.weekly.WeeklyFragment;
import com.android.timesheet.user_operations.timesheet.sheet_fragment.TimeSheetFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class LandingActivity extends BaseActivity<LandingPresenter> {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewPagerHome)
    NonSwipeableViewPager viewPager;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;

    @BindView(R.id.mLeftDrawer)
    LinearLayout linearmLeftDrawer;

    @BindView(R.id.mainDrawerLayout)
    DrawerLayout mDrawerLayout;

    String TAG = "LandingActivity";

    private ActionBarDrawerToggle mDrawerToggle;
    int currentTab = 0;
    public static final int KEY_HOME = 0;
    public static final int KEY_WEEKLY = 1;
    public static final int KEY_MONTHLY = 2;
    public static final int KEY_PROFILE = 3;

    TimeSheetFragment timeSheetFragment;
    WeeklyFragment weeklyFragment;
    MonthlyFragment monthlyFragment;

    //    private FirebaseAnalytics firebaseAnalytics;
    TabbedFragmentPagerAdapter mTabAdapter;
    User user = new User();

    @Override
    protected int layoutRestID() {
        return R.layout.activity_landing_screen;
    }

    @Override
    protected String title() {
        return "Time Sheet";
    }

    @Override
    protected int menuResID() {
        return R.menu.home_menu;
    }

    @Override
    protected LandingPresenter providePresenter() {
        return new LandingPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = presenter().getCurrentUser();


//        Just for development mode we are disabled analytics
        /*
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString("name",user.empName);
        bundle.putString("email",user.emailId);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "user_Info");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        */

        if (user != null) {
            setUpDrawerLayout();
            new Slider(this, user);

            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
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

        timeSheetFragment = new TimeSheetFragment();
        weeklyFragment = new WeeklyFragment();
        monthlyFragment = new MonthlyFragment();

        if (mMenu == null) {
            changeTitle("Time Sheet");
            showHomeToolbar();
        }

        //TODO VIEWPAGER
        setupTabViewPager();

        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_menu_home) {
            switch (currentTab) {
                case KEY_HOME:
                    presenter().openTimeSheet();
                    break;
                default:
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTabViewPager() {
        mTabAdapter = new TabbedFragmentPagerAdapter(getSupportFragmentManager());

        mTabAdapter.addFragment(timeSheetFragment, R.string.tab_home,
                R.drawable.ic_format_line, R.drawable.ic_format_line);

        mTabAdapter.addFragment(weeklyFragment, R.string.tab_week,
                R.drawable.ic_pie_chart_black, R.drawable.ic_pie_chart_black);

        mTabAdapter.addFragment(monthlyFragment, R.string.tab_month,
                R.drawable.ic_line_chart, R.drawable.ic_line_chart);

        viewPager.setAdapter(mTabAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (appBarLayout != null) {
                    appBarLayout.setExpanded(true);
                }

                switch (position) {
                    case 0:
                        currentTab = KEY_HOME;
                        changeTitle("Time Sheet");
                        showHomeToolbar();
                        break;
                    case 1:
                        currentTab = KEY_WEEKLY;
                        changeTitle("Weekly Report");
                        clearToolbarMenu();
                        break;
                    case 2:
                        currentTab = KEY_MONTHLY;
                        changeTitle("Monthly Report");
                        clearToolbarMenu();
                        break;
                    /*case 3:
                        currentTab = KEY_INVITE;
                        changeTitle("Invitations");
                        showAddInviteToolbar();
                        break;*/
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

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    protected MenuItem menuItemSwitch = null;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuResID = menuResID();
        boolean hasOptionMenu = (menuResID > 0);

        if (hasOptionMenu) {
            getMenuInflater().inflate(menuResID, menu);
        }

        mMenu = menu;

        return hasOptionMenu || showBackButton();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        if (isAdmin)
        mDrawerToggle.syncState();
    }

    void showHomeToolbar() {
        Menu menu = toolbar.getMenu();
        if (menu == null || menu.size() == 0) {
            toolbar.inflateMenu(R.menu.home_menu);
        }
    }


    protected void clearToolbarMenu() {
        Menu menu = null;
        if (toolbar != null) {
            menu = toolbar.getMenu();
        }
        if (menu != null && menu.size() > 0) {
            menu.clear();
        }
    }

    public void changeTitle(String title) {
        textViewToolbarTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        if (title.equals("Time Sheet")) {
            textViewToolbarTitle.setText(title);
            textViewToolbarTitle.setTextSize(20);
            textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_regular)));
            lp.setMargins(0, 0, 0, 0);
            textViewToolbarTitle.setPadding(0, 0, 0, 0);
            textViewToolbarTitle.setLayoutParams(lp);
        } else {
            textViewToolbarTitle.setText(title);
            textViewToolbarTitle.setTextSize(20);
            textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_regular)));

            lp.setMargins(0, 0, 55, 0);
            textViewToolbarTitle.setPadding(0, 0, 55, 0);
            textViewToolbarTitle.setLayoutParams(lp);
        }
        //textViewToolbarTitle.setPadding(0, 0, 15, 0);

    }


    /*Nav drawer*/
    public void setUpDrawerLayout() {

        try {
            mDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
            linearmLeftDrawer = (LinearLayout) findViewById(R.id.mLeftDrawer);
            mDrawerLayout.setScrimColor(Color.TRANSPARENT);
//            mDrawerLayout.setScrimColor(getResources().getColor(R.color.white));
            mDrawerLayout.setBackgroundColor(getResources().getColor(R.color.white));
            mDrawerLayout.closeDrawer(linearmLeftDrawer);
        } catch (Exception e) {
            Log.d(TAG, "slider exce-->" + e.getMessage());
        }
    }

    public void closeDrawer() {
        try {
            mDrawerLayout.closeDrawer(linearmLeftDrawer);
        } catch (Exception e) {

            Log.d(TAG, "slider exce-->" + e.getMessage());
        }
    }
    /*End of Nav drawer*/
}
