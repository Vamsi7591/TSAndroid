package com.android.timesheet.admin_operations.employee_master;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.emp_serialize.EmpMaster_Serialize;
import com.android.timesheet.admin_operations.employee_master.addEmployee.AddEmployee;
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
        BaseViewBehavior<List<Employee>>, OnItemClickListener, RecyclerView.OnItemTouchListener, Serializable {

    @BindView(R.id.empty_state_view)
    LinearLayout empty_state_view;

    @BindView(R.id.general_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

    String TAG = "Employee Master";

    EmployeeMasterAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;

    List<Employee> data;

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

//        unbinder = ButterKnife.bind(this);
        data = new ArrayList<Employee>();

        mAdapter = new EmployeeMasterAdapter(this, this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

        textViewToolbarTitle.setText(title());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        if (mMenu == null) {
            showMenu();
        }

    }

    private void showMenu() {
        Menu menu = toolbar.getMenu();
        if (menu == null || menu.size() == 0) {
            toolbar.inflateMenu(R.menu.home_menu);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_menu_home) {
            Intent timesheet_Add = new Intent(this, AddEmployee.class);
            startActivity(timesheet_Add);
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
    public void onComplete() {

    }

/**
 * @param data

*
* */

    @Override
    public void onSuccess(List<Employee> data) {

        User user = presenter().getCurrentUser();

//        if (data instanceof AllEmployeesResponse) {
//            AllEmployeesResponse allEmployeesResponse = (AllEmployeesResponse) data;
//            empListResponse = allEmployeesResponse.getEmployeeList();
//
//        }

       if (data.size() != 0) {

           this.data = data;

            mAdapter.setItems(data);
            empty_state_view.setVisibility(View.GONE);
        } else
            empty_state_view.setVisibility(View.VISIBLE);

        int i = 0;
        for (Employee list : data) {

            if (list.getEmpCode().equals(user.empCode)) {
                data.remove(i);
                break;
            }
            i = i + 1;
        }

//        if (data instanceof String) {
//            String response = (String) data;
//            Toast.makeText(EmployeeMaster.this, response, Toast.LENGTH_LONG).show();
//        }

        }

    @Override
    public void onFailed(Throwable e) {

    }

    /**
    * @param position :-navigate to EmpMaster_Serialize class when employee list item is clicked
     * */

    @Override
    public void onItemClick(View view, int position) {

        Employee model = mAdapter.getItem(position);
        Intent i = new Intent(this, EmpMaster_Serialize.class);
        Gson gson = new Gson();
        String personString = gson.toJson(data.get(position));
        i.putExtra("jsonObject", personString);
        startActivity(i);

    }

    public void onEmployeeDeleted(int position) {
        mAdapter.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    public void removedEmployees(AllEmployeesResponse response) {
        if (response != null) {
            infoSnackBar(response.getMessage());

        }

    }
/**
* @param position :- {@link com.android.timesheet.shared.presenters.Presenter}
 *                 position observes which item to delete in employee list
 *
 *                {@link EmployeeMasterServices }
 *                Service call for remove employee
 */
    @Override
    public void onItemClickToDelete(View view, int position) {

//        TimeSheet sheet = mAdapter.getItem(position);
        User user = presenter().getCurrentUser();
        if (user != null) {
            RemoveEmployeeParams removeEmployeeParams = new RemoveEmployeeParams(user.empCode, data.get(position).getEmpCode());
            presenter().removeEmp(removeEmployeeParams);
            onEmployeeDeleted(position);

        }

    }

    /**
     @param msg :infoSnackBar is diplay when service or network error
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

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
