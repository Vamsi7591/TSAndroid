package com.android.timesheet.admin_operations.emp_serialize;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.UpdateEmployeeParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.google.gson.Gson;

import butterknife.BindView;

public class EmpMaster_Serialize extends BaseActivity<EmpSerializePresenter>
        implements BaseViewBehavior<String> {

    @BindView(R.id.emp_Name)
    EditText empName;

    @BindView(R.id.emp_code)
    EditText empCode;

    @BindView(R.id.input_email)
    EditText eMail;

    @BindView(R.id.inputLayout_password)
    TextView inputPassword;

    @BindView(R.id.password_Emp)
    EditText password;

    @BindView(R.id.toggleButton)
    ToggleButton toggleButton;

    @BindView(R.id.edit_ButtoN)
    Button edit;

    @BindView(R.id.submit_Btn)
    Button submit;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

    boolean gone = false;

    @Override
    protected String title() {
        return "Employee Master";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected EmpSerializePresenter providePresenter() {
        return new EmpSerializePresenter(this, this);
    }

    @Override
    protected int layoutRestID() {
        return R.layout.activity_emp_master__serialize;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gone) {
                    submit.setVisibility(View.GONE);
                    gone = true;
                } else {
                    submit.setVisibility(View.VISIBLE);
                    gone = false;
                    toggleButton.setEnabled(true);
                    password.setEnabled(true);
                    edit.setVisibility(View.INVISIBLE);
                }
/*                else
                    submit.setVisibility(View.GONE);
                   gone = true;*/
            }
        });

/*  ToolbarTitle */
        textViewToolbarTitle.setText(title());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        /*  ToolbarTitle End */

        empName.setEnabled(false);
        empCode.setEnabled(false);
        eMail.setEnabled(false);
        password.setEnabled(false);
        toggleButton.setEnabled(false);

        Intent intent = getIntent();
        String s = intent.getStringExtra("jsonObject");
        Gson gson = new Gson();
        Employee empData = gson.fromJson(s, Employee.class);

        empName.setText(empData.getEmpName());
        empCode.setText(empData.getEmpCode());
        eMail.setText(empData.getEmpEmailId());
        password.setText(empData.getPassword());

        closeKeyBoard();

        if (empData.getEmpRole().equalsIgnoreCase("A"))
            toggleButton.setChecked(true);
        else
            toggleButton.setChecked(false);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = presenter().getCurrentUser();
                if (toggleButton.isChecked()) {
                    empData.setEmpRole("A");
                } else
                    empData.setEmpRole("U");

                if (user != null && password()) {
                    UpdateEmployeeParams updateEmployeeParams = new UpdateEmployeeParams(user.empCode, empCode.getText().toString(), password.getText().toString(), empData.getEmpRole());
                    presenter().updateEmp(updateEmployeeParams);
//                    submit.isEnabled();
                }
            }
        });
    }

    public void closeKeyBoard() {
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public boolean password() {
        if (password.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Minimum Six", Toast.LENGTH_LONG).show();
            inputPassword.setError("Password Length Error");
            return false;
        } else

            inputPassword.setError(null);
        return true;

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

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
