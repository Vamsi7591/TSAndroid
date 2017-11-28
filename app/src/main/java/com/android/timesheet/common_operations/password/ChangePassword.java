package com.android.timesheet.common_operations.password;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.common_operations.login.LoginActivity;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.ChangePasswordParams;
import com.android.timesheet.shared.models.ChangePasswordPojo;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import butterknife.BindView;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class ChangePassword extends BaseActivity<ChangePasswordPresenter> implements
        BaseViewBehavior<ChangePasswordPojo> {

    @BindView(R.id.oldPassword)
    EditText oldPwd;

    @BindView(R.id.newPassword)
    EditText newPwd;

    @BindView(R.id.confirmPassword)
    EditText confirmPwd;

    @BindView(R.id.submitBtn)
    CustomFontTextView submitBtn;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.inputLayout_oldpassword)
    TextInputLayout inputOldPwd;

    @BindView(R.id.textNewPwd)
    TextInputLayout inputNewPwd;

    @BindView(R.id.textConfirmPwd)
    TextInputLayout inputConfirmPwd;


    @Override
    protected ChangePasswordPresenter providePresenter() {
        return new ChangePasswordPresenter(this, this);
    }

    @Override
    protected String title() {
        return "Change Password";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected int layoutRestID() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.aleo_regular)));


        oldPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkOldPassword();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkOldPassword();
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() <= 5) {
                    checkOldPassword();
                }

            }
        });

        newPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkNewPassword();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() <= 5) {
                    checkNewPassword();
                }


            }
        });

        confirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkConfirmPassword();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() <= 5) {
                    checkConfirmPassword();
                }

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = presenter().getCurrentUser();

                if (checkOldPassword() && checkNewPassword() && checkConfirmPassword()) {
                    ChangePasswordParams changePasswordParams = new ChangePasswordParams(user.empCode, oldPwd.getText().toString(), newPwd.getText().toString());
                    presenter().changePwd(changePasswordParams);

                }
            }
        });
    }


    private boolean checkOldPassword() {
        if (oldPwd.getText().toString().length() <= 0) {
            inputOldPwd.setError("Old Password is Required");

            return false;
        } else if (oldPwd.getText().toString().length() <= 5) {
            inputOldPwd.setError("Minimum 6 Characters Required");

            return false;
        }
        inputOldPwd.setError(null);
        return true;
    }

    private boolean checkNewPassword() {

        if (newPwd.getText().toString().length() <= 0) {
            inputNewPwd.setError("New Password is Required");

            return false;

        } else if (newPwd.getText().toString().length() <= 5
                ) {
            inputNewPwd.setError("Minium 6 Characters Required");

            return false;
        } else {
            inputNewPwd.setError(null);

            return true;
        }
    }

    private boolean checkConfirmPassword() {

        if (!confirmPwd.getText().toString().matches(newPwd.getText().toString())) {
            inputConfirmPwd.setError("New Password Should Match with Confirm Password");

            return false;

        } else {
            inputConfirmPwd.setError(null);

            return true;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(ChangePasswordPojo data) {
        if (data.getCode() == 200) {
            presenter().clearUser();
            Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            App.getInstance().customToast(data.getMessage());
        }

    }

//    @Override
//    public void onSuccess(ChangePasswordPojo data) {
//
//        presenter().clearUser();
//        Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//
//
//    }

    @Override
    public void onFailed(Throwable e) {

        App.getInstance().customToast(e.getMessage());

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
