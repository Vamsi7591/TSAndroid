package com.android.timesheet.common_operations.login;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.ValidationError;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginViewBehavior<User> {


    @BindView(R.id.textInputLayoutECode)
    TextInputLayout textInputLayoutECode;
    @BindView(R.id.textInputLayoutPassword)
    TextInputLayout textInputLayoutPassword;

    @BindView(R.id.editTextECode)
    EditText editTextECode;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.textViewLogin)
    CustomFontTextView textViewLogin;

    /*@BindViews({R.id.textInputLayoutECode, R.id.textInputLayoutPassword})
    TextInputLayout textInputLayoutECode, textInputLayoutPassword;

    @BindViews({R.id.editTextECode, R.id.editTextPassword})
    EditText editTextECode, editTextPassword;*/

    /*Old code
    @Bind(R.id.textViewLogin)
    TextView textViewLogin;*/

    private static final String TAG = "LoginActivity";
    Animation animationShake;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter(this, this);
    }

    @Override
    protected boolean showBackButton() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* @implSpec Instead of regular {@code setContentView(R.layout.activity_login)} syntax
         * here I'm using {@code layoutResID()} method by following VIPER design pattern coding principal's
         *
           int layoutResID = layoutRestID();
           if (layoutResID > 0) {
              setContentView(layoutResID);
              ButterKnife.bind(this);
           }
         */



        try {
            editTextPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editTextPassword.setSelection(0);
                    editTextPassword.setSelection(editTextPassword.getText().length());
                }
            });
            editTextPassword.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            editTextPassword.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");

        User user = new User();
        user = presenter().getCurrentUser();

        if (user != null) {
            Log.d(TAG, "stored user : " + user.toString());
            onSuccess(user);
        }

        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        animationShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_animation);

        super.onResume();
    }

    @Override
    public void onLoading() {
        Log.i(TAG, "onLoading");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onComplete() {
        Log.i(TAG, "onComplete");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(User data) {
        onComplete();

        if (data != null) {
            presenter().openMainActivity();
        }
    }

    @Override
    public void onFailed(Throwable e) {
        onComplete();
        App.getInstance().customToast(e.getMessage());
        /*editTextECode.setText("");
        editTextPassword.setText("");*/

        textViewLogin.setVisibility(View.GONE);
        textViewLogin.setVisibility(View.VISIBLE);
        textViewLogin.setAnimation(animationShake);

        editTextPassword.setSelection(editTextPassword.getText().length());
    }

    @Override
    public void validationError(HashMap<ValidationError, Integer> errors) {
        Log.i(TAG, "validationError");
        handleError(errors);

        textViewLogin.setVisibility(View.GONE);
        textViewLogin.setVisibility(View.VISIBLE);
        textViewLogin.setAnimation(animationShake);
    }

    @OnClick(R.id.textViewLogin)
    public void login() {
        clearErrors();
        if (InternetUtils.isInternetConnected(this)) {

            presenter().submitLogin(editTextECode.getText(), editTextPassword.getText());
            /* To hard code inputs use below values
              "E010", "1234567"
              */
        } else {
            infoSnackBar(   getString(R.string.no_internet_connection));
        }
    }

    void clearErrors() {
        textInputLayoutECode.setErrorEnabled(false);
        textInputLayoutECode.setError(null);

        textInputLayoutPassword.setErrorEnabled(false);
        textInputLayoutPassword.setError(null);

        /*editTextECode.setCursorVisible(false);
        editTextPassword.setCursorVisible(false);*/
    }

    public void infoSnackBar(String msg) {
        if (coordinatorLayout != null) {
            Snackbar snack = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
            View view = snack.getView();
            TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.RED);
            tv.setMaxLines(4);
            tv.setTextSize(18);
            snack.show();
        }
    }

    void handleError(HashMap<ValidationError, Integer> errors) {
        for (ValidationError error : errors.keySet()) {
            String errorStr = getString(errors.get(error));

            if (error.equals(ValidationError.PASSWORD)) {

                showError(textInputLayoutPassword, errorStr);
                editTextPassword.setSelection(editTextPassword.getText().length());
            } else if (error.equals(ValidationError.EMP_CODE)) {
                showError(textInputLayoutECode, errorStr);
                editTextECode.setSelection(editTextECode.getText().length());
            }
        }
    }

    void showError(TextInputLayout inputLayout, String errorStr) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(errorStr);
        inputLayout.requestFocus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Inside onResume and onDestroy
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            clipboardManager.setText("");
        }
    }
}
