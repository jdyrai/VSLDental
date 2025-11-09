package com.example.vsldental;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayout;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ChooseDateTimeFragment extends Fragment {

    private RecyclerView timeRecycler;

    public ChooseDateTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_choose_date_time, container, false);

        timeRecycler = view.findViewById(R.id.timeRecycler);

        List<String> timeSlots = generateTimeSlots();

        TimeAdapter adapter = new TimeAdapter(timeSlots, time ->
                // You can handle the selected time here
                System.out.println("Selected time: " + time)
        );

        timeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        timeRecycler.setAdapter(adapter);

        return view;
    }

    private List<String> generateTimeSlots() {
        List<String> times = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 30);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 18);
        end.set(Calendar.MINUTE, 0);

        while (cal.before(end) || cal.equals(end)) {
            times.add(sdf.format(cal.getTime()));
            cal.add(Calendar.MINUTE, 30);
        }
        return times;
    }
}