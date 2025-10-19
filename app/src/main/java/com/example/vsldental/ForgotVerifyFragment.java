package com.example.vsldental;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class ForgotVerifyFragment extends Fragment {

    public ForgotVerifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_forgot_verify, container, false);

        EditText otp1 = view.findViewById(R.id.otp1);
        EditText otp2 = view.findViewById(R.id.otp2);
        EditText otp3 = view.findViewById(R.id.otp3);
        EditText otp4 = view.findViewById(R.id.otp4);
        EditText otp5 = view.findViewById(R.id.otp5);
        EditText otp6 = view.findViewById(R.id.otp6);

// Put them in an array to make it easier
        EditText[] otpInputs = {otp1, otp2, otp3, otp4, otp5, otp6};

// Attach TextWatchers
        for (int i = 0; i < otpInputs.length; i++) {
            final int index = i;

            otpInputs[i].addTextChangedListener(new android.text.TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.length() == 1 && index < otpInputs.length - 1) {
                        otpInputs[index + 1].requestFocus();
                    }

                    else if (s.length() == 0 && index > 0) {
                        otpInputs[index - 1].requestFocus();
                    }
                }



                @Override
                public void afterTextChanged(android.text.Editable s) {}
            });
        }

        for (EditText otpInput : otpInputs) {
            otpInput.setOnFocusChangeListener((v, hasFocus) -> {
                GradientDrawable drawable = (GradientDrawable) v.getBackground().mutate();
                if (hasFocus) {
                    int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                    drawable.setStroke(3, focusedColor);
                } else {
                    int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                    drawable.setStroke(2, defaultColor);
                }
            });
        }

        otp6.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                otp6.clearFocus();

                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });

        MaterialButton btnVerify = view.findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_continue);
            }
        });


        return view;
    }
}