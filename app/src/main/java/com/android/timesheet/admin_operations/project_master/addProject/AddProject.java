package com.android.timesheet.admin_operations.project_master.addProject;

import android.os.Bundle;
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
import com.android.timesheet.shared.models.AddProjParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import butterknife.BindView;

public class AddProject extends BaseActivity<AddProjectPresenter> implements
        BaseViewBehavior<String> {

    @BindView(R.id.project_Name)
    EditText projName;

    @BindView(R.id.toggle)
    ToggleButton toggleButton;

    @BindView(R.id.submit_Button)
    Button submitBtn;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

    public void checkField(){
        String projectName = projName.getText().toString();

        if (projectName.length()<2 ){
            submitBtn.setVisibility(View.GONE);
        }

        else
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
        return R.layout.activity_add_project;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkField();

        projName.addTextChangedListener(new TextWatcher() {
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
                if (user !=null) {

                    AddProjParams addProjParams = new AddProjParams(projName.getText().toString(), toggleButton.isChecked());
                    presenter().addProjEmp(addProjParams);
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
