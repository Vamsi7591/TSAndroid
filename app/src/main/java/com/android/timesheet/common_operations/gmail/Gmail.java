package com.android.timesheet.common_operations.gmail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.utils.WidgetUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.shared.widget.TokenizeTextView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;

public class Gmail extends BaseActivity<GmailPresenter> implements
        BaseViewBehavior<Object>, OnItemClickListener, Serializable {

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.UserEmail)
    TextView userEmail;

    @BindView(R.id.toEditTxt)
    TokenizeTextView toAddress;

    @BindView(R.id.ccAddress)
    MultiAutoCompleteTextView ccAddress;

    @BindView(R.id.bccAddress)
    MultiAutoCompleteTextView bccAddress;

    @BindView(R.id.sendBtn)
    CustomFontTextView send;

    @BindView(R.id.messageET)
    EditText messgge;

    @BindView(R.id.subject_ET)
    EditText subject;

    List<Employee> employeesList;
    List<String> employeesArray;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-" +
                    "\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\" +
                    "x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[" +
                    "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|" +
                    "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\" +
                    "x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    );

    Context context;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_gmail;
    }

    @Override
    protected String title() {
        return "MAIL";
    }

    @Override
    protected GmailPresenter providePresenter() {
        return new GmailPresenter(getApplicationContext(), this);
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        employeesList = new ArrayList<Employee>();
        employeesArray = new ArrayList<>();

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.aleo_regular)));

        context = this;
        /* Get intent for Email id */
        Intent intent = getIntent();
        String s = intent.getStringExtra(AppConfig.EMPLOYEE_OBJECT);
        Gson gson = new Gson();
        User employeeObj = gson.fromJson(s, User.class);

                userEmail.setText(employeeObj.getEmailId());


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eMAil = toAddress.getText().toString();

                if (checkEmail(eMAil)) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", toAddress.getText().toString(), null));

                    intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, messgge.getText().toString());

                    startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                } else

                    Toast.makeText(context, "Enter Valid TO Address", Toast.LENGTH_SHORT).show();
            }


        });

        closeKeyBoard();

    }

    private boolean checkEmail(String eMAil) {
        if (toAddress.length() == 0) {
            Toast.makeText(context, "Enter Valid TO Address", Toast.LENGTH_SHORT).show();
//            emailTIL.setError("Email address is required");
            return false;
        } else if (!EMAIL_ADDRESS_PATTERN.matcher(eMAil).matches()) {
            Toast.makeText(context, "Enter Valid TO Address", Toast.LENGTH_SHORT).show();
//            emailTIL.setError("Invalid Email address");
            return false;
        } else {
//            emailTIL.setError(null);
            return true;
        }
    }


    public void closeKeyBoard() {
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    void updateToEmailIds() {
        /* Auto Complete MultiAutoCompleteTextView*/
//        toAddress.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
//        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, employeesArray);
//
//        toAddress.setThreshold(1);
//        toAddress.setAdapter(adp);

        toAddress.setText(WidgetUtils.createSpannableFromList(this, employeesArray));

        ccAddress.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> cc = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, employeesArray);

        ccAddress.setThreshold(1);
        ccAddress.setAdapter(cc);

        bccAddress.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> bcc = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, employeesArray);

        bccAddress.setThreshold(1);
        bccAddress.setAdapter(bcc);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(Object data) {

        User user = presenter().getCurrentUser();
        if (data instanceof AllEmployeesResponse) {

            AllEmployeesResponse allEmployeesResponse = (AllEmployeesResponse) data;
            List<Employee> employeeListResponse = allEmployeesResponse.getEmployeeList();

            if (employeeListResponse != null) {
                if (employeeListResponse.size() != 0) {
                    this.employeesList = employeeListResponse;
                }
                employeesArray = new ArrayList<>();
                for (Employee employee : employeeListResponse) {
                    if (!employee.getEmpCode().equals(user.empCode))
                        employeesArray.add(employee.empEmailId);
                }
                updateToEmailIds();
            }


        }


    }

    @Override
    protected void onResume() {
        presenter().fetchAllEmployees();
        super.onResume();
    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemClickToDelete(View view, int position) {

    }
}
