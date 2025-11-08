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
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_ClientDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_ClientDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_ClientDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_ClientDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_ClientDetails newInstance(String param1, String param2) {
        Admin_ClientDetails fragment = new Admin_ClientDetails();
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
        View view = inflater.inflate(R.layout.fragment_admin__client_details, container, false);
        NavController navController = NavHostFragment.findNavController(this);

        Bundle args = getArguments();

        String email = args.getString("email");
        String role = args.getString("role");


        TextView emailView = view.findViewById(R.id.Email);
        TextView roleView = view.findViewById(R.id.Date);

        emailView.setText(email);
        roleView.setText(role);
        // Inflate the layout for this fragment

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Log.d("BookingHistory", "Back button clicked!");
            navController.popBackStack();
        });

        return view;
    }
}