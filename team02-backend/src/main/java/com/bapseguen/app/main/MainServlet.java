package com.bapseguen.app.main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainServlet extends HttpServlet {

    private static final String KAKAO_API_KEY = "YOUR_REST_API_KEY"; // 🔑 발급받은 REST API 키로 교체하세요.

    // GET 요청을 처리하는 핵심 메서드
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        
        // 요청 경로에 따라 다른 로직 실행
        if (requestURI.endsWith("/convertAddressAndSave")) {
            handleConvertAddress(request, response);
        } else if (requestURI.endsWith("/getSortedStores")) {
            handleGetSortedStores(request, response);
        } else {
            // 다른 경로의 요청은 처리하지 않음
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 주소 변환 및 세션 저장 로직
     */
    private void handleConvertAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullAddress = request.getParameter("fullAddress");
        HttpSession session = request.getSession();

        String jsonResponse = "";
        try {
            String encodedAddress = URLEncoder.encode(fullAddress, "UTF-8");
            String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + encodedAddress;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_API_KEY);

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder apiResponse = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    apiResponse.append(line);
                }
                in.close();

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(apiResponse.toString(), JsonObject.class);
                if (jsonObject.getAsJsonArray("documents").size() > 0) {
                    JsonObject addressData = jsonObject.getAsJsonArray("documents").get(0).getAsJsonObject();
                    double latitude = addressData.get("y").getAsDouble();
                    double longitude = addressData.get("x").getAsDouble();

                    session.setAttribute("userLatitude", latitude);
                    session.setAttribute("userLongitude", longitude);

                    jsonResponse = "{\"status\":\"success\"}";
                } else {
                    jsonResponse = "{\"status\":\"no_result\"}";
                }
            } else {
                jsonResponse = "{\"status\":\"api_error\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"status\":\"server_error\"}";
        }
        
        sendJsonResponse(response, jsonResponse);
    }

    /**
     * 가게 목록 정렬 및 반환 로직
     */
    private void handleGetSortedStores(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Double userLat = (Double) session.getAttribute("userLatitude");
        Double userLng = (Double) session.getAttribute("userLongitude");

        List<Store> allStores = getStoresFromDatabase();
        
        if (userLat != null && userLng != null) {
            for (Store store : allStores) {
                double distance = calculateDistance(userLat, userLng, store.getItemLatitude(), store.getItemLongitude());
                store.setDistance(distance);
            }
            Collections.sort(allStores, Comparator.comparingDouble(Store::getDistance));
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(allStores);

        sendJsonResponse(response, jsonResponse);
    }

    /**
     * JSON 응답을 클라이언트로 전송
     */
    private void sendJsonResponse(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    
    // 두 지점 간의 거리 계산 로직 (기존 코드와 동일)
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
    
    // ⭐ 실제 DB 연동이 필요한 부분
    private List<Store> getStoresFromDatabase() {
        // 예시 데이터
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "A카페", "main_food_img_1.jpg", 37.5665, 126.9780, "09:00", "21:00", 15000));
        stores.add(new Store(2, "B레스토랑", "main_food_img_2.jpg", 37.5645, 126.9800, "10:00", "20:00", 8000));
        stores.add(new Store(3, "C빵집", "main_food_img_3.jpg", 37.5685, 126.9760, "08:00", "22:00", 5000));
        return stores;
    }

    // Store 객체 (기존 코드와 동일)
    public static class Store {
        private int itemNumber;
        private String storeName;
        private String itemImageSystemName;
        private double itemLatitude;
        private double itemLongitude;
        private String storeOpenTime;
        private String storeCloseTime;
        private int itemPrice;
        private double distance;

        public Store(int itemNumber, String storeName, String itemImageSystemName, double itemLatitude, double itemLongitude, String storeOpenTime, String storeCloseTime, int itemPrice) {
            this.itemNumber = itemNumber;
            this.storeName = storeName;
            this.itemImageSystemName = itemImageSystemName;
            this.itemLatitude = itemLatitude;
            this.itemLongitude = itemLongitude;
            this.storeOpenTime = storeOpenTime;
            this.storeCloseTime = storeCloseTime;
            this.itemPrice = itemPrice;
        }

        public double getDistance() { return distance; }
        public void setDistance(double distance) { this.distance = distance; }
        public int getItemNumber() { return itemNumber; }
        public String getStoreName() { return storeName; }
        public String getItemImageSystemName() { return itemImageSystemName; }
        public double getItemLatitude() { return itemLatitude; }
        public double getItemLongitude() { return itemLongitude; }
        public String getStoreOpenTime() { return storeOpenTime; }
        public String getStoreCloseTime() { return storeCloseTime; }
        public int getItemPrice() { return itemPrice; }
    }
}