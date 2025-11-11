package com.example.vsldental;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    private EditText inputFirstName, inputLastName, inputEmail, inputPass;
    private TextView labelFirstName, labelLastName, labelEmail, labelPass, tvFNameGuide, tvLNameGuide, tvEmailError, tvPassError;
    private boolean isPasswordVisible = false;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        // Layout Binding
        LinearLayout layoutFirstName = view.findViewById(R.id.layoutFirstName);
        LinearLayout layoutLastName = view.findViewById(R.id.layoutLastName);
        LinearLayout layoutEmail = view.findViewById(R.id.layoutEmail);
        LinearLayout layoutPass = view.findViewById(R.id.layoutPass);
        // Button Binding
        MaterialButton btnContinue = view.findViewById(R.id.btnContinue);
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        // EditText Binding
        inputFirstName = view.findViewById(R.id.inputFirstName);
        inputLastName = view.findViewById(R.id.inputLastName);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputPass = view.findViewById(R.id.inputPass);
        // TextView Binding
        labelFirstName = view.findViewById(R.id.labelFirstName);
        labelLastName = view.findViewById(R.id.labelLastName);
        labelEmail = view.findViewById(R.id.labelEmail);
        labelPass = view.findViewById(R.id.labelPass);
        tvFNameGuide = view.findViewById(R.id.tvFNameGuide);
        tvLNameGuide = view.findViewById(R.id.tvLNameGuide);
        tvEmailError = view.findViewById(R.id.tvEmailError);
        tvPassError = view.findViewById(R.id.tvPassError);

        // Input Fields Anim
        setupFocusAnimation(inputFirstName, labelFirstName, layoutFirstName, "Enter your first name");
        setupFocusAnimation(inputLastName, labelLastName, layoutLastName, "Enter your last name");
        setupFocusAnimation(inputEmail, labelEmail, layoutEmail, "Enter your email address");
        setupFocusAnimation(inputPass, labelPass, layoutPass, "Create your password");

        inputPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_END = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Drawable[] drawables = inputPass.getCompoundDrawables();
                    if (drawables[DRAWABLE_END] != null) {
                        float touchX = event.getRawX();
                        float iconStartX = inputPass.getRight() - drawables[DRAWABLE_END].getBounds().width();
                        if (touchX >= iconStartX) {
                            togglePasswordVisibility();
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        inputPass.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, android.view.KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    inputPass.clearFocus();
                    InputMethodManager imm = (InputMethodManager) requireContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.popBackStack();
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavController navController = Navigation.findNavController(view);
                //navController.navigate(R.id.action_continue);

                errorMessage(inputFirstName, tvFNameGuide);
                errorMessage(inputLastName, tvLNameGuide);
                errorMessage(inputEmail, tvEmailError);
                errorMessage(inputPass, tvPassError);


                new Thread(() -> {
                    try {
                        String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/AccountRegistration_Page2.php";
                        String postData = "email=" + inputEmail.getText().toString() +
                                "&password=" + inputPass.getText().toString() +
                                "&role=Patient" +
                                "&fullname=" + inputFirstName.getText().toString();
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

        Typeface customFont = ResourcesCompat.getFont(requireContext(), R.font.inter_24pt_regular);
        isPasswordVisible = !isPasswordVisible;

        int start = inputPass.getSelectionStart();
        int end = inputPass.getSelectionEnd();

        if (isPasswordVisible) {
            inputPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            inputPass.post(new Runnable() {
                @Override
                public void run() {
                    setPasswordIcon(R.drawable.ic_visibility_off, R.color.hintColor);
                    inputPass.setTypeface(customFont);
                }
            });
        } else {
            inputPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            inputPass.post(new Runnable() {
                @Override
                public void run() {
                    setPasswordIcon(R.drawable.ic_visibility_on, R.color.hintColor);
                    inputPass.setTypeface(customFont);
                }
            });
        }

        inputPass.setSelection(start, end);
    }

    private void setPasswordIcon(int drawableId, int colorId) {
        Drawable icon = ContextCompat.getDrawable(requireContext(), drawableId);
        if (icon != null) {
            icon.setTint(ContextCompat.getColor(requireContext(), colorId));
        }
        inputPass.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    private void setupFocusAnimation(EditText editText, TextView label, LinearLayout layout, String hintText) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layout.getBackground().mutate();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                editText.setHint("");
                editText.animate().translationY(-4f);
                label.animate().translationY(-2f).alpha(1f).setDuration(150).start();
            } else if (editText.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                label.animate().translationY(16f).alpha(0f).setDuration(150).start();
                editText.animate().translationY(-9f);
                editText.setHint(hintText);
            } else if (!hasFocus && !editText.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.borderColor);
                drawable.setStroke(1, defaultColor);
                label.animate().translationY(16f).alpha(0f).setDuration(150).start();
                editText.animate().translationY(-9f);
            }
        });
    }

//    private void adjustForgotPos() {
//        boolean hasError = !(labelNameGuide.getVisibility() == View.GONE && passError.getVisibility() == View.GONE);
//        float ifHasError = hasError ? 45f : 0f;
//        goToForgot.animate().translationY(ifHasError).setDuration(150).start();
//    }

//    private void adjustContinuePos() {
//        boolean hasError = !(tvFNameGuide.getVisibility() == View.GONE && tvLNameGuide.getVisibility() == View.GONE);
//        float ifHasError = hasError ? 45f : 0f;
//        layout.animate().translationY(ifHasError).setDuration(150).start();
//    }

    private void errorMessage(EditText inputText, TextView errorText) {
        String inputValue = inputText.getText().toString().trim();

        if (inputText == inputFirstName) {
            if (inputValue.isEmpty()) {
                errorText.setText("First name is required.");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);
            }
        }

        if (inputText == inputLastName) {
            if (inputValue.isEmpty()) {
                errorText.setText("Last name is required.");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);
            }
        }

        if (inputText == inputEmail) {
            if (inputValue.isEmpty()) {
                errorText.setText("Email is required.");
                errorText.setVisibility(View.VISIBLE);
            } else if (!Patterns.EMAIL_ADDRESS.matcher(inputValue).matches()) {
                errorText.setText("Please enter a valid email address.");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);
            }
        }

        else if (inputText == inputPass) {
            if (inputValue.isEmpty()) {
                errorText.setText("Password is required.");
                errorText.setVisibility(View.VISIBLE);
            } else if (inputValue.length() < 6) {
                errorText.setText("Password must be at least 6 characters.");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);
            }
        }

        //adjustForgotPos();
    }
}