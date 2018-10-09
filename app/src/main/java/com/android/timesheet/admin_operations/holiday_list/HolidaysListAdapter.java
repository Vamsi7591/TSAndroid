package com.android.timesheet.admin_operations.holiday_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.HolidayModel;
import com.android.timesheet.shared.models.TimeSheet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class HolidaysListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        Filterable {

    final Context context;

    private List<HolidayModel> timeSheetList, dup;

    OnItemClickListener listener;

    /*to store filter result*/
    private List<HolidayModel> items;

    HolidaysListAdapter(Context context, OnItemClickListener listener) {
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

    void setItems(List<HolidayModel> timeSheets) {
        if (timeSheetList == null) {
            timeSheetList = Collections.emptyList();
            dup = Collections.emptyList();
        }

        timeSheetList = timeSheets;
        dup = timeSheets;
        notifyDataSetChanged();
    }

    HolidayModel getItem(int position) {
        return timeSheetList.get(position);
    }

    private List<HolidayModel> getTimeSheets() {
        return timeSheetList;
    }

    public void remove(int i) {
        timeSheetList.remove(i);
    }

    public HolidayModel getChatMessageAt(int i) {
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
                View view = LayoutInflater.from(context).inflate(R.layout.content_holiday_item, parent, false);
                return new TimeSheetViewHolder(context, view, listener);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HolidayModel sheet = timeSheetList.get(position);

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
                List<HolidayModel> results = new ArrayList<HolidayModel>();
                HashMap<String, List<HolidayModel>> today_retroHashMap = new HashMap<>();


                for (int i = 0; i < results.size(); i++) {

                    ArrayList<HolidayModel> today_retroList = new ArrayList<>();
                    today_retroList.add(results.get(i));

                    if (today_retroHashMap.containsKey(results.get(i).getDateOfHoliday())) {
                        today_retroHashMap.get(results.get(i).getDateOfHoliday()).add(results.get(i));

                    } else

                    {
                        today_retroHashMap.put((results.get(i).getDateOfHoliday()), today_retroList);
                    }

                }

                results = new ArrayList<>();

                for (HashMap.Entry today : today_retroHashMap.entrySet()) {

                    List<HolidayModel> timeSheets = new ArrayList<>();
                    timeSheets = today_retroHashMap.get(today.getKey().toString());

                    for (int k = 0; k < timeSheets.size(); k++) {

                        timeSheets.get(k).setRowType(TimeSheet.TYPE_BODY);
                        results.add(timeSheets.get(k));
                    }

                    HolidayModel sheet = new HolidayModel(today.getKey().toString());
                    sheet.setDateOfHoliday(today.getKey().toString());
                    results.add(sheet);

                }

                Collections.sort(results, new StringDateComparator());
                Collections.reverse(results);


                try {
                    if (timeSheetList == null)
                        timeSheetList = items;
                    else {
                        timeSheetList = Collections.emptyList();
                        timeSheetList = dup;
                    }

                    if (constraint != null) {
                        boolean isDate = true;
                        if (timeSheetList != null & timeSheetList.size() > 0) {
                            for (final HolidayModel g : timeSheetList) {

                                if (g.getRowType() == 2) {
                                    isDate = false;
                                    if (g.getDateOfHoliday().contains(constraint.toString().toLowerCase()) ||
                                            g.getDateOfHoliday().toLowerCase().contains(constraint.toString().toLowerCase())) {
                                        results.add(g);
                                    }
                                } else if (g.getRowType() == 1) {
                                    if (g.getDateOfHoliday().contains(constraint.toString().toLowerCase()))
                                        results.add(g);
                                }

                            }
                        }

                        if (!isDate && results.size() > 0) {
                            // /* genetate header */
                            for (int i = 0; i < results.size(); i++) {
                                ArrayList<HolidayModel> today_retroList = new ArrayList<>();
                                today_retroList.add(results.get(i));

                                if (today_retroHashMap.containsKey(results.get(i).getDateOfHoliday())) {

                                    today_retroHashMap.put((results.get(i).getDateOfHoliday()), today_retroList);

                                    results.add(today_retroList.get(i));
                                }

//                                else
//                                {
//                                    today_retroHashMap.put((results.get(i).getDate()), today_retroList);
//                                }

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
                    items = (ArrayList<HolidayModel>) results.values;
                    timeSheetList = items;
                }
                notifyDataSetChanged();
            }
        };
    }

    static class StringDateComparator implements Comparator<HolidayModel> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        public int compare(HolidayModel lhs, HolidayModel rhs) {
            try {
                return dateFormat.parse(lhs.getDateOfHoliday()).compareTo(dateFormat.parse(rhs.getDateOfHoliday()));
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    static class TimeSheetViewHolder extends RecyclerView.ViewHolder {

        Context context;
        OnItemClickListener listener;

        @BindView(R.id.holidayLL)
        LinearLayout holidayLL;

        @BindView(R.id.holiday)
        TextView holidayTV;

        @BindView(R.id.date)
        TextView dateTV;

        @BindView(R.id.description)
        TextView descriptionTV;

        @BindView(R.id.holidayImage)
        ImageView holidayImage;

        View itemView;

        TimeSheetViewHolder(Context context, View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            this.context = context;
            this.listener = listener;
        }

        void bind(HolidayModel sheet, int position) {

            holidayTV.setText(sheet.getHoliday());
            dateTV.setText(sheet.getDateOfHoliday());
            descriptionTV.setText(sheet.getDescription());

            if (sheet.isFestival()) {
                holidayLL.setBackgroundColor(context.getResources().getColor(R.color.colorShine));
            } else {
                holidayLL.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }

            holidayLL.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(view, position);
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
//            headerTextView.setText(headerName);
            headerTextView.setText(String.format("%s  2018", headerName));

            headerTextView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
            });
        }
    }

}
