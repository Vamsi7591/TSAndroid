package com.android.timesheet.admin_operations.employee_project;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.timesheet.R;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.AssignEmpParams;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.user_operations.timesheet.sheet_entry.ProjectsSpinnerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class EmployeeProject extends BaseActivity<EmployeeProjectPresenter> implements
        BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.assignProject)
    Button assignTabBtn;

    @BindView(R.id.removeProject)
    Button removeTabBtn;

    @BindView(R.id.employeeName)
    Spinner empNameSp;

    @BindView(R.id.projectName)
    Spinner projectNameSp;

    @BindView(R.id.assignProjectToEmp)
    Button assignProjectToEmpBtn;

    @BindView(R.id.removeProjectFromEmp)
    Button removeProjectFromEmpBtn;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    boolean isAssignVisible = true;
    ArrayAdapter adapter;

    ArrayList<String> employeeSpinnerList = new ArrayList<>();
    ArrayList<String> projectSpinnerList = new ArrayList<>();

    //    List<String> employeeSpinnerList;
//    List<String> projectSpinnerList;
    List<Employee> employeesList;
    List<Project> projectsList;
    int selectedEmployeeNamePos = 0;

    private static final String TAG = "Employee_Project";

    @Override
    protected int layoutRestID() {
        return R.layout.activity_employee_project;
    }

    @Override
    protected String title() {
        return "Employee Project";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected EmployeeProjectPresenter providePresenter() {
        return new EmployeeProjectPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.aleo_regular)));

        employeesList = new ArrayList<>();
        projectsList = new ArrayList<>();
        employeeSpinnerList = new ArrayList<>();
        projectSpinnerList = new ArrayList<>();

        empNameSp.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        projectNameSp.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        User user = presenter().getCurrentUser();
        if (user != null) {
            presenter().getAllEmpDetails();
        }

        assignTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAssignVisible = true;
                assignTabBtn.setTextSize(20);
                assignTabBtn.setTypeface(null, Typeface.BOLD);
                assignTabBtn.setTextColor(getResources().getColor(R.color.white));

                removeTabBtn.setTextSize(18);
                removeTabBtn.setTypeface(null, Typeface.NORMAL);
                removeTabBtn.setTextColor(getResources().getColor(R.color.colorWhite115));

                clearUI(true);

            }
        });

        removeTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAssignVisible = false;
                removeTabBtn.setTextSize(20);
                removeTabBtn.setTypeface(null, Typeface.BOLD);
                removeTabBtn.setTextColor(getResources().getColor(R.color.white));

                assignTabBtn.setTextSize(18);
                assignTabBtn.setTypeface(null, Typeface.NORMAL);
                assignTabBtn.setTextColor(getResources().getColor(R.color.colorWhite115));

                clearUI(true);
            }
        });

        assignProjectToEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "AssignEmployeesToProject : " +
                        empNameSp.getSelectedItem().toString()
                        + " : " + employeesList.get(selectedEmployeeNamePos - 1).getEmpCode()
                        + " : " + projectNameSp.getSelectedItem().toString());

                // Service Call //
                User user = presenter().getCurrentUser();
                if (user != null) {
                    AssignEmpParams assignEmpParams = new AssignEmpParams(employeesList.get(selectedEmployeeNamePos - 1).empCode, projectNameSp.getSelectedItem().toString());
                    presenter().assignEmp(assignEmpParams);
                }

                clearUI(true);
            }
        });

        removeProjectFromEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "RemoveEmployeesFromProject : " +
                        empNameSp.getSelectedItem().toString()
                        + " : " + employeesList.get(selectedEmployeeNamePos - 1).getEmpCode()
                        + " : " + projectNameSp.getSelectedItem().toString());

                // Service Calls //
                User user = presenter().getCurrentUser();
                if (user != null) {
                    AssignEmpParams assignEmpParams = new AssignEmpParams(employeesList.get(selectedEmployeeNamePos - 1).empCode, projectNameSp.getSelectedItem().toString());
                    presenter().removeEmp(assignEmpParams);

                }

                clearUI(true);
            }
        });


        empNameSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                /*If user selected spinner call projects service*/

                selectedEmployeeNamePos = empNameSp.getSelectedItemPosition();

                if (isAssignVisible) {
                    // Assign
                    if (!empNameSp.getSelectedItem().toString().isEmpty()) {

                        if (!empNameSp.getSelectedItem().toString().equalsIgnoreCase("Select")) {
                            presenter().unAssignProj(employeesList.get(selectedEmployeeNamePos - 1).empCode);
                        } else {
                            projectNameSp.setAdapter(null);
                            clearUI(false);
                        }
                    }
                } else {
                    // Remove
                    if (!empNameSp.getSelectedItem().toString().isEmpty()) {

                        if (!empNameSp.getSelectedItem().toString().equalsIgnoreCase("Select")) {
                            presenter().getProjectNames(employeesList.get(selectedEmployeeNamePos - 1).empCode);
                        } else {
                            projectNameSp.setAdapter(null);
                            clearUI(false);
                        }
                    }

                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        projectNameSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (isAssignVisible) {
                    // Assign
                    if (!projectNameSp.getSelectedItem().toString().isEmpty()) {
                        if (!projectNameSp.getSelectedItem().toString().equalsIgnoreCase("Select"))
                            assignProjectToEmpBtn.setVisibility(View.VISIBLE);
                        else
                            assignProjectToEmpBtn.setVisibility(View.GONE);
                    }
                } else {
                    // Remove
                    if (!projectNameSp.getSelectedItem().toString().isEmpty()) {
                        if (!projectNameSp.getSelectedItem().toString().equalsIgnoreCase("Select"))
                            removeProjectFromEmpBtn.setVisibility(View.VISIBLE);
                        else
                            removeProjectFromEmpBtn.setVisibility(View.GONE);

                    }
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void clearUI(boolean flag) {


        if (flag) {

            ProjectsSpinnerAdapter forEmpNAme = new ProjectsSpinnerAdapter(EmployeeProject.this, employeeSpinnerList);
            empNameSp.setAdapter(forEmpNAme);

//            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employeeSpinnerList);
//            empNameSp.setAdapter(adapter);
        }

        projectNameSp.setAdapter(null);
        assignProjectToEmpBtn.setVisibility(View.GONE);
        removeProjectFromEmpBtn.setVisibility(View.GONE);

    }


    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(Object o) {

        if (o instanceof ProjectNamesResponse) {
            /*Projects response List<Projects>*/
            ProjectNamesResponse projectNamesResponse = (ProjectNamesResponse) o;

            projectsList = new ArrayList<>();
            projectsList = projectNamesResponse.getProjectList();

            projectSpinnerList = new ArrayList<>();
            projectSpinnerList.add("Select");

            if (projectsList != null) {
                for (int i = 0; i < projectsList.size(); i++) {
                    if (!projectsList.get(i).commonFlag) {
                        projectSpinnerList.add(projectsList.get(i).getProjectName());

                    }
                }
//                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projectSpinnerList);
//                projectNameSp.setAdapter(adapter);

                ProjectsSpinnerAdapter forProjevtNAme = new ProjectsSpinnerAdapter(EmployeeProject.this, projectSpinnerList);
                projectNameSp.setAdapter(forProjevtNAme);
            }

        } else if (o instanceof AllEmployeesResponse) {
            /*Employees response List<Employees>*/
            AllEmployeesResponse response = (AllEmployeesResponse) o;

            employeesList = new ArrayList<>();
            employeesList = response.getEmployeeList();

            employeeSpinnerList = new ArrayList<>();
            employeeSpinnerList.add("Select");

            for (int i = 0; i < employeesList.size(); i++) {

                User user = presenter().getCurrentUser();

                /* Object wise operation on for loop - preferred way*/
                for (Employee employee : employeesList) {
                    if (employee.getEmpCode().equals(user.empCode)) {
                        employeesList.remove(employee);
                        break;
                    }
                }

                employeeSpinnerList.add(employeesList.get(i).getEmpName());
            }

//            User user = presenter().getCurrentUser();
//
//                    /* Object wise operation on for loop - preferred way*/
//            for (Employee employee : employeesList) {
//                if (employee.getEmpCode().equals(user.empCode)) {
//                    employeesList.remove(employee);
//                    break;
//                }
//            }
//            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employeeSpinnerList);
////        employee_Name.setPrompt("Select Category");
//            empNameSp.setAdapter(adapter);


            ProjectsSpinnerAdapter forEmpNAme = new ProjectsSpinnerAdapter(EmployeeProject.this, employeeSpinnerList);
            empNameSp.setAdapter(forEmpNAme);
        } else if (o instanceof String) {
            /*Assign or removeTabBtn response string*/
            String response = (String) o;
            App.getInstance().customToast(response);
        }

    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
