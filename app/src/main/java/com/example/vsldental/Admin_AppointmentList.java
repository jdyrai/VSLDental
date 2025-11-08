package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_AppointmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_AppointmentList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_AppointmentList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_AppointmentList.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_AppointmentList newInstance(String param1, String param2) {
        Admin_AppointmentList fragment = new Admin_AppointmentList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin__appointment_list, container, false);
        NavController navController = NavHostFragment.findNavController(this);


        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Log.d("BookingHistory", "Back button clicked!");
            navController.popBackStack();
        });

        LinearLayout RecordsBtn = view.findViewById(R.id.RecordsContainer);
        RecordsBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            navController.navigate(R.id.action_adminAppoint_to_adminRecords);
        });

        LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
        ProfileBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Profile clicked!");
            navController.navigate(R.id.action_adminAppoint_to_adminProfile);
        });

        LinearLayout HomeBtn = view.findViewById(R.id.HomeContainer);
        HomeBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Profile clicked!");
            navController.navigate(R.id.action_adminAppoint_to_adminDashboard);
        });

        return view;
    }
}