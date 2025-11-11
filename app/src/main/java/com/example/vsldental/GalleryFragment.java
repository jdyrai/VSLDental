package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;
    ImageAdapter adapter;
    List<Integer> imageList;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);
        TextView aboutusBtn = view.findViewById(R.id.tabAbout);
        aboutusBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_gallery_to_aboutus);
        });

        TextView reviewBtn = view.findViewById(R.id.tabReview);
        reviewBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Appointment clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_gallery_to_review);
        });

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.popBackStack();
        });
        recyclerView = view.findViewById(R.id.imageGrid);

        // Images
        imageList = new ArrayList<>();
        imageList.add(R.drawable.img_gallery_1);
        imageList.add(R.drawable.img_gallery_2);
        imageList.add(R.drawable.img_gallery_3);
        imageList.add(R.drawable.img_gallery_4);
        imageList.add(R.drawable.img_gallery_5);
        imageList.add(R.drawable.img_gallery_6);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new ImageAdapter(getContext(), imageList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}