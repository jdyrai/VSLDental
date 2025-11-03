package com.example.vsldental;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingHistory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookingHistory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingHistory.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingHistory newInstance(String param1, String param2) {
        BookingHistory fragment = new BookingHistory();
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

    private EditText Searchbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_history, container, false);

        Searchbar = view.findViewById(R.id.SearchBar);

        LinearLayout SearchBarAndSortContainer = view.findViewById(R.id.SearchBarAndSortContainer);

        Searchbar.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) SearchBarAndSortContainer.getBackground().mutate();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                Searchbar.setHint("");
                Searchbar.animate()
                        .translationY(-4f);
                Searchbar.animate()
                        .translationY(-5f)
                        .alpha(1f)
                        .setDuration(150)
                        .start();
            } else if (Searchbar.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                Searchbar.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                Searchbar.animate()
                        .translationY(-21f);
                Searchbar.setHint("");
            } else if (!hasFocus && !Searchbar.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                Searchbar.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                Searchbar.animate()
                        .translationY(-21f);
            }


            ImageButton btnBack = view.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController(view);
                    navController.popBackStack();
                }
            });

            LinearLayout  HomeBtn = view.findViewById(R.id.HomeContainer);
            HomeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("a","Button was clicked!");
                    NavController navController = Navigation.findNavController(view);
                    navController.popBackStack();
                }
            });

            LinearLayout  AppointBtn = view.findViewById(R.id.AppointContainer);
            AppointBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("a","Button was clicked!");

                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_bookingHistory_to_bookingHistoryDetails);
                }
            });

            LinearLayout  RecordsBtn = view.findViewById(R.id.RecordsContainer);
            RecordsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("a","Button was clicked!");
                    NavController navController = Navigation.findNavController(view);
                    navController.popBackStack();
                }
            });

            LinearLayout  ProfileBtn = view.findViewById(R.id.ProfileContainer);
            ProfileBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("a","Button was clicked!");
                    NavController navController = Navigation.findNavController(view);
                    navController.popBackStack();
                }
            });


        });
        return inflater.inflate(R.layout.fragment_booking_history, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a", "aappoint was clicked!");
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_bookingHistory_to_bookingHistoryDetails);
            }
        });

        LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
        ProfileBtn.setOnClickListener(v -> {
            Log.d("a", "profile was clicked!");
            NavController navController = Navigation.findNavController(v);
            navController.popBackStack();
        });

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("a","Button was clicked!");
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_bookingHistory_to_bookingHistoryDetails);

            }
        });
    }




}