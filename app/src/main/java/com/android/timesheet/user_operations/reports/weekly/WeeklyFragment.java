package com.android.timesheet.user_operations.reports.weekly;

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
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.timesheet.R;
import com.android.timesheet.shared.fragments.BaseFragment;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.Week;
import com.android.timesheet.shared.models.WeekParams;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public class WeeklyFragment extends BaseFragment<WeeklyPresenter>
        implements BaseViewBehavior<List<Week>>, ValueFormatter {

    @BindView(R.id.spinner_week)
    Spinner weekSpinner;

    @BindView(R.id.year_Spinner)
    Spinner yearSpinner;

    @BindView(R.id.idPieChart)
    PieChart weekChart;

    @BindView(R.id.pieChart_Arrow)
    ImageView loadChart;

    @BindView(R.id.noDataFoundRL)
    LinearLayout noDataFound;


    int cWeek = 0;
    int cYear = 2018;

    List<Week> listOfWeeks;

    ArrayList<Integer> weekList;
    ArrayList<Integer> yearList;

    String TAG = "WeeklyFragment";

    @Override
    protected WeeklyPresenter providePresenter() {
        return new WeeklyPresenter(getActivity(), this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_report_weekly;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(false);

        listOfWeeks = new ArrayList<>();
        weekList = new ArrayList<>();
        yearList = new ArrayList<>();

//        Calendar calender = Calendar.getInstance();
//        cWeek = calender.get(Calendar.WEEK_OF_YEAR);
//        cYear = calender.get(Calendar.YEAR);

        Calendar calDe = Calendar.getInstance(Locale.getDefault());
        calDe.setTime(new Date());
        cWeek = calDe.get(Calendar.WEEK_OF_YEAR);
        cYear = calDe.get(Calendar.YEAR);


        for (int counter = 1; counter <= 52; counter++) {
            weekList.add(counter);

        }
        for (int count = 2018; count >= 2017; count--) {

            yearList.add(count);
        }

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_spinner_item, weekList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setAdapter(dataAdapter);

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, yearList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);


        weekSpinner.setOnItemSelectedListener
                (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cWeek = Integer.parseInt(weekSpinner.getSelectedItem().toString());
                        Log.v(TAG, "cWeek : " + cWeek);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cYear = Integer.parseInt(yearSpinner.getSelectedItem().toString());
                Log.v(TAG, "cYear : " + cYear);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (cWeek > 0)
            weekSpinner.setSelection(cWeek - 1);
        else
            weekSpinner.setSelection(0);

        Log.v(TAG, "onRefresh called");


        loadChart.setOnClickListener(view -> {

            if (listOfWeeks.size() > 0) {
                User user = presenter().getCurrentUser();
                if (user != null) {
                    WeekParams weekParams = new WeekParams(user.empCode, cWeek, cYear);
                    presenter().fetchWeekData(weekParams);
                }

                loadPie(listOfWeeks);
                weekChart.setVisibility(View.VISIBLE);
                noDataFound.setVisibility(View.GONE);
            } else {
                weekChart.setVisibility(View.GONE);
                noDataFound.setVisibility(View.VISIBLE);
            }


            User user = presenter().getCurrentUser();
            if (user != null) {
                WeekParams weekParams = new WeekParams(user.empCode, cWeek, cYear);
                presenter().fetchWeekData(weekParams);
            }

        });

//        User user = presenter().getCurrentUser();
//        if (user != null) {
//            WeekParams weekParams = new WeekParams(user.empCode, cWeek, cYear);
//            presenter().fetchWeekData(weekParams);
//        }
    }

    /* @Param Seperate method for add values in x and y axis
     * This method calls load chart , onResume , onSuccess
     */

    public void loadPie(List<Week> arrayList) {

        ArrayList<Entry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            yEntries.add(new Entry(Float.parseFloat(arrayList.get(i).getDuration()), i, arrayList.get(i)));

            /*if (Float.parseFloat(listOfWeeks.get(i).getDuration()) <= 1)
                xEntries.add("hour in \n"+ listOfWeeks.get(i).getProjectname().trim());//Hour
            else
                xEntries.add("hours in \n"+ listOfWeeks.get(i).getProjectname().trim());//Hours*/

            xEntries.add(arrayList.get(i).getProjectname().trim());
//            projectNames[i] = listOfWeeks.get(i).getProjectname();
        }


        PieDataSet dataSet = new PieDataSet(yEntries, "");//Week Report
        PieData data = new PieData(xEntries, dataSet);


//        data.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex,
//                                            ViewPortHandler viewPortHandler) {
//
//
//                return listOfWeeks.get(dataSetIndex).getDuration();
//
//            }
//        });


        weekChart.setData(data);
        weekChart.setDescription("");//This is Week Chart
        weekChart.setSoundEffectsEnabled(true);

        if (arrayList.size() > 1) {
            weekChart.setDrawHoleEnabled(false);
        } else {
            weekChart.setDrawHoleEnabled(true);
            weekChart.setTransparentCircleRadius(25f);
            weekChart.setHoleRadius(25f);
        }

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.DKGRAY);
        data.setHighlightEnabled(true);

        Legend legend = weekChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_CENTER);
        legend.setFormSize(10f);// set the size of the legend forms/shapes
        legend.setForm(Legend.LegendForm.CIRCLE);// set what type of form/shape should be used
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
//        legend.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
//        legend.setYEntrySpace(5f);
//        legend.setExtra(ColorTemplate.JOYFUL_COLORS, projectNames);// set custom labels and colors
        legend.setEnabled(false);
        weekChart.notifyDataSetChanged();

        weekChart.animateXY(1400, 1400);

    }

    @Override
    public void onResume() {

        User user = presenter().getCurrentUser();
        if (user != null) {
            loadPie(listOfWeeks);
            WeekParams weekParams = new WeekParams(user.empCode, cWeek, cYear);
            presenter().fetchWeekData(weekParams);
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
    public void onSuccess(List<Week> arrayList) {

        if (arrayList.size() > 0) {
            loadPie(arrayList);
            weekChart.setVisibility(View.VISIBLE);
            noDataFound.setVisibility(View.GONE);
        } else {
            weekChart.setVisibility(View.GONE);
            noDataFound.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onFailed(Throwable e) {
        weekChart.setVisibility(View.GONE);
        noDataFound.setVisibility(View.VISIBLE);
    }

    /* This default method for display values in decimal format */
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format((double) value);


    }
}
