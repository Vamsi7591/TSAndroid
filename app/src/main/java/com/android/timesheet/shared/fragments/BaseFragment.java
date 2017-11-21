package com.android.timesheet.shared.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.timesheet.app.App;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.presenters.Presenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by vamsikonanki on 3/6/16.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements Presenter {

    private T mPresenter;

    protected String mFragmentTitle;

    protected abstract int layoutResID();

    protected int menuResID() {
        return -1;
    }

    protected boolean isSubscriber() {
        return false;
    }

    Unbinder unbinder;

    @Override
    public T presenter() {
        if (mPresenter == null) {
            mPresenter = providePresenter();

            if (mPresenter == null) {
                throw new RuntimeException("Presenter must be initialized first");
            }
        }

        return mPresenter;
    }

    protected T providePresenter() {
        return null;
    }

    //region Lifecycle


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu((menuResID() > 0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResID(), container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected void initializer() {
        //TODO Some init variables
    }

    @Override
    public void onResume() {
        super.onResume();

        App.activityResumed();
    }

    @Override
    public void onPause() {

        if (mPresenter != null) {
            presenter().onPause();
        }

        App.activityPaused();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (getView() != null) {
            unbinder.unbind();
        }

        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        int menuResID = menuResID();
        boolean hasOptionMenu = (menuResID > 0);

        if (hasOptionMenu) {
            inflater.inflate(menuResID, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
        }

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constant.KEYS.FRAGMENT_TITLE, getPageTitle());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            mFragmentTitle = savedInstanceState.getString(Constant.KEYS.FRAGMENT_TITLE);
        }
    }

    @Override
    public Context getContext() {
        return getBaseContext();
    }

    //endregion

    //region Public & Private methods

    public void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public Context getBaseContext() {
        if (getActivity() != null) {
            return getActivity();
        } else {
            return App.getAppContext();
        }
    }

    protected BaseActivity getBaseActivity() {
        if (getActivity() != null) {
            return (BaseActivity) getActivity();
        }

        return null;
    }

    public String getPageTitle() {
        return mFragmentTitle;
    }

    public void setPageTitle(String title) {
        mFragmentTitle = title;
    }

    public void setTitle(String title) {
        if (getBaseActivity() != null) {
            getBaseActivity().setTitle(title);
        }
    }


    //endregion
}
