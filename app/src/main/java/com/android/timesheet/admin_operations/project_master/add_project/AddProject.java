package com.android.timesheet.admin_operations.project_master.add_project;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.AddProjParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import butterknife.BindView;

public class AddProject extends BaseActivity<AddProjectPresenter> implements
        BaseViewBehavior<String> {

    @BindView(R.id.projectNameTIL)
    TextInputLayout projectNameTIL;

    @BindView(R.id.projectNameET)
    EditText projectNameET;

    @BindView(R.id.projectTBtn)
    ToggleButton projectTBtn;

    @BindView(R.id.submitBtn)
    CustomFontTextView submitBtn;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    public void checkField() {
        String projectName = projectNameET.getText().toString();

        if (projectName.length() < 2) {
            submitBtn.setVisibility(View.GONE);
        } else
            submitBtn.setVisibility(View.VISIBLE);
    }

    @Override
    protected String title() {
        return "Add Projects";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected AddProjectPresenter providePresenter() {
        return new AddProjectPresenter(this, this);
    }

    @Override
    protected int layoutRestID() {
        return R.layout.activity_project_add;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkField();

        projectNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkField();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkField();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkField();
            }

        });

        closeKeyBoard();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = presenter().getCurrentUser();
                if (user != null) {
                    AddProjParams addProjParams = new AddProjParams(projectNameET.getText().toString(), projectTBtn.isChecked());
                    presenter().addProjEmp(addProjParams);
                }
            }
        });

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.aleo_regular)));
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
