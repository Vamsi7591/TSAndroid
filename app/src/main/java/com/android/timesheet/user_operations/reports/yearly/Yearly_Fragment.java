package com.android.timesheet.user_operations.reports.yearly;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.summary.BarChartMarkerView;
import com.android.timesheet.app.App;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.Employee;
import com.android.timesheet.shared.models.Project;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.ProjectSum_Params;
import com.android.timesheet.shared.models.ProjectSum_Response;
import com.android.timesheet.shared.models.ProjectSummary;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class Yearly_Fragment extends BaseFragment<YearlyPresenter>
        implements BaseViewBehavior<Object>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.project_names)
    Spinner projName;

    @BindView(R.id.yearly_Spinner)
    Spinner yearSpinner;

    @BindView(R.id.idPieChartYearly)
    BarChart barChart;

    @BindView(R.id.pieChart_Load)
    ImageView loadBar;

    @BindView(R.id.noDataFoundRL)
    LinearLayout noDataFound;

    int cYear = 2018;
    List<ProjectSummary> listOfSummary;
    List<Employee> dataEmp;
    List<Project> dataProj;

    ArrayList<Integer> yearList;
    ArrayList<String> empNameList;
    ArrayList<String> projNamesList;

    @Override
    protected String title() {
        return "Yearly Report";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }


    @Override
    protected int layoutResID() {

        return R.layout.activity_yearly__fragment;
    }

    public Yearly_Fragment() {
    }

    @Override
    protected YearlyPresenter providePresenter() {
        return new YearlyPresenter(getActivity(), this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        projName.setOnItemSelectedListener(this);
        listOfSummary = new ArrayList<>();
        dataEmp = new ArrayList<>();
        dataProj = new ArrayList<>();
        projNamesList = new ArrayList<>();

        yearList = new ArrayList<>();
        empNameList = new ArrayList<>();

        barChart.setScaleEnabled(true);
        projName.setOnItemSelectedListener(this);

        User user = presenter().getCurrentUser();
        presenter().fetchEmployees();

        loadBar.setOnClickListener(v -> {
            if (!user.empCode.isEmpty() &&
                    !String.valueOf(projName.getSelectedItem()).isEmpty()
                    && !String.valueOf(cYear).isEmpty()) {

                ProjectSum_Params projectSum_params;

                projectSum_params = new ProjectSum_Params(user.getEmpCode(),
                        String.valueOf(projName.getSelectedItem()), String.valueOf(cYear));
                presenter().fetchSummaryData(projectSum_params);


            } else {
                barChart.setVisibility(View.GONE);
                noDataFound.setVisibility(View.VISIBLE);

            }
        });


        if (user != null) {
            presenter().fetchEmployees();
            presenter().getProjectNames(user.getEmpCode());
        }

        listOfSummary = new ArrayList<>();
        Calendar calender = Calendar.getInstance();

        cYear = calender.get(Calendar.YEAR);
        for (int count = 2018;
             count >= 2017; count--) {
            yearList.add(count);
        }

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_spinner_item, yearList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(dataAdapter);


        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cYear = Integer.parseInt(yearSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (!Objects.requireNonNull(user).empCode.isEmpty() && !String.valueOf(projName.getSelectedItem()).isEmpty() && !
                yearSpinner.getSelectedItem().toString().isEmpty()) {

            presenter().getProjectNames(user.getEmpCode());

            ProjectSum_Params projectSum_params = new ProjectSum_Params(user.empCode,
                    String.valueOf(projName.getSelectedItem()), String.valueOf(cYear));
            presenter().fetchSummaryData(projectSum_params);

        }

    }

    @Override
    public void onResume() {
        User user = presenter().getCurrentUser();
        if (user != null) {
            ProjectSum_Params projectSum_params = new ProjectSum_Params(user.getEmpCode(), String.valueOf(projName.getSelectedItem()), String.valueOf(cYear));
            presenter().fetchSummaryData(projectSum_params);
        } else {
            barChart.setVisibility(View.GONE);
            noDataFound.setVisibility(View.VISIBLE);
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

        if (o instanceof AllEmployeesResponse) {
            /*Employees response List<Employees>*/
            AllEmployeesResponse response = (AllEmployeesResponse) o;
            dataEmp = response.getEmployeeList();
            empNameList = new ArrayList<>();
            empNameList.add("Select");

            for (int i = 0; i < dataEmp.size(); i++) {
                empNameList.add(dataEmp.get(i).getEmpName().trim());
            }


//            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, empNameList);
////        employee_Name.setPrompt("Select Category");
//            employeeName.setAdapter(adapter);
        } else if (o instanceof ProjectNamesResponse) {

            /*Projects response List<Projects>*/
            ProjectNamesResponse projectNamesResponse = (ProjectNamesResponse) o;
            dataProj = projectNamesResponse.getProjectList();

            projNamesList = new ArrayList<>();
            projNamesList.add("Select");

            if (dataProj != null) {
                for (int i = 0; i < dataProj.size(); i++) {
                    projNamesList.add((dataProj.get(i).getProjectName().trim()));
                }

                barChart.setVisibility(View.VISIBLE);
//                noDataFound.setVisibility(View.GONE);

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()),
                        android.R.layout.simple_spinner_item, projNamesList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                projName.setAdapter(dataAdapter);


            }
        }


        if (o instanceof ProjectSum_Response) {
            ProjectSum_Response sumResponse = (ProjectSum_Response) o;
            if (sumResponse.isStatus()) {
                if (sumResponse.getProjectSummaries() != null) {
                    loadBarChart(sumResponse.getProjectSummaries());
                }

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
            App.getInstance().customToast(response);
        }
    }

    private void loadBarChart(List<ProjectSummary> data) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        if (data.size() > 0) {
            for (int k = 0; k < data.size(); k++) {
                barEntries.add(new BarEntry(Float.parseFloat(data.get(k).getDuration()), k, data.get(k)));
                labels.add(data.get(k).getMonth().substring(0, 3));
            }
        }

        barChart.setTouchEnabled(true);
        BarChartMarkerView barChartMarkerView = new BarChartMarkerView(getActivity(),
                R.layout.view_graph_marker);
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
        dataSet.setValueTextSize(10f);
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
