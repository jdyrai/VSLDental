package com.example.vsldental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecentAppointmentAdapter extends RecyclerView.Adapter<RecentAppointmentAdapter.ViewHolder> {

    private Context context;
    private List<RecentAppointments> recentLists;

    public RecentAppointmentAdapter(Context context, List<RecentAppointments> recentLists) {
        this.context = context;
        this.recentLists = recentLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecentAppointments recentAppointments = recentLists.get(position);

        holder.day.setText(recentAppointments.getDay());
        holder.month.setText(recentAppointments.getMonth());
        holder.serviceName.setText(recentAppointments.getServiceName());
        holder.time.setText(recentAppointments.getTime());
        holder.status.setText(recentAppointments.getStatus());

        switch (recentAppointments.getStatus()) {
            case "Completed":
                holder.status.setBackgroundResource(R.drawable.drawable_status_completed);
                break;
            case "Cancelled":
                holder.status.setBackgroundResource(R.drawable.drawable_status_cancelled);
                break;
            default:
                holder.status.setBackgroundResource(R.drawable.drawable_status_completed);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return recentLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView day, month, serviceName, time, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.tvDay);
            month = itemView.findViewById(R.id.tvMonth);
            serviceName = itemView.findViewById(R.id.tvServiceName);
            time = itemView.findViewById(R.id.tvTime);
            status = itemView.findViewById(R.id.tvStatus);
        }
    }
}
