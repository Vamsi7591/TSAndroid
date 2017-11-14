package com.android.timesheet.admin_operations.employee_master.add_employee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.timesheet.R;
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


    @BindView(R.id.employee_Name)
    EditText employeeName;

    @BindView(R.id.email_ID)
    EditText eMail_Id;

    @BindView(R.id.emp_Password)
    EditText password_Employee;

    @BindView(R.id.passwordInputLayout)
    TextInputLayout passwordInputLayout;

    @BindView(R.id.email_Input_layout)
    TextInputLayout input_layout_email;

    @BindView(R.id.input_Employee_name)
    TextInputLayout emapInputName;

    @BindView(R.id.adminAccessTBtn)
    ToggleButton toggleButton;

    @BindView(R.id.submit_Buttontn)
    Button submit;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    public void checkFieldsForEmptyValues() {

        String empName = employeeName.getText().toString();
        String eMAil = eMail_Id.getText().toString();
        String password = password_Employee.getText().toString();

        if (empName.isEmpty() || eMAil.isEmpty() || password.isEmpty()) {
            submit.setVisibility(View.GONE);
            submit.setEnabled(false);
        } else if (checkName()&&checkEmail(eMAil) && password() ) {
            submit.setVisibility(View.VISIBLE);
            submit.setEnabled(true);
        } else {
            submit.setVisibility(View.GONE);
            submit.setEnabled(false);
        }
    }

    @Override
    protected String title() {
        return "Add Employee Master";
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
        return R.layout.activity_add_employee;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        checkFieldsForEmptyValues();

        employeeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                checkFieldsForEmptyValues();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsForEmptyValues();
            }
        });

        eMail_Id.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                checkFieldsForEmptyValues();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsForEmptyValues();
            }
        });

        password_Employee.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                checkFieldsForEmptyValues();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsForEmptyValues();
            }
        });

        closeKeyBoard();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eMAil = eMail_Id.getText().toString();
                checkEmail(eMAil);
                User user = presenter().getCurrentUser();
                if (user != null) {
                    AddEmployeeParams addEmployeeParams = new AddEmployeeParams(
                            user.getEmpCode(),
                            employeeName.getText().toString(),
                            eMail_Id.getText().toString(),
                            password_Employee.getText().toString(),
                            toggleButton.isChecked() ? "A" : "U");
                    presenter().updateEmp(addEmployeeParams);
                }

            }
        });

        textViewToolbarTitle.setText(title());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

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

    public boolean password() {
        if (password_Employee.getText().toString().length() < 6) {
            passwordInputLayout.setError("Minimum six characters");
            return false;
        }
        passwordInputLayout.setError(null);
        return true;

    }
    private boolean checkEmail(String eMAil) {
        if( ! EMAIL_ADDRESS_PATTERN.matcher(eMAil).matches()){
            input_layout_email.setError("InValid Email");
            return false;
        } else{
            input_layout_email.setError(null);
            return true;
        }
    }

    private boolean checkName(){
        if (employeeName.getText().toString().length()<2){
            emapInputName.setError("Invalid Name");
            return  false;
        }
else {
            emapInputName.setError(null);
            return true;
        }

    }

    @Override
    public void onSuccess(String data) {
        Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
        finish();

    }


    @Override
    public void onFailed(Throwable e) {
        Toast.makeText(getBaseContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
        finish();

    }
}
