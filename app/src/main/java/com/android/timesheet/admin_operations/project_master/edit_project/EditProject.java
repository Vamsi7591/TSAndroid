package com.android.timesheet.admin_operations.project_master.edit_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.AddProjectParams;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.google.gson.Gson;

import butterknife.BindView;

public class EditProject extends BaseActivity<EditProjectPresenter>
        implements BaseViewBehavior<String> {

   /* @BindView(R.id.commonFlag)
    TextView commonFlag;*/

    @BindView(R.id.projectNameET)
    EditText projectNameET;

    @BindView(R.id.projectCodeET)
    EditText projectCodeET;

    @BindView(R.id.projectTBtn)
    ToggleButton projectTBtn;

    @BindView(R.id.editBtn)
    CustomFontTextView editBtn;

    @BindView(R.id.submitBtn)
    CustomFontTextView submit_Btn;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    boolean gone = false;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_project_edit;
    }

    @Override
    protected String title() {
        return "Update Project";
    }

    @Override
    protected EditProjectPresenter providePresenter() {
        return new EditProjectPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_regular)));
        toolbarTitleTv.setTextSize(25);
        toolbarTitleTv.setTextColor(ContextCompat.getColor(this, R.color.white));

        projectNameET.setEnabled(false);
        projectCodeET.setEnabled(false);
        projectTBtn.setEnabled(false);

        Intent intent = getIntent();
        String projectString = intent.getStringExtra(AppConfig.PROJECT_OBJECT);
        Gson gson = new Gson();
        Project projectData = gson.fromJson(projectString, Project.class);

        projectNameET.setText(projectData.getProjectName());
        projectCodeET.setText(projectData.getProjectCode());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gone) {
                    submit_Btn.setVisibility(View.GONE);
                    gone = true;
                } else {
                    submit_Btn.setVisibility(View.VISIBLE);
                    gone = false;
                    projectNameET.setEnabled(true);
                    projectTBtn.setEnabled(true);
                    editBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        submit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = presenter().getCurrentUser();

                if (user != null) {
                    AddProjectParams addProjectParams = new AddProjectParams(projectCodeET.getText().toString(), projectNameET.getText().toString(), projectTBtn.isChecked());
                    presenter().updateEmp(addProjectParams);
                }
            }
        });
    }

    @Override
    protected boolean showBackButton() {
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
