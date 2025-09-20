package com.bapseguen.app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Kakao 주소 검색 API를 이용해 주소 → 위도/경도 좌표로 변환 (외부 라이브러리 없이)
 */
public class KakaoGeoUtil {
    private static final String KAKAO_API_KEY = "이남혁 상품 상세용 지도 api키"; // REST API 키

    public static double[] getCoordinates(String address) {
        double[] coords = {0.0, 0.0};
        try {
            String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query="
                    + java.net.URLEncoder.encode(address, "UTF-8");

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_API_KEY);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            String json = response.toString();
            // 예시 응답: {"documents":[{"address_name":"서울 마포구 ...","x":"126.9148","y":"37.5502"}]}
            if (json.contains("\"x\":") && json.contains("\"y\":")) {
                String xValue = json.split("\"x\"")[1].split("\"")[1];
                String yValue = json.split("\"y\"")[1].split("\"")[1];

                coords[0] = Double.parseDouble(yValue); // 위도
                coords[1] = Double.parseDouble(xValue); // 경도
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coords;
    }
}
