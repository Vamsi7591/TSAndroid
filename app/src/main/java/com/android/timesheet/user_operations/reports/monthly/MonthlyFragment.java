package com.android.timesheet.user_operations.reports.monthly;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.Month;
import com.android.timesheet.shared.models.MonthParams;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
    BarChart barChart;

    @BindView(R.id.line_Arrow)
    ImageView loadLine;

    @BindView(R.id.noDataFoundRL)
    LinearLayout noDataFound;

    String TAG = "MonthlyFragment";

    int cYear = 2018;
    int cMonth = 0;

    List<Month> listOfMonths;
    ArrayList<Integer> yearList;
    ArrayList<Integer> monthList;

    HashMap<String, List<Month>> month_retroHashMap, month_retroHashMap2;

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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // load data here
            User user = presenter().getCurrentUser();

            if (monthSpinner != null) {
                cMonth = Integer.parseInt(monthSpinner.getSelectedItem().toString());
            }else{
                cMonth = (Calendar.getInstance().get(Calendar.MONTH)) + 1;
                cYear = Calendar.getInstance().get(Calendar.YEAR);
            }
            MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
            presenter().fetchMonthData(monthParams);

        } else {
            // fragment is no longer visible

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(false);

        listOfMonths = new ArrayList<>();
//        barChart.setTouchEnabled(true);
////        LineChartMarkerView lineChartMarkerView = new LineChartMarkerView(getContext(), R.layout.view_graph_marker);
////        barChart.setMarkerView(lineChartMarkerView);
//        barChart.setDrawMarkerViews(true);

        cYear = Calendar.getInstance().get(Calendar.YEAR);
        cMonth = (Calendar.getInstance().get(Calendar.MONTH));

        yearList = new ArrayList<>();
        for (int count = 2018; count >= 2017; count--) {
            yearList.add(count);
        }

        monthList = new ArrayList<>();
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
            monthSpinner.setSelection(cMonth);//- 1
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


        loadLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = presenter().getCurrentUser();

                if (listOfMonths.size() > 0) {

                    if (user != null) {
                        cMonth = Integer.parseInt(monthSpinner.getSelectedItem().toString());
                        MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
                        presenter().fetchMonthData(monthParams);

                    }

//                    loadPie(listOfMonths);
                    barChart.setVisibility(View.VISIBLE);
                    noDataFound.setVisibility(View.GONE);
                } else {
                    barChart.setVisibility(View.GONE);
                    noDataFound.setVisibility(View.VISIBLE);
                }

                if (user != null) {
                    cMonth = Integer.parseInt(monthSpinner.getSelectedItem().toString());
                    MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
                    presenter().fetchMonthData(monthParams);
                }

            }
        });

        /*User user = presenter().getCurrentUser();
        if (user != null) {
            MonthParams monthParams = new MonthParams(user.empCode, (cMonth+1), cYear);
            presenter().fetchMonthData(monthParams);
        }*/

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(List<Month> data) {


        month_retroHashMap = new HashMap<>();
        month_retroHashMap2 = new HashMap<>();

        if (data.size() > 0) {

            barChart.setVisibility(View.VISIBLE);
            noDataFound.setVisibility(View.GONE);
            //lineChartImplementation(month_retroHashMap);
        } else {
            barChart.setVisibility(View.GONE);
            noDataFound.setVisibility(View.VISIBLE);
            return;
        }

        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.BLUE);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.GREEN);
        colorList.add(Color.RED);
        colorList.add(Color.CYAN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GRAY);
        colorList.add(Color.DKGRAY);


        for (int i = 0; i < data.size(); i++) {
            ArrayList<Month> month_data = new ArrayList<>();

            month_data.add(data.get(i));

            if (month_retroHashMap.containsKey(String.valueOf(data.get(i).getProjectname())))
                month_retroHashMap.get(String.valueOf(data.get(i).getProjectname())).add(data.get(i));
            else
                month_retroHashMap.put(String.valueOf((data.get(i).getProjectname())), month_data);
        }

        for (int i = 0; i < data.size(); i++) {
            ArrayList<Month> month_data = new ArrayList<>();

            month_data.add(data.get(i));

            if (month_retroHashMap2.containsKey(String.valueOf(data.get(i).getWeekno())))
                month_retroHashMap2.get(String.valueOf(data.get(i).getWeekno())).add(data.get(i));
            else
                month_retroHashMap2.put(String.valueOf((data.get(i).getWeekno())), month_data);
        }

        // TreeMap to store values of HashMap
        /*TreeMap<String, List<Month>> sortedHashMap = new TreeMap<>();
        TreeMap<String, List<Month>> sortedHashMap2 = new TreeMap<>();*/

        // Copy all data from hashMap into TreeMap
        TreeMap<String, List<Month>> sortedHashMap = new TreeMap<>(month_retroHashMap);
        TreeMap<String, List<Month>> sortedHashMap2 = new TreeMap<>(month_retroHashMap2);
        /*sortedHashMap.putAll(month_retroHashMap);
        sortedHashMap2.putAll(month_retroHashMap2);*/

        // Display the TreeMap which is naturally sorted
        /*for (Map.Entry<String, List<Month>> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());*/

        month_retroHashMap.clear();
        month_retroHashMap2.clear();

        BarData barData = new BarData();//xValues, dataSet
        ArrayList<String> projNames = new ArrayList<String>(sortedHashMap.keySet());
        ArrayList<String> weekNames = new ArrayList<String>(sortedHashMap2.keySet());


        int indexX = projNames.size(); //X project names
        int indexY = weekNames.size(); //Y week numbers
        boolean continueWithProjectsByWeeks = false;
        if (indexY < indexX) {
            continueWithProjectsByWeeks = true;
        }

        List<Integer> projectColorLegend = new ArrayList<>();
        List<String> projectNamesLegend = new ArrayList<>();

        for (int v = 0; v < projNames.size(); v++) {
            projectColorLegend.add(colorList.get(v));
            projectNamesLegend.add(projNames.get(v));
        }

        /*if(continueWithProjectsByWeeks) {
            for (int i = 0; i < sortedHashMap.size(); i++) {

                ArrayList<Month> weekData = new ArrayList<>();
                weekData.addAll(sortedHashMap.get(projNames.get(i)));

                String[] xValues = new String[weekData.size()];
                ArrayList<BarEntry> yValues = new ArrayList<>();

                String projectName = "";
                for (int k = 0; k < weekData.size(); k++) {

                    yValues.add(new BarEntry(Float.parseFloat(
                            weekData.get(k).getDuration().replace(":", ".")
                    ), k, weekData.get(k))); // 4-6

                    xValues[k] = (String.valueOf(weekData.get(k).getWeekno()));

                    if (i < weekData.size())
                        projectName = String.valueOf(weekData.get(i).getWeekno());
                    else
                        projectName = "";
                }

                BarDataSet dataSet = new BarDataSet(yValues, projectName);//Projects

                dataSet.setColor(colorList.get(i));
                dataSet.setValueTextSize(8f);
                dataSet.setDrawValues(true);
                dataSet.setStackLabels(xValues);

                if (!projectName.isEmpty())
                    barData.addXValue(projectName);

                if (sortedHashMap.size() == 1) {
                    barData = new BarData(xValues, dataSet);
                } else
                    barData.addDataSet(dataSet);
            }
        }else{
            for (int i = 0; i < sortedHashMap2.size(); i++) {

                ArrayList<Month> weekData = new ArrayList<>();
                weekData.addAll(sortedHashMap2.get(weekNames.get(i)));

                String[] xValues = new String[weekData.size()];
                ArrayList<BarEntry> yValues = new ArrayList<>();

                String projectName = "";
                for (int k = 0; k < weekData.size(); k++) {

                    yValues.add(new BarEntry(Float.parseFloat(
                            weekData.get(k).getDuration().replace(":", ".")
                    ), k, weekData.get(k))); // 4-6

                    xValues[k] = (String.valueOf(weekData.get(k).getWeekno()));

                    if (i < weekData.size())
                        projectName = String.valueOf(weekData.get(i).getProjectname());
                    else
                        projectName = "";
                }

                BarDataSet dataSet = new BarDataSet(yValues, projectName);//Projects

                dataSet.setColor(colorList.get(i));
                dataSet.setValueTextSize(8f);
                dataSet.setDrawValues(true);
                dataSet.setStackLabels(xValues);

                if (!projectName.isEmpty())
                    barData.addXValue(projectName);

                if (sortedHashMap2.size() == 1) {
                    barData = new BarData(xValues, dataSet);
                } else
                    barData.addDataSet(dataSet);
            }
        }*/

        /*Vertical grouped chart*/
        for (int i = 0; i < sortedHashMap2.size(); i++) {

            ArrayList<Month> weekData = new ArrayList<>();
            weekData.addAll(sortedHashMap2.get(weekNames.get(i)));

            String[] xValues = new String[weekData.size()];
            float[] yValuess = new float[weekData.size()];
            ArrayList<BarEntry> yValues = new ArrayList<>();

            String projectName = "";
            for (int k = 0; k < weekData.size(); k++) {

                /*yValues.add(new BarEntry(Float.parseFloat(
                        weekData.get(k).getDuration().replace(":", ".")
                ), k, weekData.get(k)));*/ // 4-6

                xValues[k] = (String.valueOf(weekData.get(k).getWeekno()));
                yValuess[k] = Float.parseFloat(weekData.get(k).getDuration().replace(":", "."));

//                if (i < weekData.size())
                projectName = String.valueOf(weekData.get(k).getWeekno());
//                else
//                    projectName = "";
            }

            yValues.add(new BarEntry(yValuess, i, weekNames.get(i)));

            BarDataSet dataSet = new BarDataSet(yValues, "");//Projects//projectName

            dataSet.setColors(colorList);
            dataSet.setValueTextSize(12f);
            dataSet.setDrawValues(true);
//            dataSet.setStackLabels(xValues);
            dataSet.setBarSpacePercent(0.0f);

            if (!projectName.isEmpty())
                barData.addXValue(projectName);

            /*if (sortedHashMap2.size() == 1) {
                barData = new BarData(xValues, dataSet);
            } else*/
            barData.addDataSet(dataSet);
        }

        barData.setGroupSpace(0.0f);
        barData.setValueFormatter(new MyValueFormatter());
        barChart.setData(barData);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            barChart.setTransitionGroup(false);
            /*It may remove the top x values*/
        }
        barChart.setDrawValueAboveBar(true);
        barChart.setDescription("");//Monthly report
        barChart.setPinchZoom(true);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawHighlightArrow(true);
        barChart.offsetTopAndBottom(5);

        //X-axis
        XAxis xAxis = barChart.getXAxis();
        //xAxis.setEnabled(false);
        //xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
//        xAxis.setAxisLineWidth(5);
        xAxis.setSpaceBetweenLabels(5);
        /*xAxis.setAxisMaxValue(10);
        xAxis.setAxisMinValue(0);*/

        //Y-axis
        barChart.getAxisRight().setEnabled(true);
        YAxis leftAxis = barChart.getAxisLeft();
        //leftAxis.setDrawGridLines(false);
        leftAxis.setInverted(false);
        leftAxis.setSpaceTop(5f);
        leftAxis.setAxisMinValue(0f);
        leftAxis.setZeroLineWidth(1);
        leftAxis.setDrawZeroLine(true);
        leftAxis.setMinWidth(25f);
        leftAxis.setMaxWidth(100f);
        leftAxis.setGranularity(1f);
        leftAxis.setLabelCount(indexY, true);


        Legend l = barChart.getLegend();
        l.setEnabled(true);
        l.resetCustom();
        l.setCustom(projectColorLegend, projectNamesLegend);
        l.setYOffset(5f);
        l.setXOffset(3f);
        l.setYEntrySpace(0f);
        l.setWordWrapEnabled(true);
        l.setTextSize(10f);

        /*barChart.setDrawGridBackground(false);
        barChart.getXAxis().setDrawGridLines(false); // disable grid lines for the XAxis
        barChart.getAxisLeft().setDrawGridLines(false); // disable grid lines for the left YAxis
        barChart.getAxisRight().setDrawGridLines(false); // disable grid lines for the right YAxis
        barChart.getXAxis().setDrawAxisLine(false);// removeEmployee left side line
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);*/

        barChart.getXAxis().setDrawGridLines(true); // disable grid lines for the XAxis
        barChart.getAxisLeft().setDrawGridLines(false); // disable grid lines for the left YAxis
        barChart.getAxisRight().setDrawGridLines(false); // disable grid lines for the right YAxis

        barChart.getAxisLeft().setAxisMinValue(0f);
        barChart.getAxisRight().setAxisMinValue(0f);
        barChart.setFitsSystemWindows(true);

        barChart.notifyDataSetChanged();
        barChart.invalidate();
        barChart.animateXY(1000, 3000);


        // 5 X 6 --> each 1 in 5 contains 6 records
        /*for (int i = 0; i < sorted.size(); i++) {

            ArrayList<Month> month_data = new ArrayList<>();
            month_data.addAll(sorted.get(projNames.get(i)));

            ArrayList<String> xValues = new ArrayList<>();
            ArrayList<BarEntry> yValues = new ArrayList<>();

            String projectName = "";
            for (int k = 0; k < month_data.size(); k++) {

                yValues.add(new BarEntry(Float.parseFloat(
                        month_data.get(k).getDuration().replace(":", ".")
                ), k, month_data.get(k)));


                if (i == 0) {
                    projectColorLegend.add(colorList.get(k));
                    projectNamesLegend.add(month_data.get(k).getProjectname());
                    //xValues.add("W:" + month_data.get(k).getWeekno());

                    projectName = String.valueOf(month_data.get(k).getWeekno());//"Week "+
                }else
                    projectName = String.valueOf(month_data.get(k).getWeekno());
            }


            BarDataSet dataSet = new BarDataSet(yValues, projectName);//Projects

            dataSet.setColors(projectColorLegend);
            dataSet.setValueTextSize(8f);

            barData.addXValue(projectName);
            barData.addDataSet(dataSet);
        }*/

    }

    @Override
    public void onFailed(Throwable e) {
        barChart.setVisibility(View.GONE);
        noDataFound.setVisibility(View.VISIBLE);
    }

    public class MyValueFormatter implements ValueFormatter {

        private DecimalFormat mFormat;

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            if (String.valueOf(value).matches("0:0") || String.valueOf(value).matches("0.0"))
                return "";//"0"
            else
                return String.valueOf(value);
        }
    }
/* Line Chart Method  */

//void lineChartImplementation(){

//    if (dataLine.size() > 0) {
//
//        barChart.setVisibility(View.VISIBLE);
//        noDataFound.setVisibility(View.GONE);
//    } else {
//        barChart.setVisibility(View.GONE);
//        noDataFound.setVisibility(View.VISIBLE);
//    }
//
//    month_retroHashMap = new HashMap<>();
//
//    for (int i = 0; i < dataLine.size(); i++) {
//
//        ArrayList<Month> month_data = new ArrayList<>();
//        month_data.add(dataLine.get(i));
//
//        if (month_retroHashMap.containsKey(dataLine.get(i).getProjectname())) {
//
//            month_retroHashMap.get(dataLine.get(i).getProjectname()).add(dataLine.get(i));
//        } else {
//            month_retroHashMap.put((dataLine.get(i).getProjectname()), month_data);
//        }
//    }
//
//
//    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//    ArrayList<String> xValues = new ArrayList<String>();
//
//
//    ArrayList<String> projNames = new ArrayList<String>(month_retroHashMap.keySet());
//
//    List<Integer> colorList = new ArrayList<>();
//    colorList.add(Color.BLUE);
//    colorList.add(Color.MAGENTA);
//    colorList.add(Color.GREEN);
//    colorList.add(Color.RED);
//    colorList.add(Color.GRAY);
//    colorList.add(Color.CYAN);
//
//    for (int i = 0; i < month_retroHashMap.size(); i++) {
//
//        ArrayList<Month> month_data = new ArrayList<>();
//        month_data.addAll(month_retroHashMap.get(projNames.get(i)));
//
//        ArrayList<Entry> yVals = new ArrayList<>();
//
//        String projectName = "";
//        for (int k = 0; k < month_data.size(); k++) {
//
//            yVals.add(new Entry(Float.parseFloat(month_data.get(k).getDuration().replace(":", ".")), k));
//            projectName = month_data.get(k).getProjectname();
//
//            if (i == 0)
//                    /*if (k == 2) {
//                        xValues.add("Week:" + month_data.get(k).getWeekno());
//                    } else*/
//                xValues.add("W:" + month_data.get(k).getWeekno());
//        }
//
//        LineDataSet set1;
//        set1 = new LineDataSet(yVals, projectName);
//        set1.setColor(colorList.get(i));
//        set1.setCircleColor(colorList.get(i));
//        set1.setLineWidth(2f);
//        set1.setCircleRadius(6f);
//        set1.setDrawCircleHole(true);
//        set1.setHighLightColor(Color.WHITE);
//        set1.setValueTextSize(9f);
//        set1.setDrawFilled(true);
//        set1.setFillColor(Color.LTGRAY);
//        dataSets.add(set1); // add the data sets
//
//    }
//
//    barChart.setDrawGridBackground(false);
//    barChart.getXAxis().setDrawGridLines(false); // disable grid lines for the XAxis
//    barChart.getAxisLeft().setDrawGridLines(false); // disable grid lines for the left YAxis
//    barChart.getAxisRight().setDrawGridLines(false); // disable grid lines for the right YAxis
//    barChart.getXAxis().setDrawAxisLine(false);// removeEmployee left side line
//    barChart.getAxisRight().setDrawLabels(false);
//    barChart.getAxisLeft().setDrawLabels(false);
////        barChart.getXAxis().setDrawLabels(false); // Top values hide
//
//    barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//    barChart.getAxisLeft().setEnabled(false);
//    barChart.getAxisRight().setEnabled(false);
//
//    barChart.setDoubleTapToZoomEnabled(true);
//    barChart.setPinchZoom(true);
//
//    BarDataSet dataSet = new BarDataSet(barEntries, "");//Projects
//    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//    dataSet.setValueTextSize(10f);
//    BarData barData = new BarData(labels, dataSet);
//
//    BarData data = new BarData(xValues, (IBarDataSet) dataSets);
//    barChart.setData(data);
//
////        LineData data = new LineData(xValues, dataSets);
////        barChart.setData(data);
//    barChart.setDescription("");//Monthly report
//
//    Legend l = barChart.getLegend();
//    l.setForm(Legend.LegendForm.LINE);
//
//    barChart.notifyDataSetChanged();
//    barChart.invalidate();
//    barChart.animateXY(1000, 3000);
//
//    }



}
