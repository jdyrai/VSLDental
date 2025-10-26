package com.example.vsldental;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterOptionalFragment extends Fragment {

    public RegisterOptionalFragment() {
        // Required empty public constructor
    }

    TextView subLabel, prefixPhone, labelPhone;
    EditText inputPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register_optional, container, false);

        subLabel  = view.findViewById(R.id.subLabel);


        inputPhone = view.findViewById(R.id.inputPhone);
        prefixPhone = view.findViewById(R.id.prefixPhone);
        labelPhone = view.findViewById(R.id.labelPhone);
        LinearLayout layoutPhone = view.findViewById(R.id.layoutPhone);

        inputPhone.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layoutPhone.getBackground().mutate();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                inputPhone.setHint("");
                inputPhone.animate()
                        .translationY(13f);
                labelPhone.animate()
                        .translationY(-5f)
                        .alpha(1f)
                        .setDuration(150)
                        .start();
                prefixPhone.animate()
                        .translationY(13f);
            } else if (inputPhone.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                labelPhone.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputPhone.animate()
                        .translationY(-21f);
                inputPhone.setHint("Enter email address");
            } else if (!hasFocus && !inputPhone.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                labelPhone.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputPhone.animate()
                        .translationY(-21f);
            }
        });


        return view;
    }
}