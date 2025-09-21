package com.bapseguen.app.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GeoUtil {
    private static final String KAKAO_API_KEY = "4e59646afa9bc6568eb8d7d2bc08b8c2";

    // 주소 → [위도, 경도]
    public static double[] getLatLngFromAddress(String address) {
        double[] latlng = new double[2];
        try {
            String addr = URLEncoder.encode(address, "UTF-8");
            String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" + addr;
            HttpURLConnection conn = (HttpURLConnection) new URL(apiURL).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_API_KEY);

            if (conn.getResponseCode() != 200) return latlng;

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            JsonObject obj = new Gson().fromJson(sb.toString(), JsonObject.class);
            JsonArray documents = obj.getAsJsonArray("documents");
            if (documents.size() > 0) {
                JsonObject first = documents.get(0).getAsJsonObject();
                latlng[0] = first.get("y").getAsDouble();
                latlng[1] = first.get("x").getAsDouble();
            }
        } catch (Exception e) { e.printStackTrace(); }
        return latlng;
    }

    // 두 좌표 간 거리 계산 (km)
    public static double distance(double lat1, double lng1, double lat2, double lng2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat/2)*Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon/2)*Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
