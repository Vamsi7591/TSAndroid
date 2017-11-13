package com.android.timesheet.admin_operations.employee_master;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.Employee;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeMasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Context context;

    List<Employee> employeeList;

    OnItemClickListener listener;

    public EmployeeMasterAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.employeeList = Collections.emptyList();
    }

    public void setItems(List<Employee> employeeList) {
        if (employeeList == null) {
            employeeList = Collections.emptyList();
        }

        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    /*Common operations*/
    public void clear() {
        employeeList = Collections.emptyList();
        notifyDataSetChanged();
    }

    public Employee getItem(int position) {
        return employeeList.get(position);
    }

    private List<Employee> getEmployees() {
        return employeeList;
    }

    public void remove(int i) {
        employeeList.remove(i);
    }

    public Employee getChatMessageAt(int i) {
        return getEmployees().get(i);
    }
    /*End*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_employee_list_item, parent,
                false);
        return new EmployeeViewHolder(context, view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Employee employee = employeeList.get(position);

        ((EmployeeViewHolder) holder).bind(employee, position);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        Context context;
        OnItemClickListener listener;

        @BindView(R.id.employeeLL)
        LinearLayout employeeLL;

        @BindView(R.id.employee)
        TextView employeeTV;

        @BindView(R.id.code)
        TextView codeTV;

        @BindView(R.id.email)
        TextView emailTV;

        @BindView(R.id.trashed)
        LinearLayout trash;

        View itemView;

        EmployeeViewHolder(Context context, View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            this.context = context;
            this.listener = listener;
        }

        void bind(Employee employee, int position) {
            employeeTV.setText(employee.getEmpName());
            codeTV.setText(employee.getEmpCode());
            emailTV.setText(employee.getEmpEmailId());

            employeeLL.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
            } );

            trash.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClickToDelete(view, position);

                }

            });
        }
    }
}
