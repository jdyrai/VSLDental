package com.example.vsldental;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

    public class ChooseServiceFragment extends Fragment {

        private RecyclerView recyclerView;
        private RecentAppointmentAdapter adapter;
        private List<RecentAppointments>  recentLists;
        public ChooseServiceFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_choose_service, container, false);
            NavController navController = NavHostFragment.findNavController(this);

            MaterialButton finalBtnNext = view.findViewById(R.id.btnNext);
// Keep track of selected buttons
            List<ImageButton> selectedButtons = new ArrayList<>();

// Array of buttons
            ImageButton[] buttons = {
                    view.findViewById(R.id.btnExtraction),
                    view.findViewById(R.id.btnFilling),
                    view.findViewById(R.id.btnConsultation),
                    view.findViewById(R.id.btnCleaning),
                    view.findViewById(R.id.btnBraces)
            };

// Assign values to buttons via tag
            String[] values = {"Consultation", "Filling", "Cleaning", "Extraction", "Braces"};
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setTag(values[i]);
            }

// Define a single OnClickListener
            View.OnClickListener optionClickListener = new View.OnClickListener() {
                public void onClick(View v) {
                    ImageButton btn = (ImageButton) v;
                    String value = (String) btn.getTag();

                    if (selectedButtons.contains(btn)) {
                        // Deselect
                        selectedButtons.remove(btn);
                        btn.setAlpha(1.0f);
                    } else {
                        // Select
                        selectedButtons.add(btn);
                        btn.setAlpha(0.5f);
                    }

                    // Example: enable "Next" button if at least one selected
                    finalBtnNext.setEnabled(!selectedButtons.isEmpty());

                    // Optional: log selected values
                    List<String> selectedValues = new ArrayList<>();
                    for (ImageButton b : selectedButtons) {
                        selectedValues.add((String) b.getTag());
                    }
                    Log.d("SelectedValues", selectedValues.toString());
                }
            };

// Attach the listener to all buttons
            for (ImageButton b : buttons) {
                b.setOnClickListener(optionClickListener);
            }

            finalBtnNext.setOnClickListener(v -> {
                // Collect selected service names
                ArrayList<String> selectedServices = new ArrayList<>();
                for (ImageButton b : selectedButtons) {
                    selectedServices.add((String) b.getTag());
                }

               Bundle bundle = new Bundle(getArguments());
                String fullName =getArguments().getString("fullName");
                String patientID =getArguments().getString("patientID");
                bundle.putString("patientID", patientID);
                bundle.putString("fullName", fullName);
                bundle.putStringArrayList("selectedServices", selectedServices);

                Log.d("BookingHistory", fullName);
                Log.d("BookingHistory", String.valueOf(selectedServices));


                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_ChooseService_to_ChooseDateTime, bundle);

                // (Optional) show feedback
                Toast.makeText(requireContext(),
                        "Selected: " + selectedServices,
                        Toast.LENGTH_SHORT).show();
            });




            LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
            ProfileBtn.setOnClickListener(v -> {
                Log.d("BookingHistory", "Appointment clicked!");
                navController.navigate(R.id.action_ChooseService_to_UserProfile);
            });

            LinearLayout HomeBtn = view.findViewById(R.id.HomeContainer);
            HomeBtn.setOnClickListener(v -> {
                Log.d("BookingHistory", "Appointment clicked!");
                navController.navigate(R.id.action_ChooseService_to_UserDashboard);
            });

            LinearLayout RecordsBtn = view.findViewById(R.id.RecordsContainer);
            RecordsBtn.setOnClickListener(v -> {
                Log.d("BookingHistory", "Records clicked!");
                navController.navigate(R.id.action_ChooseService_to_UserRecords);
            });



            return view;
        }
}