package com.android.timesheet.common_operations.slider;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.common_operations.profile.admin_profile.MyProfile;
import com.android.timesheet.shared.models.User;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class Slider {

    public Slider(Activity context, User user) {

        ImageView slider_user_iv = context.findViewById(R.id.slider_user_iv);
        TextView slider_header_tv = context.findViewById(R.id.slider_header_tv);
        TextView slider_type_tv = context.findViewById(R.id.slider_type_tv);
        ListView slider_listView = context.findViewById(R.id.slider_listView);

        slider_header_tv.setText(String.format("Welcome %s", user.getEmpName()));

        if (user.getEmpRole().equalsIgnoreCase("Admin")) {
            slider_listView.setAdapter(new SliderBaseAdapter(context));
        } else if (user.getEmpRole().contains("User")) {

            context = null;

            slider_user_iv.setVisibility(View.GONE);
            slider_header_tv.setVisibility(View.GONE);
            slider_type_tv.setVisibility(View.GONE);
            slider_listView.setVisibility(View.GONE);

            slider_listView.setAdapter(null);

        }

        Activity finalContext = context;
        slider_user_iv.setOnClickListener(view -> {

            Intent intent = new Intent(finalContext, MyProfile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (finalContext != null) {
                finalContext.startActivity(intent);
                //                ((LandingActivity) finalContext).closeDrawer();
            }
        });
    }
}
