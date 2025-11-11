package com.example.vsldental;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserDashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private String fullName;
    private String patientID;
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


        Log.d("LoginChecker", "UserDashboard: ");
        UserAccessValidator.validate(requireActivity());
        Log.d("LoginChecker", "GetUserid: ");
        String userId = SessionManager.getUserId(requireContext());

        new Thread(() -> {

            loaduserinfo(userId);

        }).start();




        LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
        ProfileBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserDashboard_to_UserProfile);
        });

        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            Bundle bundle = new Bundle();
            bundle.putString("fullName", fullName);
            bundle.putString("patientID", patientID);
            navController.navigate(R.id.action_UserDashboard_to_UserAppoint, bundle);
        });

        LinearLayout RecordsBtn = view.findViewById(R.id.RecordsContainer);
        RecordsBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserDashboard_to_UserRecords);
        });

        LinearLayout ConsultBtn = view.findViewById(R.id.ConsultContainer);
        ConsultBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserDashboard_to_Consultation);
        });

        LinearLayout ExtractBtn = view.findViewById(R.id.ExtractContainer);
        ExtractBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserDashboard_to_Extraction);
        });

        LinearLayout CleanBtn = view.findViewById(R.id.CleaningContainer);
        CleanBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserDashboard_to_Cleaning);
        });
        LinearLayout FillingBtn = view.findViewById(R.id.FillingContainer);
        FillingBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserDashboard_to_Filling);
        });

        LinearLayout BracesBtn = view.findViewById(R.id.BracesContainer);
        BracesBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserDashboard_to_Braces);
        });

        new Thread(() -> {
            try {

                String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/loadmostrecentschedule.php";
                String postData = "userID=" +userId;
                String result = ConnectToDatabase.sendPostRequest(urlStr, postData);
                Log.d("BookingHistory", "userid: "+userId);
                requireActivity().runOnUiThread(() -> {

                    loadupcomingappointment(result, view);
                });

            } catch (Exception e) {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(requireContext(),
                                "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();



        new Thread(() -> {
            try {

                String status = "";
                String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/userloadrecords.php";
                String postData = "userID=" +userId
                        + "&status=" + status;
                String result = ConnectToDatabase.sendPostRequest(urlStr, postData);

                requireActivity().runOnUiThread(() -> {

                    loadrecentappointment(result, view);


                });

            } catch (Exception e) {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(requireContext(),
                                "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();







        recyclerView = view.findViewById(R.id.recyclerAppointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recentLists = new ArrayList<>();
        //recentLists.add(new RecentAppointments("21", "Jan", "Consultation", "09:30 AM - 10:00 AM", "Completed"));
        //recentLists.add(new RecentAppointments("21", "Jan", "Extraction", "09:30 AM - 10:00 AM", "Cancelled"));
        //recentLists.add(new RecentAppointments("21", "Jan", "Consultation", "09:30 AM - 10:00 AM", "Completed"));

        adapter = new RecentAppointmentAdapter(getContext(), recentLists);
        recyclerView.setAdapter(adapter);

        return view;
    }



public void loaduserinfo(String userId){

    try {
        String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/loaduserinfo.php";
        String postData = "userID=" + userId;
        String result = ConnectToDatabase.sendPostRequest(urlStr, postData);

        requireActivity().runOnUiThread(() -> {
            try {
                Log.d("UserDashboard", result);
                JSONObject json = new JSONObject(result);

                if (json.getString("status").equals("success")) {
                    JSONObject data = json.getJSONObject("data");
                    Log.d("UserDashboard", "eyo");
                    patientID = data.getString("patient_id");
                    String firstname = data.getString("firstname");
                    String lastname = data.getString("lastname");
                    String creationdate = data.getString("created_at");

                    requireActivity().runOnUiThread(() -> {
                        TextView tvEmail = requireView().findViewById(R.id.userName);
                        tvEmail.setText(firstname+" "+lastname);
                        fullName = firstname+" "+lastname;
                        TextView tvRole = requireView().findViewById(R.id.memberSince);
                        tvRole.setText(creationdate);
                    });

                } else {
                    Toast.makeText(requireContext(),
                            "Failed: " + json.getString("message"),
                            Toast.LENGTH_SHORT).show();
                    Log.d("UserDashboard", "Fail 1: ");
                }

            } catch (Exception e) {
                Toast.makeText(requireContext(),
                        "Parse error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("UserDashboard", "Fail 2: ");
            }
        });

    } catch (Exception e) {
        requireActivity().runOnUiThread(() ->
                Toast.makeText(requireContext(),
                        "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
        );
        Log.d("UserDashboard", "Fail 3: ");
    }

}





    public void loadrecentappointment(String result, View view){
        try {
            Log.d("BookingHistory", result);
            JSONObject json = new JSONObject(result);
            if (json.getString("status").equals("success")) {
                NavController navController = Navigation.findNavController(view);
                JSONArray dataArray = json.getJSONArray("data");




                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject item = dataArray.getJSONObject(i);

                    String appointment_status = item.getString("appointment_status");
                    String appointmentdate = item.getString("appointmentdate");
                    String appointmenttime = item.getString("appointmenttime");
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
                    recentLists.add(new RecentAppointments(day, shortMonth, services, time, appointment_status));

                    adapter.notifyDataSetChanged();

                    Log.d("BookingHistory", "date: "+day+"time: "+time+"appointmentdate: "+appointmentdate + "appointmenttime: "+appointmenttime +"status: "+appointment_status);



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














    public void loadupcomingappointment(String result, View view){
        try {
            Log.d("BookingHistory", result);
            JSONObject json = new JSONObject(result);
            if (json.getString("status").equals("success")) {
                NavController navController = Navigation.findNavController(view);

                if (!json.has("data") || json.isNull("data") || !(json.get("data") instanceof JSONObject)){
                    Log.d("BookingHistory", "456");

                    TextView consultationtext = view.findViewById(R.id.consultationtext);
                    consultationtext.setText("You Have no Upcoming Appointment");

                    TextView datetext = view.findViewById(R.id.datetext);
                    datetext.setText("Book Now!");

                    TextView timetextup = view.findViewById(R.id.timetext);
                    timetextup.setText("");

                } else {
                    Log.d("BookingHistory", "789");
                    JSONObject item = json.getJSONObject("data");


                    String appointmentdate = item.getString("appointmentdate");
                    String appointmenttime = item.getString("appointmenttime");
                    String services = item.getString("services");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date date = sdf.parse(appointmenttime);

                    SimpleDateFormat monthformat = new SimpleDateFormat("MM", Locale.getDefault());
                    String month = monthformat.format(date);
                    int monthint = Integer.parseInt(month);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a", Locale.getDefault());
                    String time = timeFormat.format(date); // "10:30"

                    String day = appointmentdate.split("-")[2]; // "12"

                    String shortMonth = new DateFormatSymbols().getShortMonths()[monthint - 1];


                    TextView consultationtext = requireView().findViewById(R.id.consultationtext);
                    consultationtext.setText(services);

                    TextView datetext = requireView().findViewById(R.id.datetext);
                    datetext.setText(appointmentdate + "  ");

                    TextView timetextup = requireView().findViewById(R.id.timetext);
                    timetextup.setText(time);


                    LinearLayout mostrecentappdashboard = requireView().findViewById(R.id.consultationbg);
                    mostrecentappdashboard.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("appdate", appointmentdate);
                        bundle.putString("apptime", appointmenttime);
                        bundle.putString("services", services);



                        navController.navigate(R.id.action_UserDashboard_to_MostRecent, bundle);
                        Toast.makeText(requireContext(),
                                "Email: ",
                                Toast.LENGTH_SHORT).show();
                    });
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






}