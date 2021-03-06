package com.android.timesheet.admin_operations.employee_master.add_employee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.AddEmployeeParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.regex.Pattern;

import butterknife.BindView;

public class AddEmployee extends BaseActivity<AddEmployeePresenter> implements
        BaseViewBehavior<String> {


    @BindView(R.id.employeeNameEt)
    EditText employeeNameEt;

    @BindView(R.id.emailIdEt)
    EditText emailIdEt;

    @BindView(R.id.passwordEt)
    EditText passwordEt;

    @BindView(R.id.passwordTIL)
    TextInputLayout passwordTIL;

    @BindView(R.id.emailTIL)
    TextInputLayout emailTIL;

    @BindView(R.id.employeeTIL)
    TextInputLayout employeeTIL;

    @BindView(R.id.adminAccessTBtn)
    ToggleButton adminAccessTBtn;

    @BindView(R.id.submitBtn)
    CustomFontTextView submitBtn;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-" +
                    "\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\" +
                    "x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[" +
                    "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|" +
                    "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\" +
                    "x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    );


    Animation animationFOut, animationFIn, animationShake;

    @Override
    protected String title() {
        return "Add Employee";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected AddEmployeePresenter providePresenter() {
        return new AddEmployeePresenter(this, this);
    }

    @Override
    protected int layoutRestID() {
        return R.layout.activity_employee_add;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        employeeNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkName();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                checkFieldsForEmptyValues();
                if (s.toString().length() <= 2) {
                    checkName();
                }
            }
        });

        emailIdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkEmail(s.toString());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPassword();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                checkFieldsForEmptyValues();
                if (s.toString().length() <=5) {
                    checkPassword();
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eMAil = emailIdEt.getText().toString();
                /* If any one fails goes to else statement
                * First checkName()
                * Second checkEmail() &
                * Third checkPassword()*/
                if (checkName() && checkEmail(eMAil) && checkPassword()) {
                    User user = presenter().getCurrentUser();
                    if (user != null) {
                        AddEmployeeParams addEmployeeParams = new AddEmployeeParams(
                                user.getEmpCode(),
                                employeeNameEt.getText().toString(),
                                emailIdEt.getText().toString(),
                                passwordEt.getText().toString(),
                                adminAccessTBtn.isChecked() ? "A" : "U");
                        presenter().updateEmp(addEmployeeParams);
                    }
                } else {
                    submitBtn.setVisibility(View.GONE);
                    submitBtn.setVisibility(View.VISIBLE);
                    submitBtn.setAnimation(animationShake);
                }
            }
        });

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.aleo_regular)));

//        checkFieldsForEmptyValues();
        animationShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_animation);

        closeKeyBoard();
    }


    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    public void closeKeyBoard() {
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public boolean checkPassword() {
        if (passwordEt.getText().toString().length() == 0) {
            passwordTIL.setError("Password is required");
            return false;
        }

        else if (passwordEt.getText().toString().length() <= 5) {
            passwordTIL.setError("Minimum six characters required");
            return false;
        } else {
            passwordTIL.setError(null);
            return true;
        }
    }

    private boolean checkEmail(String eMAil) {
        if (eMAil.length() == 0) {
            emailTIL.setError("Email address is required");
            return false;
        } else if (!EMAIL_ADDRESS_PATTERN.matcher(eMAil).matches()) {
            emailTIL.setError("Invalid Email address");
            return false;
        } else {
            emailTIL.setError(null);
            return true;
        }
    }

    private boolean checkName() {
        if (employeeNameEt.getText().toString().length() == 0) {
            employeeTIL.setError("Name is required");
            return false;
        } else if (employeeNameEt.getText().toString().length() <= 2) {
            employeeTIL.setError("Minimum 3 characters required");
            return false;
        } else {
            employeeTIL.setError(null);
            return true;
        }
    }

    public void checkFieldsForEmptyValues() {

        String empName = employeeNameEt.getText().toString();
        String eMAil = emailIdEt.getText().toString();
        String password = passwordEt.getText().toString();

        if (checkName() && checkEmail(eMAil) && checkPassword()) {
            submitBtn.setVisibility(View.VISIBLE);
            animationFIn = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
            submitBtn.setAnimation(animationFIn);
        } else {
            submitBtn.setVisibility(View.VISIBLE);
            animationFOut = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);
            submitBtn.setAnimation(animationFOut);
        }
    }

    @Override
    public void onSuccess(String response) {
        App.getInstance().customToast(response);
        finish();
    }

    @Override
    public void onFailed(Throwable e) {
        App.getInstance().customToast(e.getMessage());
        finish();
    }
}
