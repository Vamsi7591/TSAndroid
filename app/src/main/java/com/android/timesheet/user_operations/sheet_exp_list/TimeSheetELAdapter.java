package com.android.timesheet.user_operations.sheet_exp_list;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.TimeSheet;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsi on 9/11/2017.
 */

public class TimeSheetELAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    OnItemClickListener listener;

    public TimeSheetELAdapter(Context context, List<String> expandableListTitle,
                              HashMap<String, List<String>> expandableListDetail, OnItemClickListener listener) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.listener = listener;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final TimeSheet sheet = (TimeSheet) getChild(listPosition, expandedListPosition);
       /* if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.content_time_sheet_item, null);
        }*/

//        new TimeSheetViewHolder(this.context, convertView, listener).bind(sheet,expandedListPosition);

        /*@BindView(R.id.projectLL)
        LinearLayout projectLL= (TextView) convertView
                .findViewById(R.id.expandedListItem);

        @BindView(R.id.project)
        TextView projectTV= (TextView) convertView
                .findViewById(R.id.expandedListItem);

        @BindView(R.id.time)
        TextView timeTV= (TextView) convertView
                .findViewById(R.id.expandedListItem);

        @BindView(R.id.description)
        TextView descriptionTV= (TextView) convertView
                .findViewById(R.id.expandedListItem);

        @BindView(R.id.trash)
        LinearLayout trashLL= (TextView) convertView
                .findViewById(R.id.expandedListItem);*/

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


    static class TimeSheetViewHolder {

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
}