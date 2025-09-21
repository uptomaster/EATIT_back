package com.bapseguen.app.orders;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TossService {
    // 문서용 테스트 키
    public static final String DOCS_CLIENT_KEY = "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm"; 
    public static final String DOCS_SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6"; 

    /**
     * 결제 승인(Confirm)
     * @param paymentKey Toss가 successUrl로 전달한 결제키
     * @param orderId    주문번호
     * @param amount     결제금액
     * @param secretKey  Basic 인증에 사용할 시크릿 키
     * @return true면 승인 성공
     */
    public boolean confirm(String paymentKey, String orderId, int amount, String secretKey) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String auth = Base64.getEncoder()
                                .encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
            conn.setRequestProperty("Authorization", "Basic " + auth);
            conn.setRequestProperty("Content-Type", "application/json");

            String body = String.format(
                "{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
                paymentKey, orderId, amount
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return true; // 승인 성공
            } else {
                try (InputStream es = conn.getErrorStream()) {
                    if (es != null) {
                        String errorMsg = new String(es.readAllBytes(), StandardCharsets.UTF_8);
                        System.err.println("Toss confirm 실패: " + errorMsg);
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}
