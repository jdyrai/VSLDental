package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MostRecentAppointmentDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MostRecentAppointmentDashboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MostRecentAppointmentDashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MostRecentAppointmentDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static MostRecentAppointmentDashboard newInstance(String param1, String param2) {
        MostRecentAppointmentDashboard fragment = new MostRecentAppointmentDashboard();
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
        View view =  inflater.inflate(R.layout.fragment_most_recent_appointment_dashboard, container, false);

        Bundle bundle = getArguments();

        String appDate = bundle.getString("appdate");
        String appTime = bundle.getString("apptime");
        String appStatus = bundle.getString("appstatus");
        String services = bundle.getString("services");

        TextView Urapp = view.findViewById(R.id.YourAppointmentText);
        Urapp.setText("Your Appointment for "+services);

        TextView datentime = view.findViewById(R.id.DateAndTime);
        datentime.setText(appTime);


        return view;

    }
}