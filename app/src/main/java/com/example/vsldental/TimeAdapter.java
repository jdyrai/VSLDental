package com.example.vsldental;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private List<String> timeList;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnTimeClickListener listener;

    public interface OnTimeClickListener {
        void onTimeClick(String time);
    }

    public TimeAdapter(List<String> timeList, OnTimeClickListener listener) {
        this.timeList = timeList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.timeText);
        }
    }

    @NonNull
    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String time = timeList.get(position);
        holder.timeText.setText(time);

        holder.timeText.setSelected(selectedPosition == position);

        holder.timeText.setOnClickListener(v -> {
            int currentPosition = holder.getBindingAdapterPosition();
            if (currentPosition == RecyclerView.NO_POSITION) return; // Safety check
            int previous = selectedPosition;
            selectedPosition = currentPosition;
            notifyItemChanged(previous);
            notifyItemChanged(selectedPosition);
            listener.onTimeClick(timeList.get(currentPosition));
        });
    }


    @Override
    public int getItemCount() {
        return timeList.size();
    }
}
