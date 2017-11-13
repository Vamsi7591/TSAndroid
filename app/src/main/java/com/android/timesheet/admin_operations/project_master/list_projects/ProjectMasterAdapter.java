package com.android.timesheet.admin_operations.project_master.list_projects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.timesheet.R;
import com.android.timesheet.shared.interfaces.OnItemClickListener;
import com.android.timesheet.shared.models.Project;
import com.daimajia.swipe.SwipeLayout;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamsikonanki on 8/29/2017.
 */

public class ProjectMasterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final Context context;

    List<Project> projectList;

    OnItemClickListener listener;
    SwipeLayout swipeLayout;

    public ProjectMasterAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.projectList = Collections.emptyList();
    }

    public void setItems(List<Project> projectList) {
        if (projectList == null) {
            projectList = Collections.emptyList();
        }

        this.projectList = projectList;
        notifyDataSetChanged();
    }

    /*Common operations*/
    public void clear() {
        projectList = Collections.emptyList();
        notifyDataSetChanged();
    }

    public Project getItem(int position) {
        return projectList.get(position);
    }

    private List<Project> getProjects() {
        return projectList;
    }

    public void remove(int i) {
        projectList.remove(i);
    }

    public Project getProjectAt(int i) {
        return getProjects().get(i);
    }
    /*End*/


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_project_list_item, parent,
                false);
        return new ProjectViewHolder(context, view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Project project = projectList.get(position);

        ((ProjectViewHolder) holder).bind(project, position);

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        Context context;
        OnItemClickListener listener;

        @BindView(R.id.project)
        TextView projectTV;

        @BindView(R.id.code)
        TextView codeTV;

        @BindView(R.id.trashing)
        LinearLayout trashin;

        View itemView;

        ProjectViewHolder(Context context, View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            this.context = context;
            this.listener = listener;
        }

        void bind(Project project, int position) {
            projectTV.setText(project.getProjectName());
            codeTV.setText(project.getProjectCode());

            projectTV.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
            });

            trashin.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClickToDelete(view, position);
                }
            });
        }
    }
}
