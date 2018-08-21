package com.android.timesheet.admin_operations.leave.apply_leave.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.apply_leave.tabs.popup.LeavePopUpActivity;
import com.android.timesheet.shared.widget.CircularProgressBar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyLeave extends Fragment implements View.OnClickListener {

    //@BindView(R.id.general_recycler_view)
    RecyclerView recyclerView;

    //@BindView(R.id.progressBar)
    CircularProgressBar progressBar;

    //@BindView(R.id.projectSV)
    SearchView projectSV;

    //@BindView(R.id.noDataFound)
    LinearLayout noDataFound;

    //@BindView(R.id.applyLeave)
    FloatingActionButton applyLeave;

    String TAG = "MyLeaveFragment";

    //Overridden method onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view ;

        view = inflater.inflate(R.layout.fragment_my_leave, container, false);

        if (view != null)
        {
            applyLeave = (FloatingActionButton) view.findViewById(R.id.applyLeave);
            Log.d(TAG, "View is not null");

            if (applyLeave != null) {
                applyLeave.setOnClickListener(this);
                Log.d(TAG, "mButton is not null");
            }
        }

        //Returning the layout file after inflating
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        if (view == applyLeave) {
            //do something
            Intent intent = new Intent(getActivity(), LeavePopUpActivity.class);
            startActivity(intent);
        }
    }
}
