package com.example.vsldental;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ChooseDateTimeFragment extends Fragment {

    private RecyclerView timeRecycler;
    private String selectedDate = null;
    private String selectedTime = null;

    public ChooseDateTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_choose_date_time, container, false);
        if (getArguments() != null) {

            timeRecycler = view.findViewById(R.id.timeRecycler);

            MaterialCalendarView calendarView = view.findViewById(R.id.calendarView);

            calendarView.setOnDateChangedListener((widget, date, selected) -> {
                // date is a CalendarDay object
                int year = date.getYear();
                int month = date.getMonth(); // 1–12
                int day = date.getDay();

                selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);
                Log.d("SelectedDate", "User picked date: " + selectedDate);
            });


            List<String> timeSlots = generateTimeSlots();

            TimeAdapter adapter = new TimeAdapter(timeSlots, time -> {
                // You can handle the selected time here
                System.out.println("Selected time: " + time);
                selectedTime = time;
                Log.d("ReceivedServices", "User selected: " + time);
            });

            timeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
            timeRecycler.setAdapter(adapter);

            MaterialButton btnNext = view.findViewById(R.id.btnNext);
            btnNext.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController(view);

                Bundle bundle = new Bundle(getArguments());
                ArrayList<String> selectedServices = getArguments().getStringArrayList("selectedServices");
                String fullName =getArguments().getString("fullName");
                String patientID =getArguments().getString("patientID");
                bundle.putString("patientID", patientID);
                bundle.putString("fullName", fullName);
                bundle.putString("selectedDate", selectedDate);
                bundle.putString("selectedTime", selectedTime);
                bundle.putStringArrayList("selectedServices", selectedServices);

                navController.navigate(R.id.action_ChooseDateTime_to_AppointSummary, bundle);
            });


        }

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