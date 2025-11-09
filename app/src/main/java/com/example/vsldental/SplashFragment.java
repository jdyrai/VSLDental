package com.example.vsldental;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SplashFragment extends Fragment {

    private boolean hasNavigated = false;
    private final Handler handler = new Handler();
    private Runnable navigateRunnable;

    public SplashFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        ImageView logo = view.findViewById(R.id.logo);

        Animation popUp = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_pop_up);
        logo.startAnimation(popUp);

        navigateRunnable = () -> navigateToWelcome(view);
        handler.postDelayed(navigateRunnable, 2000);


        view.setOnClickListener(v -> {
            handler.removeCallbacks(navigateRunnable);
            navigateToWelcome(view);
        });

        return view;
    }

    private void navigateToWelcome(View view) {
        if (!hasNavigated) {
            hasNavigated = true;
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_splashFragment_to_welcomeFragment);
        }
    }
}
