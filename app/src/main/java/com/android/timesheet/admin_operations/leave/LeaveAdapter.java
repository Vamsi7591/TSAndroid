package com.android.timesheet.admin_operations.leave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.ArrayList;

public class LeaveAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<String> strings;

    LeaveAdapter(Context context, ArrayList<String> strings) {
        this.mContext = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.leave_grid_inflator, null);
        }

        final CustomFontTextView textView = convertView.findViewById(R.id.textView);


        textView.setText(strings.get(i));


        return convertView;
    }
}
