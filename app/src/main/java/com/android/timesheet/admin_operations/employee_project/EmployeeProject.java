package com.android.timesheet.admin_operations.employee_project;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.timesheet.R;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class EmployeeProject extends BaseActivity<EmployeeProjectPresenter> implements
        BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.assignProject)
    Button assign;

    @BindView(R.id.removeProject)
    Button remove;

    @BindView(R.id.employe_Name)
    Spinner empName;

    @BindView(R.id.proj_Name)
    Spinner projName;

    @BindView(R.id.assignRealProject)
    Button realAssign;

    @BindView(R.id.removeRealProject)
    Button realRemove;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

    boolean isAssignVisible = true;
    ArrayAdapter adapter;
    List<String> empNameList;
    List<String> projNamesList;
    List<Employee> dataEmp;
    List<Project> dataProj;
    int selectedEmployeeNamePos = 0;

    private static final String TAG = "Employee_Project";

    @Override
    protected int layoutRestID() {
        return R.layout.activity_employee_project;
    }

//    @Override
//    protected int menuResID() {
//        return R.menu.home_menu;
//    }

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

        textViewToolbarTitle.setText(title());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        dataEmp = new ArrayList<>();
        dataProj = new ArrayList<>();
        empNameList = new ArrayList<String>();
        projNamesList = new ArrayList<String>();


        empName.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        projName.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        User user = presenter().getCurrentUser();
        if (user != null) {
            presenter().getAllEmpDetails();
        }

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAssignVisible = true;
                assign.setTextSize(20);
                assign.setTypeface(null, Typeface.BOLD);
                assign.setTextColor(getColor(R.color.white));

                remove.setTextSize(18);
                remove.setTypeface(null, Typeface.NORMAL);
                remove.setTextColor(getColor(R.color.colorWhite115));

                clearUI();

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAssignVisible = false;
                remove.setTextSize(20);
                remove.setTypeface(null, Typeface.BOLD);
                remove.setTextColor(getColor(R.color.white));

                assign.setTextSize(18);
                assign.setTypeface(null, Typeface.NORMAL);
                assign.setTextColor(getColor(R.color.colorWhite115));

                clearUI();
            }
        });

        realAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "AssignEmployeesToProject : " +
                        empName.getSelectedItem().toString()
                        + " : " + dataEmp.get(selectedEmployeeNamePos - 1).getEmpCode()
                        + " : " + projName.getSelectedItem().toString());

                // Service Call //
                User user = presenter().getCurrentUser();
                if (user != null) {
                    AssignEmpParams assignEmpParams = new AssignEmpParams(dataEmp.get(selectedEmployeeNamePos - 1).empCode, projName.getSelectedItem().toString());
                    presenter().assignEmp(assignEmpParams);
                }

                clearUI();
            }
        });

        realRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "RemoveEmployeesFromProject : " +
                        empName.getSelectedItem().toString()
                        + " : " + dataEmp.get(selectedEmployeeNamePos - 1).getEmpCode()
                        + " : " + projName.getSelectedItem().toString());

                // Service Calls //
                User user = presenter().getCurrentUser();
                if (user != null) {
                    AssignEmpParams assignEmpParams = new AssignEmpParams(dataEmp.get(selectedEmployeeNamePos - 1).empCode, projName.getSelectedItem().toString());
                    presenter().removeEmp(assignEmpParams);

                }

                clearUI();
            }
        });


        empName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                 /*If user selected spinner call projects service*/

                selectedEmployeeNamePos = empName.getSelectedItemPosition();

                if (isAssignVisible) {
                    // Assign
                    if (!empName.getSelectedItem().toString().isEmpty()) {

                        if (!empName.getSelectedItem().toString().equalsIgnoreCase("Select")) {

                            presenter().unAssignProj(dataEmp.get(selectedEmployeeNamePos - 1).empCode);
                            realAssign.setVisibility(View.VISIBLE);

                        } else {
                            projName.setAdapter(null);
                            clearUI();
                        }
                    }
                } else {
                    // Remove
                    if (!empName.getSelectedItem().toString().isEmpty()) {

                        if (!empName.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//                            getProjectsNames(empMasterData.get(selectedEmployeeNamePos - 1).getEmpcode());

                                presenter().getProjectNames(dataEmp.get(selectedEmployeeNamePos - 1).empCode);
//                                presenter().getProjectNames(user.empCode);
                            realRemove.setVisibility(View.VISIBLE);
                        }

                        else {
                            projName.setAdapter(null);
                            clearUI();
                        }
                    }

                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        projName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                int selectedPos = project_Name.getSelectedItemPosition();

                if (isAssignVisible) {
                    // Assign
                    if (!projName.getSelectedItem().toString().isEmpty()) {
                        if (!projName.getSelectedItem().toString().equalsIgnoreCase("Select"))
                            realAssign.setVisibility(View.VISIBLE);
                        else
                            realAssign.setVisibility(View.GONE);
                    }
                } else {
                    // Remove
                    if (!projName.getSelectedItem().toString().isEmpty()) {
                        if (!projName.getSelectedItem().toString().equalsIgnoreCase("Select"))
                            realRemove.setVisibility(View.VISIBLE);
                        else
                            realRemove.setVisibility(View.GONE);

                    }
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }




    public void clearUI() {

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, empNameList);
        empName.setAdapter(adapter);
        projName.setAdapter(null);

        realAssign.setVisibility(View.GONE);
        realRemove.setVisibility(View.GONE);

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
            dataProj = projectNamesResponse.getProjectList();

            projNamesList = new ArrayList<>();
            projNamesList.add("Select");

            if (dataProj != null) {
                for (int i = 0; i < dataProj.size(); i++) {
                    projNamesList.add(dataProj.get(i).getProjectName());
                }
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projNamesList);
                projName.setAdapter(adapter);
            }

        }

        else if (o instanceof AllEmployeesResponse) {
            /*Employees response List<Employees>*/
            AllEmployeesResponse response = (AllEmployeesResponse) o;
            dataEmp = response.getEmployeeList();


            empNameList = new ArrayList<>();
            empNameList.add("Select");

            for (int i = 0; i < dataEmp.size(); i++) {

                empNameList.add(dataEmp.get(i).getEmpName());
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, empNameList);
//        employee_Name.setPrompt("Select Category");
            empName.setAdapter(adapter);
        }

        else if (o instanceof String) {
            /*Assign or remove response string*/
            String response = (String) o;
            Toast.makeText(EmployeeProject.this, response, Toast.LENGTH_LONG).show();
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
