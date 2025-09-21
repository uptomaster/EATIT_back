package com.bapseguen.app.main;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.main.dao.StoreDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/storeDistanceListByAddress")
public class StoreDistanceController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        // 요청 JSON 파싱
        JsonObject jsonRequest = JsonParser.parseReader(request.getReader()).getAsJsonObject();
        String address = jsonRequest.get("address").getAsString();

        // 주소 → 위도/경도 변환
        double[] userLatLng = GeoUtil.getLatLngFromAddress(address);

        // DB에서 모든 가게 가져오기
        List<MainStoreListDTO> stores = new StoreDAO().getAllStores();

        // 거리 계산
        for (MainStoreListDTO store : stores) {
            double dist = GeoUtil.distance(userLatLng[0], userLatLng[1],
                                           store.getLatitude(), store.getLongitude());
            store.setDistance(dist);
        }

        // 거리순 정렬
        stores.sort(Comparator.comparingDouble(MainStoreListDTO::getDistance));

        // DB에서 재료 가져오기
        List<ItemWithImgDTO> ingredients = new StoreDAO().getAllIngredientsWithStore();

        // 거리 계산
        for (ItemWithImgDTO ingredient : ingredients) {
            double dist = GeoUtil.distance(userLatLng[0], userLatLng[1],
                                           ingredient.getLatitude(), ingredient.getLongitude());
            ingredient.setDistance(dist);
        }

        // 거리순 정렬
        ingredients.sort(Comparator.comparingDouble(ItemWithImgDTO::getDistance));

        // JS에서 쓰기 좋게 JSON 통합
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.add("stores", new Gson().toJsonTree(stores));
        jsonResponse.add("ingredients", new Gson().toJsonTree(ingredients));

        response.getWriter().write(jsonResponse.toString());
    }
}
