package com.android.timesheet.user_operations.sheet_list.sheet_header;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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

public class DateInfoAdapter extends ArrayAdapter<TimeSheet> {

    List<TimeSheet> dateInfoPojo;
    Context context;
    int resource;
    int lastPos = 0;
    String val = "";


    @SuppressLint("StringFormatInvalid")
    public DateInfoAdapter(Context context, int resource, List<TimeSheet> dateInfoPojo) {
        super(context, resource, dateInfoPojo);
        this.dateInfoPojo = dateInfoPojo;
        this.context = context;
        this.resource = resource;


        //Add time dynamically

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
        val = res.getString(R.string.total_hours, date3);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_date_info, null, true);

        }

        TimeSheet DateInfoPojo = getItem(position);

        TextView txtname = (TextView) convertView.findViewById(R.id.projName);
        txtname.setText(DateInfoPojo.getProjectName());

        TextView txttask = (TextView) convertView.findViewById(R.id.taskDesc);
        txttask.setText(DateInfoPojo.getTaskDescription());

        TextView txtstart = (TextView) convertView.findViewById(R.id.startTime);
        txtstart.setText(DateInfoPojo.getStartTime());

        TextView txtend = (TextView) convertView.findViewById(R.id.endTime);
        txtend.setText(DateInfoPojo.getEndTime());

        TextView txttotal = (TextView) convertView.findViewById(R.id.totalHrs);
        txttotal.setText(DateInfoPojo.getTotalHours());

        TextView totalHours = (TextView) convertView.findViewById(R.id.totalHours);
        totalHours.setVisibility(View.GONE);

        if ((lastPos -1) == position) {
            totalHours.setVisibility(View.VISIBLE);
            totalHours.setText(val);
        }

        return convertView;
    }


}
