package com.example.vsldental;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputType;
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

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    private EditText inputEmail, inputPass;
    private TextView labelEmail, labelPass;
    private boolean isPasswordVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

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

        inputPass = view.findViewById(R.id.inputPass);
        labelPass = view.findViewById(R.id.labelPass);
        LinearLayout layoutPass = view.findViewById(R.id.layoutPassword);

        inputPass.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layoutPass.getBackground();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                inputPass.setHint("");
                inputPass.animate()
                        .translationY(-4f);
                labelPass.animate()
                        .translationY(-2f)
                        .alpha(1f)
                        .setDuration(150)
                        .start();
            } else if (inputPass.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
                labelPass.animate()
                        .translationY(16f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputPass.animate()
                        .translationY(-21f);
                inputPass.setHint("Enter Password");
            } else if (!hasFocus && !inputPass.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
                labelPass.animate()
                        .translationY(16f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputPass.animate()
                        .translationY(-21f);
            }
        });

        inputPass.setOnTouchListener((v, event) -> {
            final int DRAWABLE_END = 2;
            if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (inputPass.getRight() - inputPass.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });

        inputPass.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputPass.clearFocus();

                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });

        layoutEmail.setOnClickListener(v -> inputEmail.requestFocus());
        layoutPass.setOnClickListener(v -> inputPass.requestFocus());

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.popBackStack();
            }
        });

        TextView GoToForgot = view.findViewById(R.id.goToForgotPass);
        GoToForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_forgot);
            }
        });

        MaterialButton btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    try {
                        String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/Login.php";
                        String postData = "email=" + inputEmail.getText().toString() +
                                "&password=" + inputPass.getText().toString();

                        String result = ConnectToDatabase.sendPostRequest(urlStr, postData);
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(requireContext(), "Server says: " + result, Toast.LENGTH_LONG).show()
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

        MaterialButton btnGoToPhone = view.findViewById(R.id.btnGoToPhone);
        btnGoToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_phone);
            }


        });

        TextView linkToSignUp = view.findViewById(R.id.tvEmailSignUp);
        linkToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_go_to_register);
            }
        });


        return view;
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;

        if (isPasswordVisible) {
            inputPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            setPasswordIcon(R.drawable.ic_visibility_on, R.color.hintColor);
        } else {
            inputPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            setPasswordIcon(R.drawable.ic_visibility_off, R.color.hintColor);
        }
        inputPass.setSelection(inputPass.getText().length());
    }

    private void setPasswordIcon(int drawableId, int colorId) {
        Drawable icon = ContextCompat.getDrawable(requireContext(), drawableId);
        if (icon != null) {
            icon.setTint(ContextCompat.getColor(requireContext(), colorId));
        }
        inputPass.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }
}