package com.example.vsldental;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookingHistory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
private String searchterm="";
    public BookingHistory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static User_Profile newInstance(String param1, String param2) {
        User_Profile fragment = new User_Profile();
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

        View view = inflater.inflate(R.layout.fragment_booking_history, container, false);
        LinearLayout containerLayout = view.findViewById(R.id.AppointmentContainer10);

        String userId = SessionManager.getUserId(requireContext());

        EditText searchBox = view.findViewById(R.id.SearchBar);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // call your function whenever user types
                 searchterm = s.toString().trim();
                Log.d("BookingHistory", "textboxchange! "+ searchterm);

                new Thread(() -> {
                    try {
                        String status = "";
                        String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/userloadrecords.php";
                        String postData = "userID=" + URLEncoder.encode(userId, "UTF-8")
                                + "&searchterm=" + URLEncoder.encode(searchterm, "UTF-8")
                                + "&status=" + URLEncoder.encode(status, "UTF-8");
                        String result = ConnectToDatabase.sendPostRequest(urlStr, postData);

                        requireActivity().runOnUiThread(() -> {

                            loadrecords(result, view, containerLayout, status,searchterm);
                        });

                    } catch (Exception e) {
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(requireContext(),
                                        "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        );
                    }
                }).start();


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });




        new Thread(() -> {
            try {
                String status = "";
                String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/userloadrecords.php";
                String postData = "userID=" +userId
                        + "&searchterm=" +searchterm
                        + "&status=" + status;
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
        }).start();

        LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
        ProfileBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_BookingHistory_to_UserProfile);
        });

        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_BookingHistory_to_UserAppoint);
        });

        LinearLayout HomeBtn = view.findViewById(R.id.HomeContainer);
        HomeBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_BookingHistory_to_UserDashboard);
        });


        LinearLayout AllBtn = view.findViewById(R.id.AllContainer);
        AllBtn.setOnClickListener(v -> {

            new Thread(() -> {
                try {

                    String status = "";
                    String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/userloadrecords.php";
                    String postData = "userID=" +userId
                            + "&searchterm=" +searchterm
                            + "&status=" + status;
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
            }).start();

        });



        LinearLayout CompletedBtn = view.findViewById(R.id.CompletedContainer);
        CompletedBtn.setOnClickListener(v -> {

            new Thread(() -> {
                try {

                    String status = "Completed";
                    String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/userloadrecords.php";
                    String postData = "userID=" +userId
                            + "&searchterm=" +searchterm
                            + "&status=" + status;
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
            }).start();




        });

        LinearLayout CancelledBtn = view.findViewById(R.id.CancelledContainer);
        CancelledBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");

            new Thread(() -> {
                try {
                    String status = "Cancelled";
                    String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/userloadrecords.php";
                    String postData = "userID=" +userId
                            + "&searchterm=" +searchterm
                            + "&status=" + status;

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
            }).start();


        });





        return view;
    }

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
                    // 🔹 Create parent LinearLayout for this record
                    // ✅ Create parent layout for one record
                    LinearLayout itemLayout = new LinearLayout(requireContext());
                    itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                    itemLayout.setGravity(Gravity.CENTER_VERTICAL);
                    itemLayout.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.drawable_input_layout));
                    itemLayout.setPadding(30, 30, 30, 30);
                    itemLayout.setElevation(8f);

// LayoutParams for item
                    LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    itemParams.setMargins(0, 0, 0, 25);
                    itemLayout.setLayoutParams(itemParams);

// ✅ DATE BOX (Left side)
                    LinearLayout dateBox = new LinearLayout(requireContext());
                    dateBox.setOrientation(LinearLayout.VERTICAL);
                    dateBox.setGravity(Gravity.CENTER);
                    dateBox.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.drawable_gradient_v3));
                    LinearLayout.LayoutParams dateBoxParams = new LinearLayout.LayoutParams(150, 150);
                    dateBox.setLayoutParams(dateBoxParams);

// Day Text
                    TextView tvDay = new TextView(requireContext());
                    tvDay.setText(day);
                    tvDay.setTextColor(Color.WHITE);
                    tvDay.setTextSize(17f);
                    tvDay.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.poppins_semibold));
                    tvDay.setGravity(Gravity.CENTER);
                    dateBox.addView(tvDay);

// Month Text
                    TextView tvMonth = new TextView(requireContext());
                    tvMonth.setText(shortMonth);
                    tvMonth.setTextColor(Color.WHITE);
                    tvMonth.setTextSize(11f);
                    tvMonth.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.poppins_semibold));
                    tvMonth.setGravity(Gravity.CENTER);
                    dateBox.addView(tvMonth);

// ✅ RIGHT SIDE DETAILS
                    LinearLayout detailsLayout = new LinearLayout(requireContext());
                    detailsLayout.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams detailsParams = new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1f
                    );
                    detailsParams.setMarginStart(24);
                    detailsLayout.setLayoutParams(detailsParams);

// Service Name
                    TextView tvService = new TextView(requireContext());
                    tvService.setText(services);
                    tvService.setTextSize(15f);
                    tvService.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    tvService.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.inter_24pt_medium));
                    detailsLayout.addView(tvService);

// Time
                    TextView tvTime = new TextView(requireContext());
                    tvTime.setText(time); // e.g. "09:30 AM"
                    tvTime.setTextSize(11f);
                    tvTime.setTextColor(ContextCompat.getColor(requireContext(), R.color.subColor));
                    tvTime.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.inter_24pt_regular));
                    detailsLayout.addView(tvTime);

// ✅ Add views together
                    itemLayout.addView(dateBox);
                    itemLayout.addView(detailsLayout);

// ✅ Click Listener
                    itemLayout.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("appdate", appointmentdate);
                       bundle.putString("apptime", appointmenttime);
                        bundle.putString("appstatus", appointment_status);
                        bundle.putString("services", services);
                        navController.navigate(R.id.action_bookingHistory_to_bookingHistoryDetails, bundle);
                        Toast.makeText(requireContext(),
                                "Email: ",
                                Toast.LENGTH_SHORT).show();
                    });

// ✅ Add to parent container
                    containerLayout.addView(itemLayout);

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
