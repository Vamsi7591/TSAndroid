package com.android.timesheet.admin_operations.employee_master.list_employee;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.Employee;
import com.daimajia.swipe.SwipeLayout;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsikonanki on 8/28/2017.
 */

public class EmployeeMasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    private List<Employee> employeeList;

    private OnItemClickListener listener;

    EmployeeMasterAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.employeeList = Collections.emptyList();
    }

    void setItems(List<Employee> employeeList) {
        if (employeeList == null) {
            employeeList = Collections.emptyList();
        }

        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    /*Common operations*/
    public void clearEmployees() {
        employeeList = Collections.emptyList();
        notifyDataSetChanged();
    }

    public Employee getItem(int position) {
        return employeeList.get(position);
    }

    private List<Employee> getEmployees() {
        return employeeList;
    }

    public void removeEmployee(int i) {
        employeeList.remove(i);
        notifyDataSetChanged();
    }

    public Employee getEmployee(int i) {
        return getEmployees().get(i);
    }
    /*End*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_employee_list_item, parent,
                false);
        return new EmployeeViewHolder(context, view, listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        @BindView(R.id.swipe)
        SwipeLayout swipe;

        @BindView(R.id.employeeRole)
        ImageView employeeRole;

        View itemView;

        EmployeeViewHolder(Context context, View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            this.context = context;
            this.listener = listener;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void bind(Employee employee, int position) {
            employeeTV.setText(employee.getEmpName());
            codeTV.setText(employee.getEmpCode());
            emailTV.setText(employee.getEmpEmailId());

            if (employee.getEmpRole().equalsIgnoreCase("A")) {
                swipe.setBackground(context.getDrawable(R.drawable.bg_border_blue));
                employeeRole.setBackground(context.getDrawable(R.drawable.ic_admin));
            } else {
                swipe.setBackground(context.getDrawable(R.drawable.bg_border));
                employeeRole.setBackground(context.getDrawable(R.drawable.ic_user));
            }

            employeeLL.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
            });

            trash.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClickToDelete(view, position);
                }
            });
        }
    }
}
