package com.android.timesheet.user_operations.reports.monthly;

import android.graphics.Color;
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
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

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

    int cYear = 2011;
    int cMonth = 0;

    List<Month> arraylist;
    ArrayList<Integer> yearList;
    ArrayList<Integer> monthList;

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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // load data here
            User user = presenter().getCurrentUser();
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

        arraylist = new ArrayList<>();
//        barChart.setTouchEnabled(true);
////        LineChartMarkerView lineChartMarkerView = new LineChartMarkerView(getContext(), R.layout.view_graph_marker);
////        barChart.setMarkerView(lineChartMarkerView);
//        barChart.setDrawMarkerViews(true);

        cYear = Calendar.getInstance().get(Calendar.YEAR);
        cMonth = (Calendar.getInstance().get(Calendar.MONTH));

        yearList = new ArrayList<>();
        for (int count = 2017; count >= 2011; count--) {
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

                if (arraylist.size() > 0) {

                    if (user != null) {
                        cMonth = Integer.parseInt(monthSpinner.getSelectedItem().toString());
                        MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
                        presenter().fetchMonthData(monthParams);

                    }

//                    loadPie(arraylist);
                    barChart.setVisibility(View.VISIBLE);
                    noDataFound.setVisibility(View.GONE);
                } else {
                    barChart.setVisibility(View.GONE);
                    noDataFound.setVisibility(View.VISIBLE);
                }

                if (user != null) {
                    MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
                    presenter().fetchMonthData(monthParams);
                }

            }
        });
//        User user = presenter().getCurrentUser();
//        if (user != null) {
//            MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
//            presenter().fetchMonthData(monthParams);
//        }

    }

    @Override
    public void onResume() {
        User user = presenter().getCurrentUser();
        if (user != null) {

            MonthParams monthParams = new MonthParams(user.empCode, cMonth, cYear);
            presenter().fetchMonthData(monthParams);
        }

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

        if (data.size() > 0) {

            barChart.setVisibility(View.VISIBLE);
            noDataFound.setVisibility(View.GONE);
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
        colorList.add(Color.BLACK);
        colorList.add(Color.CYAN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GRAY);
        colorList.add(Color.DKGRAY);


        for (int i = 0; i < data.size(); i++) {
            ArrayList<Month> month_data = new ArrayList<>();

            month_data.add(data.get(i));

            if (month_retroHashMap.containsKey(String.valueOf(data.get(i).getProjectname()))) {

                month_retroHashMap.get(String.valueOf(data.get(i).getProjectname())).add(data.get(i));
            } else {
                month_retroHashMap.put(String.valueOf((data.get(i).getProjectname())), month_data);
            }
        }

        // TreeMap to store values of HashMap
        TreeMap<String, List<Month>> sortedHashMap = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sortedHashMap.putAll(month_retroHashMap);

        // Display the TreeMap which is naturally sorted
        /*for (Map.Entry<String, List<Month>> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());*/

        month_retroHashMap.clear();

        BarData barData = new BarData();//xValues, dataSet
        ArrayList<String> projNames = new ArrayList<String>(sortedHashMap.keySet());

        List<Integer> projectColorLegend = new ArrayList<>();
        List<String> projectNamesLegend = new ArrayList<>();

        for(int v=0; v<projNames.size();v++){
            projectColorLegend.add(colorList.get(v));
            projectNamesLegend.add(projNames.get(v));
        }


        for (int i = 0; i < sortedHashMap.size(); i++) {//6

            ArrayList<Month> weekData = new ArrayList<>();
            weekData.addAll(sortedHashMap.get(projNames.get(i)));//5

            ArrayList<String> xValues = new ArrayList<>();
            ArrayList<BarEntry> yValues = new ArrayList<>();

            String projectName = "";
            for (int k = 0; k < weekData.size(); k++) {

                yValues.add(new BarEntry(Float.parseFloat(
                        weekData.get(k).getDuration().replace(":", ".")
                ), k, weekData.get(k))); // 44

                if(i<weekData.size())
                projectName = String.valueOf(weekData.get(i).getWeekno());
                else
                    projectName = "";
            }

            BarDataSet dataSet = new BarDataSet(yValues, projectName);//Projects

            dataSet.setColor(colorList.get(i));
            dataSet.setValueTextSize(0f);

            barData.addXValue(projectName);
            barData.addDataSet(dataSet);
        }

        barChart.setData(barData);
        barChart.setDrawValueAboveBar(true);
        barChart.setDescription("");//Monthly report
        barChart.setPinchZoom(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);

        //X-axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setSpaceBetweenLabels(5);
        xAxis.setValueFormatter(new XAxisValueFormatter() {
            @Override
            public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
                return original.matches("0.00") ? "0" : original;
            }
        });

        //Y-axis
        barChart.getAxisRight().setEnabled(false);
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(5f);
        leftAxis.setAxisMinValue(0f);
        //leftAxis.setValueFormatter(new DefaultYAxisValueFormatter());
        leftAxis.setValueFormatter(new YAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, YAxis yAxis) {

                return String.valueOf((int) value).matches("0.00") ? "0" : String.valueOf((int) value);
            }

        });
        Legend l = barChart.getLegend();
        l.setEnabled(true);
        l.resetCustom();
        l.setCustom(projectColorLegend, projectNamesLegend);
        l.setYOffset(0f);
        l.setXOffset(5f);
        l.setYEntrySpace(0f);
        l.setWordWrapEnabled(true);
        l.setTextSize(10f);

        /*barChart.setDrawGridBackground(false);
        barChart.getXAxis().setDrawGridLines(false); // disable grid lines for the XAxis
        barChart.getAxisLeft().setDrawGridLines(false); // disable grid lines for the left YAxis
        barChart.getAxisRight().setDrawGridLines(false); // disable grid lines for the right YAxis
        barChart.getXAxis().setDrawAxisLine(false);// removeEmployee left side line
        barChart.getXAxis().setDrawAxisLine(false);// removeEmployee left side line
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);*/

        barChart.notifyDataSetChanged();
        barChart.invalidate();
        barChart.animateXY(1000, 3000);


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
/* Line Chart Method  */

//void lineImplementation(){
//
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

    @Override
    public void onFailed(Throwable e) {
        barChart.setVisibility(View.GONE);
        noDataFound.setVisibility(View.VISIBLE);
    }

}
