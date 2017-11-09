package com.android.timesheet.admin.project_master;

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
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.admin.project_master.addProject.AddProject;
import com.android.timesheet.admin.project_master.proj_serialize.Proj_MasterSerialize;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.RemoveProjectParams;
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

public class ProjectMaster extends BaseActivity<ProjectMasterPresenter>
        implements BaseViewBehavior<List<Project>>, OnItemClickListener, RecyclerView.OnItemTouchListener, Serializable {


    @BindView(R.id.empty_state_view)
    LinearLayout empty_state_view;

    @BindView(R.id.general_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

    List<Project> data;

    String TAG = "Project Master";

    ProjectMasterAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_project_master;
    }

    @Override
    protected int menuResID() {
        return R.menu.home_menu;
    }

    @Override
    protected String title() {
        return "Project Master";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected ProjectMasterPresenter providePresenter() {
        return new ProjectMasterPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();
        mAdapter = new ProjectMasterAdapter(this, this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);


        textViewToolbarTitle.setText(title());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_menu_home) {

            Intent addProj = new Intent(this, AddProject.class);
            startActivity(addProj);
        }
        presenter().getAllProjectNames();


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        presenter().getAllProjectNames();
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
    public void onSuccess(List<Project> data) {

        if (data.size() != 0) {
            this.data = data;

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

        Intent i = new Intent(this, Proj_MasterSerialize.class);
        Gson gson = new Gson();
        String personString = gson.toJson(data.get(position));
        i.putExtra("jsonObject", personString);

        startActivity(i);


    }

    public void onProjectsDeleted(int position) {
        mAdapter.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    public void removedEmployees(AllEmployeesResponse response) {
        if (response != null) {
            infoSnackBar(response.getMessage());
//            Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClickToDelete(View view, int position) {

        User user = presenter().getCurrentUser();
        RemoveProjectParams removeProjectParams = new RemoveProjectParams (data.get(position).getProjectName());;
        presenter().removeEmp(removeProjectParams);
        onProjectsDeleted(position);

    }

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
