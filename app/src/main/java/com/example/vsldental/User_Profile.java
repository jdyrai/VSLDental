package com.example.vsldental;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User_Profile() {
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
        View view = inflater.inflate(R.layout.fragment_user__profile, container, false);


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
                                    .remove("role")
                                    .remove("user_id")
                                    .apply();

                            // Redirect to login page
                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(R.id.action_UserProfile_to_Login);
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

        LinearLayout HomeBtn = view.findViewById(R.id.HomeContainer);
        HomeBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserProfile_to_UserDashboard);
        });

        LinearLayout AboutusBtn = view.findViewById(R.id.AboutUsContainer);
        AboutusBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserProfile_to_AboutUs);
        });

        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserProfile_to_UserAppoint);
        });

        LinearLayout RecordsBtn = view.findViewById(R.id.RecordsContainer);
        RecordsBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserProfile_to_UserRecords);
        });


        LinearLayout ProfileManageBtn = view.findViewById(R.id.ProfilePicAndSettingsContainer);
        ProfileManageBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserProfile_to_UserProfileManagement);
        });

        LinearLayout NotifBtn = view.findViewById(R.id.NotificationContainer);
        NotifBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserProfile_to_Notification);
        });


        return view;
    }
}