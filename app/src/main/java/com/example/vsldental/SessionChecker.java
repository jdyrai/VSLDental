package com.example.vsldental;

import android.content.Context;
import android.widget.Toast;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class SessionChecker {
    public static boolean checkAdminSession(Context context) {
        try {
            String sessionId = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                    .getString("session_id", null);
            Log.d("SessionChecker", sessionId);
            if (sessionId == null) return false;

            URL url = new URL("http://10.0.2.2/AndroidStudioVSLDentalClinic/RoleChecker.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Cookie", "PHPSESSID=" + sessionId);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            return json.getString("status").equals("success");

        } catch (Exception e) {

            Log.e("SessionChecker", "Error checking session: " + e);
            return false;
        }
    }
}

