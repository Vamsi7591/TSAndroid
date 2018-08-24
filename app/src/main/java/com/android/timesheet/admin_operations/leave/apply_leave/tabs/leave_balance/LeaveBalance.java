package com.android.timesheet.admin_operations.leave.apply_leave.tabs.leave_balance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.timesheet.R;
import com.android.timesheet.shared.widget.CircularProgressBar;

public class LeaveBalance extends Fragment {

    CircularProgressBar progressBar;
    View view;

    String TAG = "LeaveBalanceFragment";

    //Overridden method onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        View _view;

        _view = inflater.inflate(R.layout.fragment_leave_balance, container, false);

        if (view != null) {
            progressBar = (CircularProgressBar) _view.findViewById(R.id.progressBar);
            view = (View) _view.findViewById(R.id.view);
            Log.d(TAG, "View is not null");
        }


        //Returning the layout file after inflating
        return _view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class BalanceView extends FrameLayout {
        public BalanceView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            initView();
        }

        public BalanceView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public BalanceView(Context context) {
            super(context);
            initView();
        }

        private void initView() {
            View view = myView();
            addView(view);
        }

        public View myView(){
            View v; // Creating an instance for View Object
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.inflator_balance_view, null);

            return v;
        }

    }
}
