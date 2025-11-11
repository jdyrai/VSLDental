package com.example.vsldental;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "AppPrefs";
    private static final String KEY_SESSION_ID = "session_id";
    private static final String KEY_ROLE = "role";
    private static final String KEY_USER_ID = "user_id";

    // Save session data
    public static void saveSession(Context context, String sessionId, String role, String userId) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .putString(KEY_SESSION_ID, sessionId)
                .putString(KEY_ROLE, role)
                .putString(KEY_USER_ID, userId)
                .apply();
    }

    // Retrieve values
    public static String getSessionId(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getString(KEY_SESSION_ID, null);
    }

    public static String getRole(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getString(KEY_ROLE, null);
    }

    public static String getUserId(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getString(KEY_USER_ID, null);
    }

    // Clear everything
    public static void clearSession(Context context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}
