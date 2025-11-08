package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_HomePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_HomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment Admin_HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_HomePage newInstance(String param1, String param2) {
        Admin_HomePage fragment = new Admin_HomePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    LineChart lineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__home_page, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        // Inflate the layout for this fragment

        LinearLayout containerLayout = view.findViewById(R.id.PendingContainer);

        new Thread(() -> {
            try {
                String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/admin_loadpendingappointments.php";
                String result = ConnectToDatabase.sendPostRequest(urlStr, ""); // no data sent

                requireActivity().runOnUiThread(() -> {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("status").equals("success")) {

                            JSONArray dataArray = json.getJSONArray("data");


                            // ✅ Loop through all items
                            for (int i = 0; i < 2; i++) {
                                JSONObject item = dataArray.getJSONObject(i);

                                String firstname = item.getString("patient_firstname");
                                String lastname = item.getString("patient_lastname");
                                String userid = item.getString("patient_email");
                                String contact = item.getString("patient_contact");
                                String email = item.getString("patient_email");


                                // 🔹 Create parent LinearLayout for this record
                                LinearLayout itemLayout = new LinearLayout(requireContext());
                                itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                                itemLayout.setPadding(50, 30, 50, 30);
                                itemLayout.setBackgroundResource(R.drawable.input_layout);
                                itemLayout.setClickable(true);
                                itemLayout.setFocusable(true);

                                // 🔹 Create TextView for email & role
                                TextView tv = new TextView(requireContext());
                                tv.setText("firstname: " + firstname + "\nRole: " + lastname);
                                tv.setTextSize(16f);
                                tv.setTextColor(getResources().getColor(android.R.color.black));
                                itemLayout.addView(tv);

                                // 🔹 Add margin between items
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                params.setMargins(0, 0, 0, 25);
                                itemLayout.setLayoutParams(params);

                                // 🔹 Add Click Listener
                                itemLayout.setOnClickListener(v -> {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("email", email);
                                    bundle.putString("accounts_id", userid);

                                    //navController.navigate(R.id.action_adminRecords_to_adminClientDetails, bundle);
                                    Toast.makeText(requireContext(),
                                            "Firstname: " + firstname + "\nLastname: " + lastname,
                                            Toast.LENGTH_SHORT).show();
                                });

                                // ✅ Add this record layout to the parent container
                                containerLayout.addView(itemLayout, 0);
                            }


                        } else {
                            Toast.makeText(requireContext(),
                                    "Failed: " + json.getString("message"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(requireContext(),
                                "Parse error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e) {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(requireContext(),
                                "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();









        LineChart lineChart = view.findViewById(R.id.lineChart);

        // Example data points (x = day, y = number of appointments)
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 10));  // Day 1 = 10 appointments
        entries.add(new Entry(2, 14));
        entries.add(new Entry(3, 8));
        entries.add(new Entry(4, 18));
        entries.add(new Entry(5, 12));

        LineDataSet dataSet = new LineDataSet(entries, "Appointments This Week");
        dataSet.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        dataSet.setCircleColor(getResources().getColor(android.R.color.holo_blue_dark));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setValueTextSize(10f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        // Optional styling
        lineChart.getDescription().setText("Daily Appointments");
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false); // hide right axis
        lineChart.animateY(1000);































        LinearLayout RecordsBtn = view.findViewById(R.id.RecordsContainer);
        RecordsBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            navController.navigate(R.id.action_adminDashboard_to_adminRecords);
        });

        LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
        ProfileBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Profile clicked!");
            navController.navigate(R.id.action_adminDashboard_to_adminProfile);
        });

        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Profile clicked!");
            navController.navigate(R.id.action_adminDashboard_to_adminAppoint);
        });

        LinearLayout SeeAllTexts = view.findViewById(R.id.SeeAllText);
        SeeAllTexts.setOnClickListener(v -> {
            Log.d("BookingHistory", "Profile clicked!");
            navController.navigate(R.id.action_adminDashboard_to_adminAppoint);
        });

        return view;
    }
}