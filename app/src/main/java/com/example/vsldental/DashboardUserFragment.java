package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class DashboardUserFragment extends Fragment {

    public DashboardUserFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    AppointmentAdapter adapter;
    List<Appointment> appointments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dashboard_user, container, false);

        recyclerView = view.findViewById(R.id.appointments_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        appointments = new ArrayList<>();
        appointments.add(new Appointment("21", "Jan" ,"Consultation", "09:30 AM - 10:00 AM", true));
        appointments.add(new Appointment("21", "Jan", "Consultation", "09:30 AM - 10:00 AM", false));

        adapter = new AppointmentAdapter(appointments);
        recyclerView.setAdapter(adapter);

        return view;
    }
}