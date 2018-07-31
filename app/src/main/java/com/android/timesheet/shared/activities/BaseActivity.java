package com.android.timesheet.shared.activities;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.presenters.Presenter;
import com.android.timesheet.shared.util.InternetConnectorReceiver;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.widget.CustomFontTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements Presenter {

    private static final String TAG = "BaseActivity";
    @Nullable
    @BindView(R.id.app_bar)
    protected AppBarLayout appBarLayout;

    @Nullable
    @BindView(R.id.coordinator_layout)
    protected CoordinatorLayout coordinatorLayout;

    @Nullable
    @BindView(R.id.toolbar_layout)
    protected CollapsingToolbarLayout toolbarLayout;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    /*@Nullable
    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;*/

    private T mPresenter;

    protected Menu mMenu;

    protected ActionBar actionBar;

    //    private FirebaseAnalytics firebaseAnalytics;
    protected boolean isBackButtonFromMain = false;

    protected int layoutRestID() {
        return -1;
    }

    protected int menuResID() {
        return -1;
    }

    protected boolean isBackButtonFromMain() {
        return isBackButtonFromMain;
    }

    protected String title() {
        return null;
    }

    protected boolean isSubscriber() {
        return false;
    }

    protected boolean showBackButton() {
        return false;
    }

    protected int resourceForUpIndicator() {
        return R.drawable.back_arrow_white;
    }

    protected boolean isToolBarTransparent() {
        return false;
    }

    @Override
    public T presenter() {
        if (mPresenter == null) {
//            throw new RuntimeException("Presenter must be initialized first");
            mPresenter = providePresenter();// returns null
        }
        return mPresenter;
    }

    protected T providePresenter() {
        return null;
    }
    //region Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new InternetConnectorReceiver(this);

//        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                FirebaseCrash.report(ex);
            }
        });*/

        /*Bundle bundle = new Bundle();
        bundle.putString("name","");
        bundle.putString("email","");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "user_Info");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/

        /*FirebaseCrash.log("Here is exception");
        FirebaseCrash.report(new Exception("oops !"));*/

        int layoutResID = layoutRestID();
        if (layoutResID > 0) {
            setContentView(layoutResID);
            ButterKnife.bind(this);
        }

        String title = title();
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            if (isToolBarTransparent()) {
                //toolbar.getBackground().setAlpha(0);
                toolbar.setBackgroundColor(Color.TRANSPARENT);
                toolbar.setTitleTextColor(Color.TRANSPARENT);
            }

        }

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (showBackButton()) {
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(resourceForUpIndicator());
            }
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/

        /*This below method is used for call back operations*/
        /*testMethod(new ServiceCallback<User>() {
            @Override
            public void onFailure(Throwable e) {

            }

            @Override
            public void onSuccess(User data) {

            }
        });*/

    }

   /* public void testMethod(ServiceCallback<User> callback) {
        callback.onFailure(null);
        callback.onSuccess(null);
    }*/

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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

//        if(mMenu!=null){
//            menuItemSwitch = mMenu.findItem(R.id.action_menu_home);
//        }

        return hasOptionMenu || showBackButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && !isBackButtonFromMain()) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void invalidateOptionsMenu() {
        supportInvalidateOptionsMenu();

        if (mMenu != null) {
            onPrepareOptionsMenu(mMenu);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*if (isSubscriber()) {
            App.getInstance().getBus().register(this);
        }*/
        App.activityResumed();
    }

    @Override
    protected void onPause() {
        /*if (isSubscriber()) {
            App.getInstance().getBus().unregister(this);
        }*/

        if (mPresenter != null) {
            presenter().onPause();
        }

        App.activityPaused();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        /*if (layoutRestID() > 0) {
            ButterKnife.bind(this);
        }*/

        super.onDestroy();
    }

    protected void clearToolbarMenu() {
        Menu menu = toolbar.getMenu();
        if (menu != null && menu.size() > 0) {
            menu.clear();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void onBackPressed() {
        if (!InternetUtils.showingDialog())
            super.onBackPressed();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
    //endregion
}
