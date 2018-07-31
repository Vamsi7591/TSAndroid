package com.android.timesheet.common_operations.slider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.employee_master.list_employee.EmployeeMaster;
import com.android.timesheet.admin_operations.employee_project.EmployeeProject;
import com.android.timesheet.admin_operations.project_master.list_projects.ProjectMaster;
import com.android.timesheet.admin_operations.summary.SummaryDetails;
import com.android.timesheet.common_operations.landing.LandingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class SliderBaseAdapter extends BaseAdapter {

    Activity activity;
    List<Class> slide_classes = new ArrayList();



    public SliderBaseAdapter(Activity activity) {
        this.activity = activity;

        slide_classes.add(EmployeeMaster.class);//1
        slide_classes.add(ProjectMaster.class);//3
        slide_classes.add(EmployeeProject.class);//2
        slide_classes.add(SummaryDetails.class);//4
    }

    @Override
    public int getCount() {
        return activity.getResources().getStringArray(R.array.slider_list_items).length;
    }

    @Override
    public Object getItem(int position) {
        return activity.getResources().getStringArray(R.array.slider_list_items)[position];
    }

    public Drawable getItem_image(int position) {
        return activity.getResources().obtainTypedArray(R.array.slider_list_images).getDrawable(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        SliderViewHolder sliderViewholder;

        if (convertView == null) {
            v = activity.getLayoutInflater().inflate(R.layout.activity_drawer_layout_list_item, null, true);
            sliderViewholder = new SliderViewHolder();
            sliderViewholder.title = (TextView) v.findViewById(R.id.slider_inflator_textview);
            sliderViewholder.imageView = (ImageView) v.findViewById(R.id.slider_inflator_imagebutton);
            v.setTag(sliderViewholder);

        } else {
            sliderViewholder = (SliderViewHolder) v.getTag();
        }

        sliderViewholder.title.setText(getItem(position).toString());
        sliderViewholder.imageView.setImageDrawable(getItem_image(position));
//
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 12) {
                    ((LandingActivity) activity).closeDrawer();
                }

                else  {
                    Intent intent = new Intent(activity, slide_classes.get(position));
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
//                    ((LandingActivity) activity).closeDrawer();
                }


            }
        });

        return v;
    }

    private class SliderViewHolder {

        TextView title;
        ImageView imageView;

    }
}
