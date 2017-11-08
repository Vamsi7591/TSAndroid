package com.android.timesheet.user.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.TimeSheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    final Context context;

    private List<TimeSheet> timeSheetList, dup;

    OnItemClickListener listener;

    /*to store filter result*/
    private List<TimeSheet> items;

    TimeSheetAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.timeSheetList = Collections.emptyList();
        this.dup = Collections.emptyList();
        this.items = items;
    }

    public void clear() {
        timeSheetList = Collections.emptyList();
        dup = Collections.emptyList();
        notifyDataSetChanged();
    }

    void setItems(List<TimeSheet> timeSheets) {
        if (timeSheetList == null) {
            timeSheetList = Collections.emptyList();
            dup = Collections.emptyList();
        }

        timeSheetList = timeSheets;
        dup = timeSheets;
        notifyDataSetChanged();
    }

    TimeSheet getItem(int position) {
        return timeSheetList.get(position);
    }

    private List<TimeSheet> getTimeSheets() {
        return timeSheetList;
    }

    public void remove(int i) {
        timeSheetList.remove(i);
    }

    public TimeSheet getChatMessageAt(int i) {
        return getTimeSheets().get(i);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TimeSheet.TYPE_HEADER: {
                View view = LayoutInflater.from(context).inflate(R.layout.content_item_header, parent,
                        false);
                return new HeaderViewHolder(context, view, listener);
            }
            default: {
                View view = LayoutInflater.from(context).inflate(R.layout.content_time_sheet_item, parent, false);
                return new TimeSheetViewHolder(context, view, listener);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TimeSheet sheet = timeSheetList.get(position);

        switch (getItemViewType(position)) {
            case TimeSheet.TYPE_HEADER: {
                ((HeaderViewHolder) holder).bind(sheet.getHeader(), position);
                break;
            }
            case TimeSheet.TYPE_BODY: {
                ((TimeSheetViewHolder) holder).bind(sheet, position);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return timeSheetList.get(position).getRowType();
    }

    @Override
    public int getItemCount() {
        return timeSheetList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<TimeSheet> results = new ArrayList<TimeSheet>();
                try {
                    if (timeSheetList == null)
                        timeSheetList = items;
                    else {
                        timeSheetList = Collections.emptyList();
                        timeSheetList = dup;
                    }

                    if (constraint != null) {
                        if (timeSheetList != null & timeSheetList.size() > 0) {
                            for (final TimeSheet g : timeSheetList) {
                                if (g.getRowType() == 2)
                                    if (g.getProjectName().toLowerCase().contains(constraint.toString().toLowerCase()))
                                        results.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                } catch (Exception e) {
                    Log.v("TAG", e.toString());
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (constraint.toString().isEmpty()) {
                    timeSheetList = dup;
                } else {
                    items = (ArrayList<TimeSheet>) results.values;
                    timeSheetList = items;
                }
                notifyDataSetChanged();
            }
        };
    }

    static class TimeSheetViewHolder extends RecyclerView.ViewHolder {

        Context context;
        OnItemClickListener listener;

        @BindView(R.id.projectLL)
        LinearLayout projectLL;

        @BindView(R.id.project)
        TextView projectTV;

        @BindView(R.id.time)
        TextView timeTV;

        @BindView(R.id.description)
        TextView descriptionTV;

        @BindView(R.id.trash)
        LinearLayout trashLL;

        View itemView;

        TimeSheetViewHolder(Context context, View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            this.context = context;
            this.listener = listener;
        }

        void bind(TimeSheet sheet, int position) {

            projectTV.setText(sheet.getProjectName());
            timeTV.setText(sheet.getTotalHours());
            descriptionTV.setText(sheet.getTaskDescription());

            projectLL.setOnClickListener(view -> {
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
    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        Context context;
        OnItemClickListener listener;

        @BindView(R.id.header)
        TextView headerTextView;

        View itemView;

        public HeaderViewHolder(Context context, View view, OnItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.itemView = view;
            this.context = context;
            this.listener = listener;
        }

        public void bind(String headerName, int position) {
            headerTextView.setText(String.format("Date : %s", headerName));

            headerTextView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
            });
        }
    }

}
