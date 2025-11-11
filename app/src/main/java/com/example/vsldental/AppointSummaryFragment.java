package com.example.vsldental;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AppointSummaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_appoint_summary, container, false);
        String userId = SessionManager.getUserId(requireContext());

        if (getArguments() != null) {
            ArrayList<String> selectedServices = getArguments().getStringArrayList("selectedServices");
            String patientID =getArguments().getString("patientID");
            String selectedDate = getArguments().getString("selectedDate");
            String selectedTime = getArguments().getString("selectedTime");
            Log.d("ReceivedServices", "selectedDate: " + selectedDate + "selectedTime: " + selectedTime + "selectedServices: " + selectedServices);
            String fullName = getArguments().getString("fullName");

            TextView customerName = view.findViewById(R.id.CustomerName);
            //TextView estimatedCost = view.findViewById(R.id.EstimatedCost);
            TextView appointmentDate = view.findViewById(R.id.AppointmentDate);
            TextView appointmentTime = view.findViewById(R.id.AppointmentTime);
            TextView service = view.findViewById(R.id.Service);
            customerName.setText(fullName);
            //estimatedCost.setText();
            appointmentDate.setText(selectedDate);
            appointmentTime.setText(selectedTime);
            service.setText(TextUtils.join(", ", selectedServices));

            String appDate = appointmentDate.getText().toString();//

            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            Date time = null;
            try {
                time = sdf12.parse(selectedTime);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String formattedTime = sdf24.format(time); // "15:00:00"

// Combine date + time for TIMESTAMP
            String timestamp = selectedDate + " " + formattedTime;

            MaterialButton btnNext = view.findViewById(R.id.btnNext);
            btnNext.setOnClickListener(v -> {

                new Thread(() -> {
                    Log.d("ReceivedServices", "clickked:!!! selectedDate: " + selectedDate + "selectedTime: " + selectedTime + "selectedServices: " + selectedServices);
                    uploadtoDB(patientID, appDate, timestamp, selectedServices);
                }).start();
            });


        }

        /*    new Thread(() -> {
                try {
                    String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/appointsummary.php";
                    String postData = "userID=" +userId
                            + "&dentistID=" +"1"
                            + "&appTime=" + selectedTime
                    + "&appDate=" +selectedDate
                            + "&status=" + "Pending";

                    String result = ConnectToDatabase.sendPostRequest(urlStr, postData);

                    requireActivity().runOnUiThread(() -> {

                        loadrecords(result, view, containerLayout, status, searchterm);
                    });

                } catch (Exception e) {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(requireContext(),
                                    "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();*/
/*
<LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:weightSum="2">
                    <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:autoSizeMinTextSize="9sp"
            android:autoSizeMaxTextSize="16sp"
            android:text="Estimated Cost"
            android:textSize="14sp"
            android:textColor="@color/black"
            android:fontFamily="@font/inter_24pt_regular"
            android:letterSpacing="0.0001"/>

                    <TextView
            android:id="@+id/EstimatedCost"                    android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:fontFamily="@font/inter_24pt_regular"
            android:gravity="end"
            android:autoSizeMinTextSize="9sp"
            android:autoSizeMaxTextSize="16sp"
            android:text="$1,500"
            android:textColor="@color/hintColor"
            android:textSize="14sp"
            android:letterSpacing="0.0001"/>
                    </LinearLayout>*/




        return view;
    }


public void uploadtoDB(String patientId, String appDate,String appTime,ArrayList<String> selectedServices){
                try {
                    String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/appointsummary_bookappointment.php";

                    // Convert services array to JSON string
                    JSONArray servicesArray = new JSONArray();
                    for (String service : selectedServices) {
                        servicesArray.put(service);
                    }

                    // Prepare POST data
                    String postData = "dentist_id=" + "1"
                            + "&patient_id=" + patientId
                            + "&appointmentdate=" + appDate
                            + "&appointmenttime=" + appTime
                            + "&services=" + servicesArray.toString();
                    Log.d("ReceivedServices",patientId+" "+appDate+" "+appTime+" "+servicesArray.toString());

                    // Send POST request
                    String result = ConnectToDatabase.sendPostRequest(urlStr, postData);

                    // Handle response on UI thread
                    requireActivity().runOnUiThread(() -> {
                        try {
                            JSONObject json = new JSONObject(result);
                            if (json.getString("status").equals("success")) {
                                int appointmentId = json.getInt("appointment_id");
                                Toast.makeText(requireContext(),
                                        "Appointment booked! ID: " + appointmentId,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(requireContext(),
                                        "Error: " + json.getString("message"),
                                        Toast.LENGTH_LONG).show();
                                Log.d("ReceivedServices", json.getString("message"));

                            }
                        } catch (Exception e) {
                            Toast.makeText(requireContext(),
                                    "Parse error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            Log.e("BookAppointment", "JSON parse error", e);
                        }
                    });

                } catch (Exception e) {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(requireContext(),
                                    "Network error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show()
                    );
                    Log.e("BookAppointment", "Network error", e);
                }

    }





/*

    public void loadrecords(String result, View view, LinearLayout containerLayout, String status, String searchterm){
        containerLayout.removeAllViews();
        try {
            Log.d("BookingHistory", result);
            JSONObject json = new JSONObject(result);
            if (json.getString("status").equals("success")) {
                NavController navController = Navigation.findNavController(view);
                JSONArray dataArray = json.getJSONArray("data");


                // ✅ Loop through all items
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject item = dataArray.getJSONObject(i);



                    String appointmentdate = item.getString("appointmentdate");
                    String appointmenttime = item.getString("appointmenttime");
                    String appointment_status = item.getString("appointment_status");
                    String services = item.getString("services");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date date = sdf.parse(appointmenttime);

                    SimpleDateFormat monthformat = new SimpleDateFormat("MM", Locale.getDefault());
                    String month = monthformat.format(date);
                    int monthint =Integer.parseInt(month);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a", Locale.getDefault());
                    String time = timeFormat.format(date); // "10:30"

                    String day = appointmentdate.split("-")[2]; // "12"

                    String shortMonth = new DateFormatSymbols().getShortMonths()[monthint - 1];

                    Log.d("BookingHistory", "services: "+services+"   date: "+day+"time: "+time+"appointmentdate: "+appointmentdate + "appointmenttime: "+appointmenttime +"status: "+appointment_status);


                }


            } else {
                Toast.makeText(requireContext(),
                        "Failed: " + json.getString("message"), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(requireContext(),
                    "Parse error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("BookingHistory", e.getMessage());

        }
    }


*/







}