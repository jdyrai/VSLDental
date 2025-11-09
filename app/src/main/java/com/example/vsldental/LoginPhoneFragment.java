package com.example.vsldental;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
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

    MaterialButton btnSendCode, btnGoToEmail;
    EditText inputPhone;
    TextView labelPhone, prefixPhone, linkToSignUp, errorPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login_phone, container, false);

        // Layout Binding
        LinearLayout layoutPhone = view.findViewById(R.id.layoutPhone);
        // Button Binding
        btnSendCode = view.findViewById(R.id.btnSendCode);
        btnGoToEmail = view.findViewById(R.id.btnGoToEmail);
        // EditText Binding
        inputPhone = view.findViewById(R.id.inputPhone);
        // TextView Binding
        prefixPhone = view.findViewById(R.id.prefixPhone);
        labelPhone = view.findViewById(R.id.labelPhone);
        linkToSignUp = view.findViewById(R.id.tvpPhoneSignUp);
        errorPhone = view.findViewById(R.id.tvPhoneError);

        // Input Fields Anim
        setupFocusAnimation(inputPhone, labelPhone, layoutPhone, prefixPhone);


        linkToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_register);
            }
        });

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorMessage(inputPhone, errorPhone);
            }
        });

        btnGoToEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_email);

            }
        });

        return view;
    }

    private void setupFocusAnimation(EditText editText, TextView label, LinearLayout layout, TextView labelPrefix) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layout.getBackground().mutate();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                editText.setHint("");
                editText.animate().translationY(2f);
                label.animate().translationY(-4f).alpha(1f).setDuration(150).start();
                labelPrefix.animate().translationY(2f);
            } else if (editText.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                label.animate().translationY(15f).alpha(0f).setDuration(150).start();
                editText.animate().translationY(-21f);
            } else if (!hasFocus && !editText.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                label.animate().translationY(15f).alpha(0f).setDuration(150).start();
                editText.animate().translationY(-21f);
            }
        });
    }

    private void errorMessage(EditText inputText, TextView errorText) {
        String inputValue = inputText.getText().toString().trim();

        if (inputText == inputPhone) {
            if (inputValue.isEmpty()) {
                errorText.setText("Phone is required.");
                errorText.setVisibility(View.VISIBLE);
            } else if (!Patterns.PHONE.matcher(inputValue).matches()) {
                errorText.setText("Please enter a valid number.");
                errorText.setVisibility(View.VISIBLE);
            } else if (inputValue.length() > 11 || inputValue.length() < 11) {
                errorText.setText("Number must be 11 digits.");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);
            }
        }
    }

}