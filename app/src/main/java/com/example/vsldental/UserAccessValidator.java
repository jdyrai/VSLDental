package com.example.vsldental;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

public class UserAccessValidator {

    public static void validate(FragmentActivity activity) {
        Context context = activity.getApplicationContext();

        String sessionId = SessionManager.getSessionId(context);
        String role = SessionManager.getRole(context);
        String userId = SessionManager.getUserId(context);


        if (sessionId == null || sessionId.isEmpty()) {

            Toast.makeText(context, "No active session found. Please log in again.", Toast.LENGTH_LONG).show();
            activity.finish();
            return;
        }


        if (role == null || !role.equalsIgnoreCase("Patient")) {
            Toast.makeText(context, "Access Denied: Admins only.", Toast.LENGTH_LONG).show();

            // Optional: clear session
            context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                    .edit().remove("session_id").apply();

            activity.finish();
            return;
        }

        new Thread(() -> {
            boolean isAuthorized = SessionChecker.checkAdminSession(context);
            Log.d("LoginChecker", "UAV:isAuthorized " + isAuthorized);
            activity.runOnUiThread(() -> {
                if (!isAuthorized) {
                    Log.d("LoginChecker", "UAV:notAuthorized " + isAuthorized);
                    Toast.makeText(context, "Session invalid or expired. Please log in again.", Toast.LENGTH_LONG).show();
                    context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                            .edit().remove("session_id").apply();
                    activity.finish();
                } else {
                    Log.d("AdminAccessValidator", "Access granted for Patient (User ID: " + userId + ")");

                }
            });

        }).start();
    }
}
