package com.android.timesheet.admin_operations.leave;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_overview.LeaveOverview;
import com.android.timesheet.admin_operations.leave.apply_leave.leave_request.LeaveRequestActivity;
import com.android.timesheet.admin_operations.leave.apply_on_duty.ApplyOnDuty;
import com.android.timesheet.admin_operations.leave.approve_leave.ApproveLeave;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.InternetUtils;
import com.android.timesheet.shared.widget.AutoFitGridLayoutManager;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

import static android.content.Context.WINDOW_SERVICE;

public class LeaveActivity extends BaseActivity<LeavePresenter> implements LeaveAdapter.ItemListener {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @BindView(R.id.gridView)
    RecyclerView gridView;
//    GridView gridView;

    User user = new User();

    private ArrayList<String> strings;
    private int width = 500;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_leave;
    }

    @Override
    protected String title() {
        return "Leave";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected LeavePresenter providePresenter() {
        return new LeavePresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = presenter().getCurrentUser();


        if (InternetUtils.isInternetConnected(this)) {
            InternetUtils.hideLoadingDialog();
        } else {
            InternetUtils.showLoadingDialog(this);
        }

        collapsingToolbarLayout.setTitleEnabled(false);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Get the application context
        Context context = getApplicationContext();
        width = getScreenWidthInDPs(context);
        Log.d("LeaveActivity", "Screen width : " + width + "dp");

        /**
         Simple GridLayoutManager that spans two columns
         **/
        AutoFitGridLayoutManager manager = new AutoFitGridLayoutManager(this, width );
        gridView.setLayoutManager(manager);

        loadGrid();

        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (user.getEmpRole().equalsIgnoreCase("Admin"))
                    switch (position) {
                        case 0:
                            presenter().openActivity(LeaveRequestActivity.class);
                            break;

                        case 1:
                            presenter().openActivity(ApproveLeave.class);
                            break;

                        case 2:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 3:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 4:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 5:
                            presenter().openActivity(LeaveOverview.class);
                            break;
                    }
                else
                    switch (position) {
                        case 0:
                            presenter().openActivity(LeaveRequestActivity.class);
                            break;

                        case 1:
                            presenter().openActivity(LeaveOverview.class);
                            break;

                        case 2:
                            presenter().openActivity(LeaveOverview.class);
                            break;
                    }
            }
        });*/

    }

    private void loadGrid() {

        strings = new ArrayList<>();
        if (user.getEmpRole().equalsIgnoreCase("Admin")) {
            strings.add("Apply Leave");//Leave Requests
            strings.add("Approve Leave");
            strings.add("Apply On Duty");
            strings.add("Approve On Duty");
//            strings.add("Apply Permission");
//            strings.add("Approve Permission");
//            strings.add("2");
//            strings.add("3");
//            strings.add("4");
//            strings.add("5");
//            strings.add("6");
//            strings.add("7");
//            strings.add("8");
        } else {
            strings.add("Apply Leave");//
            strings.add("Apply On Duty");
//            strings.add("Apply Permission");
        }

        LeaveAdapter booksAdapter = new LeaveAdapter(this, strings, this);
        gridView.setAdapter(booksAdapter);
    }

    @Override
    protected void onResume() {
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
    public void onItemClick(int position) {
        if (user.getEmpRole().equalsIgnoreCase("Admin"))
            switch (position) {
                case 0:
                    presenter().openActivity(LeaveRequestActivity.class);
                    break;

                case 1:
                    presenter().openActivity(ApproveLeave.class);
                    break;

                case 2:
                    presenter().openActivity(ApplyOnDuty.class);
                    break;

                case 3:
                    presenter().openActivity(LeaveOverview.class);
                    break;

                case 4:
                    presenter().openActivity(LeaveOverview.class);
                    break;

                case 5:
                    presenter().openActivity(LeaveOverview.class);
                    break;
            }
        else
            switch (position) {
                case 0:
                    presenter().openActivity(LeaveRequestActivity.class);
                    break;

                case 1:
                    presenter().openActivity(LeaveOverview.class);
                    break;

                case 2:
                    presenter().openActivity(LeaveOverview.class);
                    break;
            }
    }

    // Custom method to get screen width in dp/dip using Context object
    public static int getScreenWidthInDPs(Context context) {
        /*
            DisplayMetrics
                A structure describing general information about a display,
                such as its size, density, and font scaling.
        */
        DisplayMetrics dm = new DisplayMetrics();

        /*
            WindowManager
                The interface that apps use to talk to the window manager.
                Use Context.getSystemService(Context.WINDOW_SERVICE) to get one of these.
        */

        /*
            public abstract Object getSystemService (String name)
                Return the handle to a system-level service by name. The class of the returned
                object varies by the requested name. Currently available names are:

                WINDOW_SERVICE ("window")
                    The top-level window manager in which you can place custom windows.
                    The returned object is a WindowManager.
        */

        /*
            public abstract Display getDefaultDisplay ()

                Returns the Display upon which this WindowManager instance will create new windows.

                Returns
                The display that this window manager is managing.
        */

        /*
            public void getMetrics (DisplayMetrics outMetrics)
                Gets display metrics that describe the size and density of this display.
                The size is adjusted based on the current rotation of the display.

                Parameters
                outMetrics A DisplayMetrics object to receive the metrics.
        */
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);
        return widthInDP;
    }
}
