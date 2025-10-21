package com.example.vsldental;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreatePasswordFragment extends Fragment {

    private EditText inputNewPass, inputReEnterPass;
    private TextView labelPass, labelPass2;

    private boolean isNewPassVisible = false;
    private boolean isReEnterPassVisible = false;

    public CreatePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_password, container, false);

        inputNewPass = view.findViewById(R.id.inputNewPass);
        inputReEnterPass = view.findViewById(R.id.inputReEnterPass);
        labelPass = view.findViewById(R.id.labelPass);
        labelPass2 = view.findViewById(R.id.labelPass2);
        LinearLayout layoutPass = view.findViewById(R.id.layoutPassword);
        LinearLayout layoutPassword2 = view.findViewById(R.id.layoutPassword2);

        setupFocusAnimation(inputNewPass, labelPass, layoutPass, "Enter Password");
        setupFocusAnimation(inputReEnterPass, labelPass2, layoutPassword2, "Re-enter Password");

        // Individual toggle icons
        setupPasswordToggle(inputNewPass, true);
        setupPasswordToggle(inputReEnterPass, false);

        // Hide keyboard on done
        inputReEnterPass.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputReEnterPass.clearFocus();
                InputMethodManager imm = (InputMethodManager)
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });

        return view;
    }

    private void setupFocusAnimation(EditText editText, TextView label, LinearLayout layout, String hintText) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            GradientDrawable drawable = (GradientDrawable) layout.getBackground();
            if (hasFocus) {
                int focusedColor = ContextCompat.getColor(requireContext(), R.color.mainColor);
                drawable.setStroke(2, focusedColor);
                editText.setHint("");
                editText.animate().translationY(-4f);
                label.animate().translationY(-2f).alpha(1f).setDuration(150).start();
            } else if (editText.getText().toString().isEmpty()) {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
                label.animate().translationY(16f).alpha(0f).setDuration(150).start();
                editText.animate().translationY(-21f);
                editText.setHint(hintText);
            } else {
                int defaultColor = ContextCompat.getColor(requireContext(), R.color.black);
                drawable.setStroke(1, defaultColor);
            }
        });
    }

    private void setupPasswordToggle(EditText editText, boolean isFirst) {
        editText.setOnTouchListener((v, event) -> {
            final int DRAWABLE_END = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (editText.getCompoundDrawables()[DRAWABLE_END] != null &&
                        event.getRawX() >= (editText.getRight() -
                                editText.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {

                    if (isFirst) {
                        togglePasswordVisibility(editText, true);
                    } else {
                        togglePasswordVisibility(editText, false);
                    }
                    return true;
                }
            }
            return false;
        });
    }

    private void togglePasswordVisibility(EditText editText, boolean isFirst) {
        if (isFirst) {
            isNewPassVisible = !isNewPassVisible;
            if (isNewPassVisible) {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                setPasswordIcon(editText, R.drawable.ic_visibility_on, R.color.hintColor);
            } else {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                setPasswordIcon(editText, R.drawable.ic_visibility_off, R.color.hintColor);
            }
            editText.setSelection(editText.getText().length());
        } else {
            isReEnterPassVisible = !isReEnterPassVisible;
            if (isReEnterPassVisible) {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                setPasswordIcon(editText, R.drawable.ic_visibility_on, R.color.hintColor);
            } else {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                setPasswordIcon(editText, R.drawable.ic_visibility_off, R.color.hintColor);
            }
            editText.setSelection(editText.getText().length());
        }
    }

    private void setPasswordIcon(EditText editText, int drawableId, int colorId) {
        Drawable icon = ContextCompat.getDrawable(requireContext(), drawableId);
        if (icon != null) icon.setTint(ContextCompat.getColor(requireContext(), colorId));
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }
}
