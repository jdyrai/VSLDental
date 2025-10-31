package com.example.vsldental;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayout;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ChooseDateTimeFragment extends Fragment {

    public ChooseDateTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_choose_date_time, container, false);

        FlexboxLayout flexTimeSlots = view.findViewById(R.id.flexTimeSlots);

        List<String> timeSlots = new ArrayList<>();
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(17, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        while (!start.isAfter(end)) {
            timeSlots.add(start.format(formatter));
            start = start.plusMinutes(30);
        }

        for (String time : timeSlots) {
            addTimeButton(flexTimeSlots, time);
        }

        return view;
    }

    private void addTimeButton(FlexboxLayout flexLayout, String timeText) {
        Button btn = new Button(getContext());
        btn.setText(timeText);
        btn.setAllCaps(false);
        btn.setBackgroundResource(R.drawable.time_slot);
        btn.setTextColor(Color.parseColor("#999999"));
        btn.setTextSize(14);
        btn.setPadding(40, 16, 40, 16);
        btn.setStateListAnimator(null);

        FlexboxLayout.LayoutParams params =
                new FlexboxLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 8, 8, 8);
        btn.setLayoutParams(params);

        btn.setOnClickListener(v -> {
            for (int i = 0; i < flexLayout.getChildCount(); i++) {
                flexLayout.getChildAt(i).setSelected(false);
            }
            v.setSelected(true);
        });

        flexLayout.addView(btn);
    }

}