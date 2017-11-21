package com.android.timesheet.admin_operations.employee_master.edit_employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.UpdateEmployeeParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.google.gson.Gson;

import butterknife.BindView;

public class EditEmployee extends BaseActivity<EditEmployeePresenter>
        implements BaseViewBehavior<String> {

    @BindView(R.id.empNameEt)
    EditText empNameEt;

    @BindView(R.id.empCodeEt)
    EditText empCodeEt;

    @BindView(R.id.eMailEt)
    EditText eMailEt;

    @BindView(R.id.passwordTIL)
    TextInputLayout passwordTIL;

    @BindView(R.id.passwordEt)
    EditText passwordEt;

    @BindView(R.id.adminAccessTBtn)
    ToggleButton adminAccessTBtn;

    @BindView(R.id.editBtn)
    CustomFontTextView editBtn;

    @BindView(R.id.submitBtn)
    CustomFontTextView submitBtn;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    boolean gone = false;

    @Override
    protected String title() {
        return "Update Employee";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected EditEmployeePresenter providePresenter() {
        return new EditEmployeePresenter(this, this);
    }

    @Override
    protected int layoutRestID() {
        return R.layout.activity_employee_edit;
    }

    Animation animationFOut, animationFIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*  ToolbarTitle */

        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_regular)));
        toolbarTitleTv.setTextSize(25);
        toolbarTitleTv.setTextColor(ContextCompat.getColor(this, R.color.white));
        toolbarTitleTv.setText(title());

        /*  ToolbarTitle End */

        /*Disable fields selection initially*/
        empNameEt.setEnabled(false);
        empCodeEt.setEnabled(false);
        eMailEt.setEnabled(false);
        passwordEt.setEnabled(false);
        adminAccessTBtn.setEnabled(false);

        Intent intent = getIntent();
        String s = intent.getStringExtra(AppConfig.EMPLOYEE_OBJECT);
        Gson gson = new Gson();
        Employee employeeObj = gson.fromJson(s, Employee.class);

        empNameEt.setText(employeeObj.getEmpName());
        empCodeEt.setText(employeeObj.getEmpCode());
        eMailEt.setText(employeeObj.getEmpEmailId());
        passwordEt.setText(employeeObj.getPassword());

        closeKeyBoard();

        if (employeeObj.getEmpRole().equalsIgnoreCase("A"))
            adminAccessTBtn.setChecked(true);
        else
            adminAccessTBtn.setChecked(false);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBtn.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
                adminAccessTBtn.setEnabled(true);
                passwordEt.setEnabled(true);

                editBtn.setVisibility(View.GONE);
                animationFOut = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);
                editBtn.setAnimation(animationFOut);

                submitBtn.setVisibility(View.VISIBLE);
                animationFIn = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
                submitBtn.setAnimation(animationFIn);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = presenter().getCurrentUser();
                if (adminAccessTBtn.isChecked()) {
                    employeeObj.setEmpRole("A");
                } else
                    employeeObj.setEmpRole("U");

                if (user != null && password()) {
                    UpdateEmployeeParams updateEmployeeParams = new UpdateEmployeeParams(
                            user.empCode,
                            empCodeEt.getText().toString(),
                            passwordEt.getText().toString(),
                            employeeObj.getEmpRole()
                    );
                    presenter().updateEmp(updateEmployeeParams);
                }
            }
        });
    }

    public void closeKeyBoard() {
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public boolean password() {
        if (passwordEt.getText().toString().length() < 6) {
            passwordTIL.setError("Minimum Six characters...");
            return false;
        } else
            passwordTIL.setError(null);

        return true;
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

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
