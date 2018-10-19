package com.android.timesheet.admin_operations.leave;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.admin_operations.leave.approve_leave.ApproveLeaveAdapter;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.interfaces.OnItemLeaveActionsClickListener;
import com.android.timesheet.shared.models.LeaveEntry;
import com.android.timesheet.shared.widget.CustomFontTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private ArrayList<String> strings;
    private ItemListener mListener;


    LeaveAdapter(Context context, ArrayList<String> strings, ItemListener mListener) {
        this.mContext = context;
        this.strings = strings;
        this.mListener = mListener;

    }

    public void setItems(ArrayList<String> strings) {
        if (strings == null) {
            this.strings = new ArrayList<>();
        }

        this.strings = strings;
        notifyDataSetChanged();
    }

    /*Common operations*/
    public void clear() {
        this.strings = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.leave_grid_inflator, viewGroup, false);

        return new LeaveViewHolder(mContext, view, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((LeaveAdapter.LeaveViewHolder) viewHolder).bind(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class LeaveViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ItemListener itemListener;

        @BindView(R.id.textView)
        CustomFontTextView textView;

        View itemView;

        LeaveViewHolder(Context _context, View _itemView, ItemListener _listener) {

            super(_itemView);
            ButterKnife.bind(this, _itemView);
            itemView = _itemView;
            context = _context;
            itemListener = _listener;
        }

        public void bind(int pos) {
            textView.setText(strings.get(pos));

            textView.setOnClickListener(view -> {
                if (itemListener != null) {
                    itemListener.onItemClick(pos);
                }
            });
        }
    }

    /*@Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.leave_grid_inflator, null);
        }

        final CustomFontTextView textView = convertView.findViewById(R.id.textView);


        textView.setText(strings.get(i));


        return convertView;
    }*/
    public interface ItemListener {
        void onItemClick(int i);
    }

}
