package com.android.timesheet.admin_operations.employee_master.list_employee;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.common.AppConfig;
import com.android.timesheet.R;
import com.android.timesheet.admin_operations.employee_master.add_employee.AddEmployee;
import com.android.timesheet.admin_operations.employee_master.edit_employee.EditEmployee;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.RemoveEmployeeParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class EmployeeMaster extends BaseActivity<EmployeeMasterPresenter> implements
        BaseViewBehavior<Object>, OnItemClickListener, Serializable {

    @BindView(R.id.emptyStateLL)
    LinearLayout emptyStateLL;

    @BindView(R.id.general_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    String TAG = "Employee Master";

    EmployeeMasterAdapter employeeAdapter;
    LinearLayoutManager linearLayoutManager;

    List<Employee> employeesList;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_employee_master;
    }

    @Override
    protected int menuResID() {
        return R.menu.home_menu;
    }

    @Override
    protected String title() {
        return "Employee Master";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected EmployeeMasterPresenter providePresenter() {
        return new EmployeeMasterPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        employeesList = new ArrayList<Employee>();
        employeeAdapter = new EmployeeMasterAdapter(this, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(employeeAdapter);

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.aleo_regular)));

        if (mMenu == null) {
            showMenu();
        }
    }

    private void showMenu() {
        Menu menu = null;
        if (toolbar != null) {
            menu = toolbar.getMenu();
        }
        if (menu == null || menu.size() == 0) {
            if (toolbar != null) {
                toolbar.inflateMenu(R.menu.home_menu);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_menu_home) {
            Intent addEmployee = new Intent(this, AddEmployee.class);
            startActivity(addEmployee);
        }

        presenter().fetchEmployees();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuResID = menuResID();
        boolean hasOptionMenu = (menuResID > 0);

        if (hasOptionMenu) {
            getMenuInflater().inflate(menuResID, menu);
        }

        mMenu = menu;
        return hasOptionMenu || showBackButton();
    }

    @Override
    protected void onResume() {
        presenter().fetchEmployees();
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
    public void onFailed(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    /**
     * @param genericResponse - success result
     */
    @Override
    public void onSuccess(Object genericResponse) {

        User user = presenter().getCurrentUser();

        if (genericResponse instanceof AllEmployeesResponse) {

            AllEmployeesResponse allEmployeesResponse = (AllEmployeesResponse) genericResponse;
            List<Employee> employeeListResponse = allEmployeesResponse.getEmployeeList();

            if (employeeListResponse != null) {
                if (employeeListResponse.size() != 0) {
                    this.employeesList = employeeListResponse;
                    employeeAdapter.setItems(employeeListResponse);
                    emptyStateLL.setVisibility(View.GONE);
                } else
                    emptyStateLL.setVisibility(View.VISIBLE);

                /* Object wise operation on for loop - preferred way*/
                for (Employee employee : employeeListResponse) {
                    if (employee.getEmpCode().equals(user.empCode)) {
                        employeeListResponse.remove(employee);
                        break;
                    }
                }

                /*Index wise operations on for loop - not preferred
                for (int i = 0; i < employeesList.size(); i++) {
                    if (employeesList.get(i).getEmpCode().equals(user.empCode)) {
                        employeesList.removeEmployee(i);
                        break;
                    }
                }*/
            }
        } else if (genericResponse instanceof String) {
            String response = (String) genericResponse;
            if (response.contains("Success") && deletePosition != -1)
                onEmployeeDeleted(deletePosition);
        }
    }


    /**
     * @param position :-navigate to EditEmployee class when
     *                 employee selects list item
     */

    @Override
    public void onItemClick(View view, int position) {

        Employee employee = employeeAdapter.getItem(position);
        Gson gson = new Gson(); //Convert object to string using Gson()
        String employeeJson = gson.toJson(employee);//employeesList.get(position)

        Intent editEmployee = new Intent(this, EditEmployee.class);
        editEmployee.putExtra(AppConfig.EMPLOYEE_OBJECT, employeeJson);
        startActivity(editEmployee);
    }

    public void onEmployeeDeleted(int position) {
        employeeAdapter.removeEmployee(position);
    }

    /**
     * @param position :- position observes which item to delete in employee list
     * <p>
     * {@link EmployeeMasterPresenter}
     * Service call for removeEmployee employee
     * </p>
     */
    int deletePosition = -1;

    @Override
    public synchronized void onItemClickToDelete(View view, int position) {

        deletePosition = -1;
        User user = presenter().getCurrentUser();
        if (user != null) {
            RemoveEmployeeParams removeEmployeeParams = new RemoveEmployeeParams
                    (user.empCode, employeesList.get(position).getEmpCode());
            presenter().removeEmp(removeEmployeeParams);
            deletePosition = position;
        }
    }

    /**
     * @param msg :infoSnackBar is diplay when service or network error
     */
    public void infoSnackBar(String msg) {
        if (recyclerView != null) {
            Snackbar snack = Snackbar.make(recyclerView, msg, Snackbar.LENGTH_LONG);
            View view = snack.getView();
            TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            tv.setMaxLines(4);
            tv.setTextSize(18);
            snack.show();
        }
    }
}
