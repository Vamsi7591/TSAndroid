package com.android.timesheet.user_operations.reports.monthly;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.Month;
import com.android.timesheet.shared.models.MonthParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class MonthlyFragment extends BaseFragment<MonthlyPresenter> implements BaseViewBehavior<List<Month>> {

    @BindView(R.id.spinner_Month)
    Spinner monthSpinner;

    @BindView(R.id.spinnerYear)
    Spinner yearSpinner;

    @BindView(R.id.lineChart)
    LineChart lineChart;

    @BindView(R.id.line_Arrow)
    ImageView loadLine;

    @BindView(R.id.noDataFoundRL)
    RelativeLayout noDataFound;

    String TAG = "MonthlyFragment";

    int cYear = 2011;
    int cMonth = 0;

    ArrayList<Integer> yearList = new ArrayList<Integer>();
    ArrayList<Integer> monthList = new ArrayList<Integer>();

    HashMap<String, List<Month>> month_retroHashMap;

    public MonthlyFragment() {
    }

    @Override
    protected MonthlyPresenter providePresenter() {
        return new MonthlyPresenter(getActivity(), this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_report_monthly;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lineChart.setTouchEnabled(true);
        CustomMarkerView customMarkerView = new CustomMarkerView(getContext(), R.layout.view_graph_marker);
        lineChart.setMarkerView(customMarkerView);
        lineChart.setDrawMarkerViews(true);

        Calendar calender = Calendar.getInstance();

        cYear = Calendar.getInstance().get(Calendar.YEAR);
        cMonth = (Calendar.getInstance().get(Calendar.MONTH));

        for (int count = 2017; count >= 2011; count--) {
            yearList.add(count);
        }

        for (int month = 1; month <= 12; month++) {
            monthList.add(month);
        }

        ArrayAdapter<Integer> monthAdapter = new ArrayAdapter<Integer>(this.getContext(), android.R.layout.simple_spinner_item, monthList);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);


        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<Integer>(this.getContext(), android.R.layout.simple_spinner_item, yearList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);


        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cMonth = Integer.parseInt((monthSpinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (cMonth > 0)
            monthSpinner.setSelection(cMonth - 1);
        else
            monthSpinner.setSelection(0);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cYear = Integer.parseInt(yearSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.v(TAG, "onRefresh called");
        User user = presenter().getCurrentUser();
        if (user != null) {
            MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
            presenter().fetchMonthData(monthParams);
        }


        loadLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = presenter().getCurrentUser();
                if (user != null) {
                    MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
                    presenter().fetchMonthData(monthParams);
                }

            }
        });

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(List<Month> dataLine) {

        if (dataLine.size() > 0) {

            lineChart.setVisibility(View.VISIBLE);
            noDataFound.setVisibility(View.GONE);
        } else {
            lineChart.setVisibility(View.GONE);
            noDataFound.setVisibility(View.VISIBLE);
        }


        if (dataLine.size() == 0)
            return;

        month_retroHashMap = new HashMap<>();

        for (int i = 0; i < dataLine.size(); i++) {

            ArrayList<Month> month_data = new ArrayList<>();
            month_data.add(dataLine.get(i));

            if (month_retroHashMap.containsKey(dataLine.get(i).getProjectname())) {

                month_retroHashMap.get(dataLine.get(i).getProjectname()).add(dataLine.get(i));
            } else {
                month_retroHashMap.put((dataLine.get(i).getProjectname()), month_data);
            }
        }


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        ArrayList<String> xValues = new ArrayList<String>();


        ArrayList<String> projNames = new ArrayList<String>(month_retroHashMap.keySet());

        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.BLUE);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.GREEN);
        colorList.add(Color.RED);
        colorList.add(Color.GRAY);
        colorList.add(Color.CYAN);

        for (int i = 0; i < month_retroHashMap.size(); i++) {

            ArrayList<Month> month_data = new ArrayList<>();
            month_data.addAll(month_retroHashMap.get(projNames.get(i)));

            ArrayList<Entry> yVals = new ArrayList<>();

            String projectName = "";
            for (int k = 0; k < month_data.size(); k++) {

                yVals.add(new Entry(Float.parseFloat(month_data.get(k).getDuration().replace(":", ".")), k));
                projectName = month_data.get(k).getProjectname();

                if (i == 0)
                    if (k == 2) {
                        xValues.add("Week:" + month_data.get(k).getWeekno());
                    } else
                        xValues.add("W:" +month_data.get(k).getWeekno());
            }

            LineDataSet set1;
            set1 = new LineDataSet(yVals, projectName);
            set1.setColor(colorList.get(i));
            set1.setCircleColor(colorList.get(i));
            set1.setLineWidth(2f);
            set1.setCircleRadius(6f);
            set1.setDrawCircleHole(true);
            set1.setHighLightColor(Color.WHITE);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFillColor(Color.LTGRAY);
            dataSets.add(set1); // add the data sets

        }

        lineChart.setDrawGridBackground(false);
        lineChart.getXAxis().setDrawGridLines(false); // disable grid lines for the XAxis
        lineChart.getAxisLeft().setDrawGridLines(false); // disable grid lines for the left YAxis
        lineChart.getAxisRight().setDrawGridLines(false); // disable grid lines for the right YAxis
        lineChart.getXAxis().setDrawAxisLine(false);// removeEmployee left side line
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getAxisLeft().setDrawLabels(false);
//        lineChart.getXAxis().setDrawLabels(false); // Top values hide

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);

        lineChart.setDoubleTapToZoomEnabled(true);
        lineChart.setPinchZoom(true);

        LineData data = new LineData(xValues, dataSets);
        lineChart.setData(data);
        lineChart.setDescription("");//Monthly report

        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        lineChart.animateXY(1000, 3000);
    }

    @Override
    public void onFailed(Throwable e) {

        lineChart.setVisibility(View.GONE);
        noDataFound.setVisibility(View.VISIBLE);

    }


}
