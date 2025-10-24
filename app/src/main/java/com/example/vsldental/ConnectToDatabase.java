package com.example.vsldental;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConnectToDatabase{

public static String sendPostRequest(String requestURL, String postData) throws Exception {
    URL url = new URL(requestURL);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);

    try (OutputStream os = conn.getOutputStream()) {
        os.write(postData.getBytes(StandardCharsets.UTF_8));
    }

    StringBuilder response = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
    }

    conn.disconnect();
    return response.toString();
}
}
