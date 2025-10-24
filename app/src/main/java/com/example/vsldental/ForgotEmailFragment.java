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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ForgotEmailFragment extends Fragment {

    public ForgotEmailFragment() {
        // Required empty public constructor
    }

    EditText inputEmail;
    TextView labelEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_forgot_email, container, false);

        inputEmail = view.findViewById(R.id.inputEmail);
        labelEmail = view.findViewById(R.id.labelEmail);
        LinearLayout layoutEmail = view.findViewById(R.id.layoutEmail);

        inputEmail.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layoutEmail.getBackground();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                inputEmail.setHint("");
                inputEmail.animate()
                        .translationY(-4f);
                labelEmail.animate()
                        .translationY(-5f)
                        .alpha(1f)
                        .setDuration(150)
                        .start();
            } else if (inputEmail.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
                labelEmail.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputEmail.animate()
                        .translationY(-21f);
                inputEmail.setHint("Enter email address");
            } else if (!hasFocus && !inputEmail.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
                labelEmail.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputEmail.animate()
                        .translationY(-21f);
            }
        });

        inputEmail.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputEmail.clearFocus();

                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.popBackStack();
            }
        });

        TextView goToPhone = view.findViewById(R.id.goToPhone);
        goToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_forgot_phone);
            }
        });

        MaterialButton btnSendLink = view.findViewById(R.id.btnSendLink);
        btnSendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_continue);

                new Thread(() -> {
                    try {
                        String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/Change_password.php";
                        String postData = "email=" + inputEmail.getText().toString();
                        String result = ConnectToDatabase.sendPostRequest(urlStr, postData);
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(requireContext(), "" + result, Toast.LENGTH_LONG).show()
                        );

                    } catch (Exception e) {
                        e.printStackTrace();
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show()
                        );
                    }
                }).start();








            }
        });

        return view;
    }
}