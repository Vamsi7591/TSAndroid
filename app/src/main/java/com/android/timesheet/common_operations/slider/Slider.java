package com.android.timesheet.common_operations.slider;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.common_operations.landing.LandingActivity;
import com.android.timesheet.common_operations.profile.MyProfile;
import com.android.timesheet.shared.models.User;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class Slider {

    private Activity context;
    private ImageView slider_user_iv;
    private TextView slider_header_tv,slider_type_tv;
    private ListView slider_listView;

    public Slider(Activity context, User user) {
        this.context = context;
        slider_user_iv = (ImageView) context.findViewById(R.id.slider_user_iv);
        slider_header_tv = (TextView) context.findViewById(R.id.slider_header_tv);
        slider_type_tv = (TextView) context.findViewById(R.id.slider_type_tv);
        slider_listView = (ListView) context.findViewById(R.id.slider_listView);

        slider_header_tv.setText(String.format("Welcome %s", user.getEmpName()));

        if (user.getEmpRole().equalsIgnoreCase("Admin")) {
            slider_listView.setAdapter(new SliderBaseAdapter(context));
        }else
            slider_type_tv.setText("Logged In as User");

        slider_user_iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MyProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
//                ((LandingActivity) context).closeDrawer();
            }
        });
    }
}
