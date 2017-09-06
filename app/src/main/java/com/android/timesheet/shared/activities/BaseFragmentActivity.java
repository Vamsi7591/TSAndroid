package com.android.timesheet.shared.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.android.timesheet.R;
import com.android.timesheet.shared.presenters.BasePresenter;


/**
 * Created by vamsikonanki on 11/30/2016.
 */

public abstract class BaseFragmentActivity<T extends BasePresenter> extends BaseActivity<T> {

    private int mContainerResId = R.id.main_container;

    protected abstract Fragment createFragment();

    protected void restoreFragment(Fragment fragment) {
        // No implementation
    }

    protected int layoutRestID() {
        return R.layout.activity_fragment;
    }

    protected Fragment getContainerFragment() {
        return getSupportFragmentManager().findFragmentById(mContainerResId);
    }

    protected void addFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().add(mContainerResId, fragment).commit();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment containerFragment = getContainerFragment();
        if (containerFragment == null) {
            addFragment(createFragment());
        } else {
            restoreFragment(containerFragment);
        }
    }


}
