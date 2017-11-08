package com.android.timesheet.admin.project_master.proj_serialize;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.AddProjectParams;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.google.gson.Gson;

import butterknife.BindView;

public class Proj_MasterSerialize extends BaseActivity<ProjSerializePresenter>
        implements BaseViewBehavior<String> {

    @BindView(R.id.commonFlag)
    TextView commonFlag;

    @BindView(R.id.projNemeEdit)
    EditText projName;

    @BindView(R.id.projCode_Edit)
    EditText getProjectCode;

    @BindView(R.id.toggleButton)
    ToggleButton toggleButton;

    @BindView(R.id.edit_Btn)
    Button edit_Button;

    @BindView(R.id.submit_Btn)
    Button submit_Btn;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

    boolean gone = false;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_proj__master_serialize;
    }

    @Override
    protected String title() {
        return "Project Master";
    }

    @Override
    protected ProjSerializePresenter providePresenter() {
        return new ProjSerializePresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        textViewToolbarTitle.setText(title());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        projName.setEnabled(false);
        getProjectCode.setEnabled(false);
        toggleButton.setEnabled(false);

        Intent intent = getIntent();
        String s = intent.getStringExtra("jsonObject");
        Gson gson = new Gson();

        Project projData = gson.fromJson(s, Project.class);

        projName.setText(projData.getProjectName());
        getProjectCode.setText(projData.getProjectCode());

        edit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gone) {
                    submit_Btn.setVisibility(View.GONE);
                    gone = true;
                } else {
                    submit_Btn.setVisibility(View.VISIBLE);
                    gone = false;
                    projName.setEnabled(true);
                    toggleButton.setEnabled(true);
                    edit_Button.setVisibility(View.INVISIBLE);
                }
            }
        });

        submit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = presenter().getCurrentUser();

                if (user != null) {
                    AddProjectParams addProjectParams = new AddProjectParams(getProjectCode.getText().toString(), projName.getText().toString(), toggleButton.isChecked());
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
