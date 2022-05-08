package com.example.bai1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<Task> taskList;

    public TaskAdapter(MainActivity context, int layout, List<Task> taskList) {
        this.context = context;
        this.layout = layout;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTitle, txtDes;
        ImageView imgEdit, imgDelete;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.txtDes = (TextView) convertView.findViewById(R.id.tvDes);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgViewEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgViewDelete);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Task task = taskList.get(position);
        holder.txtTitle.setText(task.getTitle());
        holder.txtDes.setText(task.getDescription());

        //bắt sự kiện edit
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.EditTask(task.getTitle(),task.getDescription(),task.getIdTask());
            }
        });
        //bắt sự kiện delete
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DeleteTask(task.getTitle(),task.getDescription(),task.getIdTask());
            }
        });
        return convertView;
    }
}
