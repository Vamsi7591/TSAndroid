package com.android.timesheet.shared.presenters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

import com.android.timesheet.shared.interactors.BaseInteractor;
import com.android.timesheet.shared.routers.BaseRouter;
import com.android.timesheet.shared.views.BaseViewBehavior;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public abstract class BasePresenter<V extends BaseViewBehavior, I extends BaseInteractor, R extends BaseRouter> {

    private V mViewBehavior;

    private I mInteractor;

    private R mRouter;

    protected Context context;

    public BasePresenter(Context context) {
        this.context = context;
    }

    public BasePresenter(Context context, V mViewBehavior) {
        this.context = context;
        this.mViewBehavior = mViewBehavior;
    }

    /**
     * @return mViewBehavior - View component
     *
     *
     *
     */
    protected V viewBehaviour() {
        return mViewBehavior;
    }

    protected abstract I provideInteractor();

    /**
     * @return mInteractor -
     */
    protected I interactor() {
        if (mInteractor == null) {
            mInteractor = provideInteractor();
        }

        return mInteractor;
    }

    protected abstract R provideRouter();

    protected R router() {
        if (mRouter == null) {
            mRouter = provideRouter();
        }

        return mRouter;
    }

    public void onPause() {
        if (interactor() != null) {
            interactor().unsubscribe();
        }
    }

    public void openMainActivity()
        {
        router().openMainActivity();
    }


    @SuppressLint("HardwareIds")
    public String getSecureAndroidId() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
