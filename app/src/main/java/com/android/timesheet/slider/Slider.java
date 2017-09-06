package com.android.timesheet.slider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.main.MainActivity;
import com.android.timesheet.profile.MyProfile;
import com.android.timesheet.shared.models.User;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class Slider {

    private Activity context;
    private ImageView slider_user_iv;
    private TextView slider_header_tv;
    private ListView slider_listView;

    public Slider(Activity context, User user) {
        this.context = context;
        slider_user_iv = (ImageView) context.findViewById(R.id.slider_user_iv);
        slider_header_tv = (TextView) context.findViewById(R.id.slider_header_tv);
        slider_listView = (ListView) context.findViewById(R.id.slider_listView);

        slider_header_tv.setText(String.format("Welcome %s", user.getEmpName()));

        slider_listView.setAdapter(new SliderBaseAdapter(context));

        slider_user_iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);

                ((MainActivity) context).closeDrawer();
            }
        });
    }
}
