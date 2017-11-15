package com.android.timesheet.common_operations.password;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.timesheet.R;
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

    @BindView(R.id.sbtbtn)
    Button submit;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;

    private String old_Password, new_Password, confirm_Password;

    User user;

    public void checkFieldsForEmptyValues() {

        old_Password = oldPwd.getText().toString();
        new_Password = newPwd.getText().toString();
        confirm_Password = confirmPwd.getText().toString();

        if (old_Password.isEmpty() || new_Password.isEmpty() || confirm_Password.isEmpty()) {

            submit.setVisibility(View.GONE);
            submit.setEnabled(false);
        } else {
            submit.setVisibility(View.VISIBLE);
            submit.setEnabled(true);

        }
    }

    @Override
    protected ChangePasswordPresenter providePresenter() {
        return new ChangePasswordPresenter(this, this);
    }

//    @Override
//    protected int menuResID() {
//        return R.menu.home_menu;
//    }

    @Override
    protected String title() {
        return "ChangePassword";
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
        checkFieldsForEmptyValues();

        textViewToolbarTitle.setText(title());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        confirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsForEmptyValues();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsForEmptyValues();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = presenter().getCurrentUser();
                checkFieldsForEmptyValues();

                if (!new_Password.matches(confirm_Password)
                        || (old_Password.equalsIgnoreCase(confirm_Password)
                        || old_Password.length() <= 6
                        || new_Password.length() <= 6)) {

                    Toast.makeText(getApplicationContext(), "Incorrect values entered.", Toast.LENGTH_LONG).show();

                }
                else if (user != null) {
                    ChangePasswordParams changePasswordParams = new ChangePasswordParams(user.empCode, old_Password, new_Password);
                    presenter().changePwd(changePasswordParams);

                    presenter().clearUser();
                    Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
                    startActivity(intent);

                    finish();

                }
            }
        });
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

//        if (data.getCode().equalsIgnoreCase("200")) {
//
//            presenter().clearUser();
//
//            Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
//            startActivity(intent);
//
//            finish();
//
//        }

    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
