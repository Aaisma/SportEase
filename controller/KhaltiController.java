package controller;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class KhaltiController {
    // Replace with your actual secret key (test or live)
    private static final String SECRET_KEY = "Key test_secret_key_xxx";
    private static final String VERIFY_URL = "https://khalti.com/api/v2/payment/verify/";

    // amount in NPR; Khalti expects paisa (NPR * 100)
    public boolean verifyToken(String token, double amountNpr) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(VERIFY_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", SECRET_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = "{\"token\":\"" + token + "\",\"amount\":" + (int)(amountNpr * 100) + "}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }

            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}