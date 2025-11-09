package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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