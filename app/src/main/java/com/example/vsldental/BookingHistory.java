package com.example.vsldental;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class BookingHistory extends Fragment {

    private EditText Searchbar;

    public BookingHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking_history, container, false);
        NavController navController = NavHostFragment.findNavController(this);

        Searchbar = view.findViewById(R.id.SearchBar);
        LinearLayout SearchBarAndSortContainer = view.findViewById(R.id.SearchBarAndSortContainer);

        // ✅ Get the NavController once


        // 🔹 Searchbar focus behavior
        Searchbar.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) SearchBarAndSortContainer.getBackground().mutate();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                Searchbar.setHint("");
            } else {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                Searchbar.setHint("");
            }
        });

        // 🔹 Button click handlers — moved OUTSIDE the focus listener
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Log.d("BookingHistory", "Back button clicked!");
            // navController.popBackStack();
        });

        LinearLayout HomeBtn = view.findViewById(R.id.HomeContainer);
        HomeBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Home clicked!");
            // navController.popBackStack();
        });

        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
           navController.navigate(R.id.action_bookingHistory_to_bookingHistoryDetails);
        });

        LinearLayout RecordsBtn = view.findViewById(R.id.RecordsContainer);
        RecordsBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            //navController.popBackStack();
        });

        LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
        ProfileBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Profile clicked!");
            //navController.popBackStack();
        });

        return view;
    }
}
