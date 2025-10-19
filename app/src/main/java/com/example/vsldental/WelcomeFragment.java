package com.example.vsldental;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.button.MaterialButton;

public class WelcomeFragment extends Fragment {

    public WelcomeFragment() {
        super(R.layout.fragment_welcome);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        // Button References
        MaterialButton btnLogin = view.findViewById(R.id.goToLogin);
        MaterialButton btnRegister = view.findViewById(R.id.goToRegister);

        // Animation Reference
        Animation buttonClick = AnimationUtils.loadAnimation(getContext(), R.anim.anim_button);

        // Create reusable animated click listener
        View.OnClickListener animatedClickListener = v -> {
            v.startAnimation(buttonClick);

            NavController navController = Navigation.findNavController(v);
            if (v.getId() == R.id.goToLogin) {
                navController.navigate(R.id.action_welcomeFragment_to_loginFragment);
            } else if (v.getId() == R.id.goToRegister) {
                navController.navigate(R.id.action_welcomeFragment_to_RegisterFragment);
            }
        };

        // Apply the listener to both buttons
        btnLogin.setOnClickListener(animatedClickListener);
        btnRegister.setOnClickListener(animatedClickListener);

        return view;
    }
}
