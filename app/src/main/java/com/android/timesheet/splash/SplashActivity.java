package com.android.timesheet.splash;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.views.BaseViewBehavior;

public class SplashActivity extends BaseActivity<SplashPresenter> implements BaseViewBehavior<User> {

    public String TAG = "SplashActivity";//.this.getLocalClassName();

    /*
    * It's VIPER design pattern
    * -------------------------
    * V --> View
    * I --> Interactions
    * P --> Presenter
    * E --> Entity Model
    * R --> Router
    * */

    @Override
    protected int layoutRestID() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenter providePresenter() {
        return new SplashPresenter(this, this);
    }

    @Override
    protected boolean showBackButton() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        Log.i(TAG, "onCreate");

        User user = new User();
        user = presenter().getCurrentUser();
        onSuccess(user);

        if (user != null)
            Log.d(TAG, "stored user : " + user.toString());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(User data) {
        new Handler().postDelayed(() -> enterToApp(data), 300);
    }

    private void enterToApp(User data) {
        presenter().dispatchScreen(data);
    }

    @Override
    public void onFailed(Throwable e) {

    }
}
