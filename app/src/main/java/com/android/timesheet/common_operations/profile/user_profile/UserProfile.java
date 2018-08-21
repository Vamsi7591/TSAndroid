package com.android.timesheet.common_operations.profile.user_profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.common_operations.login.LoginActivity;
import com.android.timesheet.common_operations.password.ChangePassword;
import com.android.timesheet.common_operations.profile.admin_profile.MyProfile;
import com.android.timesheet.common_operations.profile.user_profile.users.UserProfilePresenter;
import com.android.timesheet.shared.activities.WebViewActivity;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.utils.WidgetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.TokenizeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserProfile extends BaseFragment<UserProfilePresenter> implements
        BaseViewBehavior<ProjectNamesResponse> {

//    @BindView(R.id.toolbarTitleTv)
//    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.textViewProjects)
    TokenizeTextView tokenizeTextViewProjects;

    @BindView(R.id.textViewAboutUs)
    TextView textViewAboutUs;

    @BindView(R.id.editTextName)
    EditText editTexttName;

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

    String TAG = "Profile";
    User user;


    @Override
    protected int layoutResID() {
        return R.layout.activity_user_profile;
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
    protected UserProfilePresenter providePresenter() {
        return new UserProfilePresenter(getContext(), this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        user = presenter().getCurrentUser();
        if (user != null) {
            presenter().getProjectNames(user.empCode);

            editTexttName.setText(user.getEmpName());
            editTextEmpCode.setText(user.getEmpCode());
            editTextEmail.setText(user.getEmailId());
        }

        textViewChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ChangePassword.class);
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
                Intent in =new Intent(getActivity(), LoginActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);


            }
        });


    }



    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
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
            tokenizeTextViewProjects.setText(WidgetUtils.createSpannableFromList(getContext(), projectsList));
        else {
            projectsList.add("No projects assigned.");
            tokenizeTextViewProjects.setText(WidgetUtils.createSpannableFromList(getContext(), projectsList));
        }

    }

    @Override
    public void onFailed(Throwable e) {

        App.getInstance().customToast(e.getMessage());

    }

    @OnClick(R.id.textViewAboutUs)
    void openWebView() {
        String url = AppConfig.WEB_URL_HELP_CENTER;

        Intent intent = WebViewActivity.newIntent(getContext(), "Wilco Source", url);
        startActivity(intent);
    }
}
