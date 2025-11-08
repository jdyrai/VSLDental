package com.example.vsldental;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageServices#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageServices extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SwitchMaterial ConsultationSwitch;
    private SwitchMaterial CleaningSwitch;
    private SwitchMaterial ToothExtractionSwitch;
    private SwitchMaterial DentalSwitch;
    private SwitchMaterial DentalBracesSwitch;

    public ManageServices() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageServices.
     */
    // TODO: Rename and change types and number of parameters


    public static ManageServices newInstance(String param1, String param2) {
        ManageServices fragment = new ManageServices();
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
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_manage_services, container, false);
        NavController navController = NavHostFragment.findNavController(this);




        // Initialize the switch from the inflated view
        ConsultationSwitch = view.findViewById(R.id.ConsultationSwitch);

        // Handle ON/OFF toggle
        ConsultationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getContext(), "Switch is ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Switch is OFF", Toast.LENGTH_SHORT).show();
            }
        });

        CleaningSwitch = view.findViewById(R.id.ConsultationSwitch);

        // Handle ON/OFF toggle
        CleaningSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getContext(), "Switch is ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Switch is OFF", Toast.LENGTH_SHORT).show();
            }
        });

        ToothExtractionSwitch = view.findViewById(R.id.ConsultationSwitch);

        // Handle ON/OFF toggle
        ToothExtractionSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getContext(), "Switch is ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Switch is OFF", Toast.LENGTH_SHORT).show();
            }
        });

        DentalSwitch = view.findViewById(R.id.ConsultationSwitch);

        // Handle ON/OFF toggle
        DentalSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getContext(), "Switch is ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Switch is OFF", Toast.LENGTH_SHORT).show();
            }
        });

        DentalBracesSwitch = view.findViewById(R.id.ConsultationSwitch);

        // Handle ON/OFF toggle
        DentalBracesSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getContext(), "Switch is ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Switch is OFF", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Log.d("BookingHistory", "Back button clicked!");
            navController.popBackStack();
        });

        return view;
    }
}