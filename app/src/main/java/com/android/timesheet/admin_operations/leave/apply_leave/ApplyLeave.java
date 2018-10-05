package com.android.timesheet.admin_operations.leave.apply_leave;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverview;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class ApplyLeave extends BaseActivity<ApplyLeavePresenter> implements BaseViewBehavior<List<Object>> {


    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    User user = new User();
    String leaveView = "Leave Overview";

    @Override
    protected int layoutRestID() {
        return R.layout.activity_apply_leave;
    }

    @Override
    protected String title() {
        return "Leave Overview";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected ApplyLeavePresenter providePresenter() {
        return new ApplyLeavePresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = presenter().getCurrentUser();


        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);

        Intent intent = getIntent();
        leaveView = intent.getStringExtra(Constant.KEYS.FRAGMENT_TITLE);


        toolbarTitleTv.setText(leaveView);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

         /*for(int i=0; i< subCategories.length; i++){
            adapter.addFrag(new Categoryfragment(i), subCategories[i].getSubCategoryName());
        }*/

        if (leaveView.matches("Leave Overview"))
            adapter.addFrag(new LeaveOverview(), "Leave Overview");//My Leave
        /*else
            adapter.addFrag(new LeaveCalender(), "Leave Calender View");*/


//        adapter.addFrag(new LeaveBalance(), "Leave Balance View");//Leave Balance View
//        adapter.addFrag(new HolidaysList(), "Holiday List");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(List<Object> data) {

    }

    @Override
    public void onFailed(Throwable e) {

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

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("Fragment Position===>", " " + position);
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


       /*@Override
       public int getItemPosition(Object object) {
           return POSITION_NONE;
       }*/

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
