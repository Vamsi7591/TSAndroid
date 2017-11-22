package com.android.timesheet.admin_operations.summary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.timesheet.R;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.ProjectSum_Params;
import com.android.timesheet.shared.models.ProjectSum_Response;
import com.android.timesheet.shared.models.ProjectSummary;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.android.timesheet.user_operations.reports.monthly.LineChartMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * Created by vijay on 8/22/2017.
 */

public class SummaryDetails extends BaseActivity<SummaryDetailsPresenter>
        implements BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {


    @BindView(R.id.empName_spinner)
    Spinner employeeName;

    @BindView(R.id.ProjName_spinner)
    Spinner projName;

    @BindView(R.id.yearSpinSum)
    Spinner yearSpinner;

    @BindView(R.id.barChart)
    BarChart barChart;

    @BindView(R.id.loadBarChart)
    ImageView loadBar;

    @BindView(R.id.noDataFoundRL)
    LinearLayout noDataFound;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;

    int cYear = 2011;
    ArrayList<Integer> yearList = new ArrayList<Integer>();
    List<ProjectSummary> data;
    List<String> empNameList;
    ArrayAdapter adapter;
    List<String> projNamesList;
    List<Employee> dataEmp;
    List<Project> dataProj;

    int selectedEmployeeNamePos = 0;

    @Override
    protected String title() {
        return "Summary Details";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected int layoutRestID() {
        return R.layout.activity_summary_details;
    }

    @Override
    protected SummaryDetailsPresenter providePresenter() {
        return new SummaryDetailsPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        data = new ArrayList<>();
        dataEmp = new ArrayList<>();
        dataProj = new ArrayList<>();
        empNameList = new ArrayList<String>();
        projNamesList = new ArrayList<String>();

        employeeName.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        projName.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        User user = presenter().getCurrentUser();

        textViewToolbarTitle.setText(title());

        loadBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.empCode.isEmpty() &&
                        !String.valueOf(projName.getSelectedItem()).isEmpty()
                        && !String.valueOf(cYear).isEmpty()) {


                    ProjectSum_Params projectSum_params;
                    if (selectedEmployeeNamePos > 0) {
                        projectSum_params = new ProjectSum_Params(dataEmp.get(selectedEmployeeNamePos - 1).getEmpCode(), String.valueOf(projName.getSelectedItem()), String.valueOf(cYear));
                        presenter().fetchSummaryData(projectSum_params);
                    }
//
                } else {
                    barChart.setVisibility(View.GONE);
                    noDataFound.setVisibility(View.VISIBLE);

                }

            }
        });


        if (user != null) {
            presenter().fetchEmployees();
            presenter().getProjectNames(user.getEmpCode());
        }
        data = new ArrayList<>();
        Calendar calender = Calendar.getInstance();

        cYear = calender.get(Calendar.YEAR);
        for (int count = 2017;
             count >= 2011; count--) {

            yearList.add(count);
        }

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, yearList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cYear = Integer.parseInt(yearSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        employeeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                 /*If user selected spinner call projects service*/

                selectedEmployeeNamePos = employeeName.getSelectedItemPosition();

                if (!employeeName.getSelectedItem().toString().isEmpty()) {

                    if (!employeeName.getSelectedItem().toString().equalsIgnoreCase("Select")) {

                        if (user != null)
                            presenter().getProjectNames(user.getEmpCode());

                    } else {
                        projName.setAdapter(null);
                        clearUI();

                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (!user.empCode.isEmpty() && !String.valueOf(projName.getSelectedItem()).isEmpty() && !
                yearSpinner.getSelectedItem().toString().isEmpty()) {
            ProjectSum_Params projectSum_params = new ProjectSum_Params(user.empCode, String.valueOf(projName.getSelectedItem()), String.valueOf(cYear));
            presenter().fetchSummaryData(projectSum_params);
//
        } else {
            barChart.setVisibility(View.GONE);
            noDataFound.setVisibility(View.VISIBLE);

        }

    }

    public void clearUI() {

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, empNameList);

        employeeName.setAdapter(adapter);

        projName.setAdapter(null);


    }


    @Override
    protected void onResume() {
        User user = presenter().getCurrentUser();
        if (user != null) {
            ProjectSum_Params projectSum_params = new ProjectSum_Params(user.getEmpCode(), String.valueOf(projName.getSelectedItem()), String.valueOf(cYear));
            presenter().fetchSummaryData(projectSum_params);
        }

        presenter().fetchEmployees();
        super.onResume();
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
                    projNamesList.add(dataProj.get(i).getProjectName().trim());
                }

                barChart.setVisibility(View.VISIBLE);
                noDataFound.setVisibility(View.GONE);

                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projNamesList);
                projName.setAdapter(adapter);
            }

        } else if (o instanceof AllEmployeesResponse) {
            /*Employees response List<Employees>*/
            AllEmployeesResponse response = (AllEmployeesResponse) o;
            dataEmp = response.getEmployeeList();
            empNameList = new ArrayList<>();
            empNameList.add("Select");

            for (int i = 0; i < dataEmp.size(); i++) {
                empNameList.add(dataEmp.get(i).getEmpName().trim());
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, empNameList);
//        employee_Name.setPrompt("Select Category");
            employeeName.setAdapter(adapter);
        }

        if (o instanceof ProjectSum_Response) {
            ProjectSum_Response sumResponse = (ProjectSum_Response) o;
            if (sumResponse.isStatus()) {
                loadBarChart(sumResponse.getProjectSummaries());

                barChart.setVisibility(View.VISIBLE);
                noDataFound.setVisibility(View.GONE);
            }
        } else {
            barChart.setVisibility(View.GONE);
            noDataFound.setVisibility(View.VISIBLE);
        }

        if (o instanceof String) {
            /*Assign or removeEmployee response string*/
            String response = (String) o;
            Toast.makeText(SummaryDetails.this, response, Toast.LENGTH_LONG).show();
        }

    }


    private void loadBarChart(List<ProjectSummary> data) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        if (data.size() > 0) {
            for (int k = 0; k < data.size(); k++) {
                barEntries.add(new BarEntry(Float.parseFloat(data.get(k).getDuration()), k));
                labels.add(data.get(k).getMonth().substring(0, 3));
            }
        }

        barChart.setTouchEnabled(true);
        BarChartMarkerView barChartMarkerView = new BarChartMarkerView(this, R.layout.view_graph_marker);
        barChart.setMarkerView(barChartMarkerView);
        barChart.setDrawMarkerViews(true);

        barChart.setDrawGridBackground(false);
        barChart.getXAxis().setDrawGridLines(false); // disable grid lines for the XAxis
        barChart.getAxisLeft().setDrawGridLines(false); // disable grid lines for the left YAxis
        barChart.getAxisRight().setDrawGridLines(false); // disable grid lines for the right YAxis
        barChart.getXAxis().setDrawAxisLine(false);// removeEmployee left side line
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);
//        barChart.getXAxis().setDrawLabels(false); // Top values hide

        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);

        barChart.setDoubleTapToZoomEnabled(true);
        barChart.setPinchZoom(true);

        BarDataSet dataSet = new BarDataSet(barEntries, "");//Projects
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(labels, dataSet);

        barChart.setData(barData);
        barChart.setDescription(""); //Summary Details
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();
        barChart.notifyDataSetChanged();
        barChart.animateXY(2000, 2000);
    }


    @Override
    public void onFailed(Throwable e) {

        barChart.setVisibility(View.GONE);
        noDataFound.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
