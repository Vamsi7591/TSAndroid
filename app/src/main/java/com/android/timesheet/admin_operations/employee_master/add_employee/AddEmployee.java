package com.android.timesheet.admin_operations.employee_master.add_employee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
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
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
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
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                checkFieldsForEmptyValues();
                if (s.toString().length() >= 2) {
                    checkName();
                }
            }
        });

        emailIdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                checkFieldsForEmptyValues();
                if (s.toString().length() > 6) {
                    checkEmail(s.toString());
                }
            }
        });

        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                checkFieldsForEmptyValues();
                if (s.toString().length() > 6) {
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

        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_regular)));
        toolbarTitleTv.setTextSize(25);
        toolbarTitleTv.setTextColor(ContextCompat.getColor(this, R.color.white));
        toolbarTitleTv.setText(title());


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
        } else if (passwordEt.getText().toString().length() < 6) {
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
        } else if (employeeNameEt.getText().toString().length() < 2) {
            employeeTIL.setError("Invalid Name");
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
