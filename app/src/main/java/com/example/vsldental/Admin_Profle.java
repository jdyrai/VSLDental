package com.example.vsldental;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_Profle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_Profle extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_Profle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_Profle.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_Profle newInstance(String param1, String param2) {
        Admin_Profle fragment = new Admin_Profle();
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
        View view = inflater.inflate(R.layout.fragment_admin__profle, container, false);
        NavController navController = NavHostFragment.findNavController(this);

        new Thread(() -> {
            boolean isAuthorized = SessionChecker.checkAdminSession(requireContext());

            requireActivity().runOnUiThread(() -> {
                if (!isAuthorized) {
                    Toast.makeText(requireContext(),
                            "Access Denied: Admins only.", Toast.LENGTH_LONG).show();

                    // Optional: Clear session if invalid
                    requireContext().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                            .edit().remove("session_id").apply();

                    requireActivity().finish(); // Close this activity or redirect
                } else {
                    Log.d("AdminProfile", "Access granted. Welcome, Admin!");
                }
            });
        }).start();




        LinearLayout Logout = view.findViewById(R.id.LogOutContainer);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Logout", "LogoutClickedd");
                new Thread(() -> {
                    try {
                        String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/Logout.php";
                        String result = ConnectToDatabase.sendPostRequest(urlStr, ""); // no data needed

                        requireActivity().runOnUiThread(() -> {
                            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

                            // Clear session info in SharedPreferences
                            requireContext().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                                    .edit()
                                    .remove("session_id")
                                    .apply();

                            // Redirect to login page
                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(R.id.action_adminProfile_to_login);
                        });

                    } catch (Exception e) {
                        requireActivity().runOnUiThread(() -> {
                                    Toast.makeText(requireContext(), "Logout failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("Logout", e.getMessage());
                                }
                        );
                    }
                }).start();
            }
        });

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Log.d("BookingHistory", "Back button clicked!");
            navController.popBackStack();
        });
        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            navController.navigate(R.id.action_adminProfile_to_adminAppoint);
        });

        LinearLayout ManageServiceBtn = view.findViewById(R.id.ManageServicesContainer);
        ManageServiceBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            navController.navigate(R.id.action_adminProfile_to_manageServices);
        });

        LinearLayout RecordsBtn = view.findViewById(R.id.RecordsContainer);
        RecordsBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            navController.navigate(R.id.action_adminProfile_to_adminRecords);
        });

        LinearLayout HomeBtn = view.findViewById(R.id.HomeContainer);
        HomeBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            navController.navigate(R.id.action_adminProfile_to_adminDashboard);
        });

        return view;
    }
}