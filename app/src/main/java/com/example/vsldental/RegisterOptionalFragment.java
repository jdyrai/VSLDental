package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegisterOptionalFragment extends Fragment {

    public RegisterOptionalFragment() {
        // Required empty public constructor
    }

    TextView subLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register_optional, container, false);

        subLabel  = view.findViewById(R.id.subLabel);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            // For API 24+
            subLabel.setText(Html.fromHtml(getString(R.string.reminder_text), Html.FROM_HTML_MODE_LEGACY));
        } else {
            // For older APIs
            subLabel.setText(Html.fromHtml(getString(R.string.reminder_text)));
        }

        return view;
    }
}