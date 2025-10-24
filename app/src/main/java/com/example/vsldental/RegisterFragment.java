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

public class RegisterFragment extends Fragment {


    private EditText inputFullName, inputEmail, inputPass;
    private TextView labelFullName, labelEmail, labelPass;
    private boolean isPasswordVisible = false;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        inputFullName = view.findViewById(R.id.inputFullName);
        labelFullName = view.findViewById(R.id.labelFullName);
        LinearLayout layoutFullName = view.findViewById(R.id.layoutFullName);

        inputFullName.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layoutFullName.getBackground();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                inputFullName.setHint("");
                inputFullName.animate()
                        .translationY(-4f);
                labelFullName.animate()
                        .translationY(-5f)
                        .alpha(1f)
                        .setDuration(150)
                        .start();
            } else if (inputFullName.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
                labelFullName.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputFullName.animate()
                        .translationY(-21f);
                inputFullName.setHint("Enter email address");
            } else if (!hasFocus && !inputFullName.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
                labelFullName.animate()
                        .translationY(15f)
                        .alpha(0f)
                        .setDuration(150)
                        .start();
                inputFullName.animate()
                        .translationY(-21f);
            }
        });

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
        LinearLayout layoutPass = view.findViewById(R.id.layoutPass);

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

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.popBackStack();
            }
        });

        MaterialButton btnContinue = view.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_continue);

                new Thread(() -> {
                    try {
                        String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/AccountRegistration_Page2.php";
                        String postData = "email=" + inputEmail.getText().toString() +
                                "&password=" + inputPass.getText().toString() +
                                "&role=Patient" +
                                "&fullname=" + inputFullName.getText().toString();
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