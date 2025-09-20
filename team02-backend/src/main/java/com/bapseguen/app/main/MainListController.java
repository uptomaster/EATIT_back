package com.bapseguen.app.main;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.app.main.dao.MainDAO;
import com.bapseguen.app.main.dao.StoreDAO;

public class MainListController {

    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== MainListController 실행 ====");

        MainDAO mainDAO = new MainDAO();
        Result result = new Result();

        String userAddress = request.getParameter("address");
        if (userAddress == null || userAddress.isEmpty()) {
            userAddress = "서울 중구 세종대로 110"; // 기본값
        }

        double[] userLatLng = GeoUtil.getLatLngFromAddress(userAddress);
        System.out.println("사용자 주소: " + userAddress + ", 좌표: " + userLatLng[0] + ", " + userLatLng[1]);

        List<MainStoreListDTO> storeList = new StoreDAO().getAllStores();
        for (MainStoreListDTO store : storeList) {
            double dist = GeoUtil.distance(userLatLng[0], userLatLng[1],
                                           store.getLatitude(), store.getLongitude());
            store.setDistance(dist);
        }

        storeList.sort(Comparator.comparingDouble(MainStoreListDTO::getDistance));
        request.setAttribute("storeList", storeList);


        // -------------------------------
        // 4. 기타 데이터
        // -------------------------------
        List<ItemWithImgDTO> ingredientList = mainDAO.selectMainIngredientList();
        request.setAttribute("ingredientList", ingredientList);

        List<PostDetailDTO> recipeList = mainDAO.selectMainRecipeList();
        request.setAttribute("recipeList", recipeList);

        List<AdminImageDTO> bannerList = mainDAO.selectAdminImageDTO();
        request.setAttribute("bannerList", bannerList);

        result.setPath("/main.jsp");
        result.setRedirect(false);
        return result;
    }
}
