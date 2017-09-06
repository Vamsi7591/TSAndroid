package com.android.timesheet.admin.employee_master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class EmployeeMaster extends BaseActivity<EmployeeMasterPresenter> implements BaseViewBehavior<List<Employee>>, OnItemClickListener {

    @BindView(R.id.empty_state_view)
    LinearLayout empty_state_view;

    @BindView(R.id.general_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

    String TAG = "EmployeeMaster";

    EmployeeMasterAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;


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

        mAdapter = new EmployeeMasterAdapter(this, this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);


        textViewToolbarTitle.setText(title());
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        textViewToolbarTitle.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

//        lp.setMargins(0, 0, 75, 0);
//        textViewToolbarTitle.setPadding(0, 0, 75, 0);
//        textViewToolbarTitle.setLayoutParams(lp);

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

        if (item.getItemId() == R.id.action_menu_home)
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

    @Override
    public void onSuccess(List<Employee> data) {

        if (data.size() != 0) {
            mAdapter.setItems(data);
            empty_state_view.setVisibility(View.GONE);
        } else
            empty_state_view.setVisibility(View.VISIBLE);

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
