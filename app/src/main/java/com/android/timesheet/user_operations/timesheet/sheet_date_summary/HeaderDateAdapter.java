package com.android.timesheet.user_operations.timesheet.sheet_date_summary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.models.TimeSheet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Vijay on 20.07.2017
 */

public class HeaderDateAdapter extends ArrayAdapter<TimeSheet> {

    List<TimeSheet> dateInfoPojo;
    Context context;
    int resource;
    int lastPos = 0;
    String val = "";


    @SuppressLint("StringFormatInvalid")
    public HeaderDateAdapter(Context context, int resource, List<TimeSheet> dateInfoPojo) {
        super(context, resource, dateInfoPojo);
        this.dateInfoPojo = dateInfoPojo;
        this.context = context;
        this.resource = resource;


        //Add time dynamically
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date1 = null;
        long sum = 0;
        lastPos = dateInfoPojo.size();

        for (int i = 0; i < dateInfoPojo.size(); i++) {
            try {
                date1 = timeFormat.parse(dateInfoPojo.get(i).getTotalHours());
                sum = sum + date1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        String date3 = timeFormat.format(new Date(sum));
        Resources res = this.context.getResources();
        val = res.getString(R.string.total_hours, date3, dateInfoPojo.get(0).getDate());
    }


    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent,
                    false);
        }

        TimeSheet dateInfo = getItem(position);

        TextView txtname = (TextView) convertView.findViewById(R.id.projName);
        txtname.setText(dateInfo.getProjectName());

        TextView txttask = (TextView) convertView.findViewById(R.id.taskDesc);
        txttask.setText(dateInfo.getTaskDescription());

        TextView txtstart = (TextView) convertView.findViewById(R.id.startTime);
        txtstart.setText(dateInfo.getStartTime());

        TextView txtend = (TextView) convertView.findViewById(R.id.endTime);
        txtend.setText(dateInfo.getEndTime());

        TextView txttotal = (TextView) convertView.findViewById(R.id.totalHrs);
        txttotal.setText(dateInfo.getTotalHours());

        TextView totalHours = (TextView) convertView.findViewById(R.id.totalHours);
        totalHours.setVisibility(View.GONE);

        if ((lastPos - 1) == position) {
            totalHours.setVisibility(View.VISIBLE);
            totalHours.setText(val);
        }

        return convertView;
    }


}
