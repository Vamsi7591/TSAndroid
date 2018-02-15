package com.android.timesheet.common_operations.profile.admin_profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.common_operations.login.LoginActivity;
import com.android.timesheet.common_operations.password.ChangePassword;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.activities.WebViewActivity;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.TokenizeTextView;
import com.android.timesheet.shared.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class MyProfile extends BaseActivity<MyProfilePresenter> implements
        BaseViewBehavior<ProjectNamesResponse> {

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.textViewProjects)
    TokenizeTextView tokenizeTextViewProjects;

    @BindView(R.id.textViewAboutUs)
    TextView textViewAboutUs;

    @BindView(R.id.editTextFirstName)
    EditText editTextFirstName;

    @BindView(R.id.editTextEmpCode)
    EditText editTextEmpCode;

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.textViewChangePassword)
    TextView textViewChangePassword;

    @BindView(R.id.textViewContactUs)
    TextView textViewContactUs;

    @BindView(R.id.textViewLogOut)
    TextView textViewLogOut;

//    @BindView(R.id.sendMail)
//    TextView textSendEmail;

    public static final String NUMBER = "8012841680";

    String TAG = "Profile";
    User user;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_my_profile;
    }

    @Override
    protected String title() {
        return "Profile";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected MyProfilePresenter providePresenter() {
        return new MyProfilePresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        textSendEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                User user = presenter().getCurrentUser();
//                Gson gson = new Gson(); //Convert object to string using Gson()
//                String employeeJson = gson.toJson(user);//employeesList.get(position)
//                Intent intent = new Intent(MyProfile.this, Gmail.class);
//                intent.putExtra(AppConfig.EMPLOYEE_OBJECT, employeeJson);
//
//
//                startActivity(intent);
//
//
//            }
//
//        });

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.aleo_regular)));

        user = presenter().getCurrentUser();
        if (user != null) {
            presenter().getProjectNames(user.empCode);
            editTextFirstName.setText(user.getEmpName());
            editTextEmpCode.setText(user.getEmpCode());
            editTextEmail.setText(user.getEmailId());
        }


    }

    public void closeKeyBoard() {
        // Check if no view has focus:
        View view = MyProfile.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) MyProfile.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        textViewChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(i);

            }
        });

        textViewContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + MyProfile.NUMBER));
                startActivity(intent);
            }
        });

        textViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter().clearUser();
                Intent in =new Intent(getApplicationContext(), LoginActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);


            }
        });

    }

    @Override
    protected void onResume() {

        closeKeyBoard();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(ProjectNamesResponse data) {

        List<String> projectsList = new ArrayList<>();

        if (data.status && data.getProjectList() != null)
            for (Project list :
                    data.getProjectList()) {
                if (!list.commonFlag)
                    projectsList.add(list.getProjectName());
            }

        if (projectsList.size() != 0)
            tokenizeTextViewProjects.setText(WidgetUtils.createSpannableFromList(this, projectsList));
        else {
            projectsList.add("No projects assigned.");
            tokenizeTextViewProjects.setText(WidgetUtils.createSpannableFromList(this, projectsList));
        }

    }

    @Override
    public void onFailed(Throwable e) {

        App.getInstance().customToast(e.getMessage());

    }

    @OnClick(R.id.textViewAboutUs)
    void openWebView() {
        String url = AppConfig.WEB_URL_HELP_CENTER;

        Intent intent = WebViewActivity.newIntent(this, "Wilco Source", url);
        startActivity(intent);
    }
}
