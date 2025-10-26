package com.example.vsldental;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ForgotPhoneFragment extends Fragment {

    public ForgotPhoneFragment() {
        // Required empty public constructor
    }

    EditText inputPhone;
    TextView labelPhone, prefixPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_forgot_phone, container, false);

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

        TextView goToEmail = view.findViewById(R.id.goToEmail);
        goToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_forgot_email);
            }
        });

        MaterialButton btnSendCode = view.findViewById(R.id.btnSendCode);
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_continue);
            }
        });

        return view;
    }
}