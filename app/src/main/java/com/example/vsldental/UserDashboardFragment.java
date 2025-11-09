package com.example.vsldental;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class UserDashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecentAppointmentAdapter adapter;
    private List<RecentAppointments>  recentLists;
    public UserDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_dashboard, container, false);

        recyclerView = view.findViewById(R.id.recyclerAppointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recentLists = new ArrayList<>();
        recentLists.add(new RecentAppointments("21", "Jan", "Consultation", "09:30 AM - 10:00 AM", "Completed"));
        recentLists.add(new RecentAppointments("21", "Jan", "Extraction", "09:30 AM - 10:00 AM", "Cancelled"));
        recentLists.add(new RecentAppointments("21", "Jan", "Consultation", "09:30 AM - 10:00 AM", "Completed"));

        adapter = new RecentAppointmentAdapter(getContext(), recentLists);
        recyclerView.setAdapter(adapter);

        return view;
    }
}