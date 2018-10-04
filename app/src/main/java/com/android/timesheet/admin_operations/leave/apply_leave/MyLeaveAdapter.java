package com.android.timesheet.admin_operations.leave.apply_leave;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.timesheet.R;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.widget.CustomFontTextView;
import com.daimajia.swipe.SwipeLayout;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsikonanki on 8/29/2017.
 */

public class MyLeaveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Context context;

    List<LeaveEntry> leaveEntryList;

    OnItemClickListener listener;
    SwipeLayout swipeLayout;

    public MyLeaveAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.leaveEntryList = Collections.emptyList();
    }

    public void setItems(List<LeaveEntry> leaveEntryList) {
        if (leaveEntryList == null) {
            leaveEntryList = Collections.emptyList();
        }

        this.leaveEntryList = leaveEntryList;
        notifyDataSetChanged();
    }

    /*Common operations*/
    public void clear() {
        leaveEntryList = Collections.emptyList();
        notifyDataSetChanged();
    }

    public LeaveEntry getItem(int position) {
        return leaveEntryList.get(position);
    }

    private List<LeaveEntry> getProjects() {
        return leaveEntryList;
    }

    public void remove(int i) {
        leaveEntryList.remove(i);
    }

    public LeaveEntry getProjectAt(int i) {
        return getProjects().get(i);
    }
    /*End*/


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_leave_item, parent,
                false);
        return new MyLeaveViewHolder(context, view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeaveEntry entry = leaveEntryList.get(position);

        ((MyLeaveViewHolder) holder).bind(entry, position);

    }

    @Override
    public int getItemCount() {
        return leaveEntryList.size();
    }

    static class MyLeaveViewHolder extends RecyclerView.ViewHolder {

        Context context;
        OnItemClickListener listener;

        @BindView(R.id.leaveTypeTV)
        CustomFontTextView leaveTypeTV;

        @BindView(R.id.fromAndToDateTV)
        CustomFontTextView fromAndToDateTV;

        @BindView(R.id.noOfDaysTV)
        CustomFontTextView noOfDaysTV;

        @BindView(R.id.daysTV)
        CustomFontTextView daysTV;

        @BindView(R.id.leaveTypeIV)
        ImageView leaveTypeIV;

        @BindView(R.id.trashLL)
        LinearLayout trashLL;

        @BindView(R.id.swipeSL)
        SwipeLayout swipeSL;

        View itemView;

        MyLeaveViewHolder(Context context, View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            this.context = context;
            this.listener = listener;
        }

        void bind(LeaveEntry entry, int position) {

            leaveTypeTV.setText(entry.getLeaveType());
            noOfDaysTV.setText(entry.getNoOfDays());
            fromAndToDateTV.setText(entry.getFromDate()+" - "+entry.getRemarks());
            daysTV.setText("Days");

            if(entry.getLeaveType().contains("Sick")){
                leaveTypeIV.setBackground(context.getResources().getDrawable(R.drawable.ic_sick));
            }else if(entry.getLeaveType().contains("Casual")){
                leaveTypeIV.setBackground(context.getResources().getDrawable(R.drawable.ic_casual));
            }else{
                leaveTypeIV.setBackground(context.getResources().getDrawable(R.drawable.ic_earned));
            }

            leaveTypeTV.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
            });

            trashLL.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClickToDelete(view, position);
                }
            });
        }
    }
}
