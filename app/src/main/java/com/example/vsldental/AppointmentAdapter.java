package com.example.vsldental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public AppointmentAdapter(List<Appointment> appointments) {
        this.appointmentList = appointments;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.dateTextView.setText(appointment.getDate());
        holder.monthTextView.setText(appointment.getMonth());
        holder.serviceNameTextView.setText(appointment.getServiceName());
        holder.timeTextView.setText(appointment.getTime());

        if (appointment.isConfirmed()) {
            holder.statusIconImageView.setImageResource(R.drawable.img_completed);  // Your checkmark icon
        } else {
            holder.statusIconImageView.setImageResource(R.drawable.img_cancelled); // Your cross icon
        }
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView, monthTextView, serviceNameTextView, timeTextView;
        ImageView statusIconImageView;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.appointment_date);
            monthTextView = itemView.findViewById(R.id.appointment_month);
            serviceNameTextView = itemView.findViewById(R.id.service_name);
            timeTextView = itemView.findViewById(R.id.appointment_time);
            statusIconImageView = itemView.findViewById(R.id.status_icon);
        }
    }
}
