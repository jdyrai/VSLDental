package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewsAdapter adapter;
    private List<Review> reviewList;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reviews, container, false);

        recyclerView  = view.findViewById(R.id.reviewRecyclerView);

        reviewList = new ArrayList<>();
        reviewList.add(new Review("Jaslee", "First time ko magpa bunot pero sobrang thank you kay Doc...", 5));
        reviewList.add(new Review("Mark", "Super bait ni Doc, painless extraction!", 5));
        reviewList.add(new Review("Anna", "Napakagaan ng kamay, highly recommended!", 5));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReviewsAdapter(getContext(), reviewList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}