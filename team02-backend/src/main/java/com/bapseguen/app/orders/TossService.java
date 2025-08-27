package com.bapseguen.app.orders;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TossService {
    // 문서용(v2 Standard 가이드) 테스트 시크릿 키 (docs 키를 쓸 때만 사용)
    public static final String DOCS_SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

    /**
     * 결제 승인(Confirm)
     * @param paymentKey successUrl 쿼리 파라미터로 받은 값
     * @param orderId    successUrl 쿼리 파라미터로 받은 값
     * @param amount     successUrl 쿼리 파라미터로 받은 값(서버 재계산과 동일해야 안전)
     * @param secretKey  Basic 인증에 사용할 시크릿 키("...sk..." 혹은 DOCS_SECRET_KEY)
     * @return true면 승인 성공
     */
    public boolean confirm(String paymentKey, String orderId, int amount, String secretKey) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(15000);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // ★ Authorization: Basic base64("{secretKey}:")
            String basic = "Basic " + Base64.getEncoder()
                    .encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
            conn.setRequestProperty("Authorization", basic);

            String json = "{\"paymentKey\":\"" + escape(paymentKey) + "\","
                        + "\"orderId\":\""   + escape(orderId)    + "\","
                        + "\"amount\":"      + amount             + "}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                // 승인 성공
                return true;
            } else {
                // 실패 본문 로그로 확인
                try (InputStream es = conn.getErrorStream()) {
                    if (es != null) {
                        String err = new String(es.readAllBytes(), StandardCharsets.UTF_8);
                        System.err.println("[TossConfirm] FAIL " + code + " body=" + err);
                    } else {
                        System.err.println("[TossConfirm] FAIL " + code + " (no error body)");
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

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
