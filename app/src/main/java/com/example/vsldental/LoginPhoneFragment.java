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

public class LoginPhoneFragment extends Fragment {

    public LoginPhoneFragment() {
        // Required empty public constructor
    }

    EditText inputPhone;
    TextView labelPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login_phone, container, false);

        inputPhone = view.findViewById(R.id.inputPhone);
        labelPhone = view.findViewById(R.id.labelPhone);
        LinearLayout layoutPhone = view.findViewById(R.id.layoutPhone);

        inputPhone.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layoutPhone.getBackground();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                inputPhone.setHint("");
                inputPhone.animate()
                        .translationY(-4f);
                labelPhone.animate()
                        .translationY(-4f)
                        .alpha(1f)
                        .setDuration(150)
                        .start();
            } else if (inputPhone.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
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
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
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

        TextView linkToSignUp = view.findViewById(R.id.tvpPhoneSignUp);
        linkToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_register);
            }
        });

        MaterialButton btnGoToEmail = view.findViewById(R.id.btnGoToEmail);
        btnGoToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_email);
            }
        });

        return view;
    }
}