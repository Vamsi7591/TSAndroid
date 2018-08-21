package com.android.timesheet.admin_operations.leave;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.ApplyLeave;
import com.android.timesheet.admin_operations.leave.apply_on_duty.ApplyOnDuty;
import com.android.timesheet.admin_operations.leave.apply_permission.ApplyPermission;
import com.android.timesheet.admin_operations.leave.approve_leave.ApproveLeave;
import com.android.timesheet.admin_operations.leave.approve_on_duty.ApproveOnDuty;
import com.android.timesheet.admin_operations.leave.approve_permission.ApprovePermission;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class LeaveActivity extends BaseActivity<LeavePresenter> {


    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.htab_tabs)
    TabLayout tabLayout;

    @BindView(R.id.htab_viewpager)
    ViewPager viewPager;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.gridView)
    GridView gridView;


    User user = new User();

    protected static int viewPagerSelected;
    protected static int currentViewPager;

    private ArrayList<String> strings;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_leave;
    }

    @Override
    protected String title() {
        return "Leave";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected LeavePresenter providePresenter() {
        return new LeavePresenter(this);
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

        collapsingToolbarLayout.setTitleEnabled(false);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //setupViewPager(viewPager);

        loadGrid();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (user.getEmpRole().equalsIgnoreCase("Admin"))
                    switch (position) {
                        case 0:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                        case 1:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                        case 2:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                        case 3:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                        case 4:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                        case 5:
                            presenter().openActivity(ApplyLeave.class);
                            break;
                    }
                else
                    switch (position) {
                        case 0:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                        case 1:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                        case 2:
                            presenter().openActivity(ApplyLeave.class);
                            break;

                    }

            }
        });

    }

    private void loadGrid() {

        strings = new ArrayList<>();
        strings.add("Apply Leave");
        strings.add("Approve Leave");
        strings.add("Apply On Duty");
        strings.add("Approve On Duty");
        strings.add("Apply Permission");
        strings.add("Approve Permission");

        LeaveAdapter booksAdapter = new LeaveAdapter(this, strings);
        gridView.setAdapter(booksAdapter);
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

    private void setupViewPager(ViewPager viewPager) {

        /*ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


         *//*for(int i=0; i< subCategories.length; i++){
            adapter.addFrag(new Categoryfragment(i), subCategories[i].getSubCategoryName());
        }*//*


        adapter.addFrag(new ApplyLeave(), "Apply Leave");
        adapter.addFrag(new ApplyOnDuty(), "Apply On Duty");
        adapter.addFrag(new ApplyPermission(), "Apply Permission");
        adapter.addFrag(new ApproveLeave(), "Approve Leave");
        adapter.addFrag(new ApproveOnDuty(), "Approve On Duty");
        adapter.addFrag(new ApprovePermission(), "Approve Permission");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);*/

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
