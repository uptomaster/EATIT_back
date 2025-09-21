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

	    // 1️⃣ GET 파라미터로 주소 받기
	    String userAddress = request.getParameter("address");

	    // 2️⃣ 세션에 저장
	    if(userAddress != null && !userAddress.isEmpty()) {
	        request.getSession().setAttribute("userAddress", userAddress);
	    }

	    // 3️⃣ 세션에서 주소 가져오기 (로고 클릭으로 돌아왔을 때도 적용됨)
	    String sessionAddress = (String) request.getSession().getAttribute("userAddress");
	    if(sessionAddress != null && !sessionAddress.isEmpty()) {
	        userAddress = sessionAddress;
	    } else {
	        // 세션에도 없으면 기본값
	        userAddress = "서울 강남구 테헤란로 146";
	    }

	    double[] userLatLng = GeoUtil.getLatLngFromAddress(userAddress);
	    System.out.println("사용자 주소: " + userAddress + ", 좌표: " + userLatLng[0] + ", " + userLatLng[1]);

	    // 가게 리스트
	    List<MainStoreListDTO> storeList = new StoreDAO().getAllStores();
	    for (MainStoreListDTO store : storeList) {
	        double dist = GeoUtil.distance(userLatLng[0], userLatLng[1],
	                                       store.getLatitude(), store.getLongitude());
	        store.setDistance(dist);
	    }
	    storeList.sort(Comparator.comparingDouble(MainStoreListDTO::getDistance));
	    request.setAttribute("storeList", storeList);

	    // 재료 리스트
	    List<ItemWithImgDTO> ingredientList = new StoreDAO().getAllIngredientsWithStore();
	    for (ItemWithImgDTO ingredient : ingredientList) {
	        double dist = GeoUtil.distance(userLatLng[0], userLatLng[1],
	        		ingredient.getLatitude(), ingredient.getLongitude());
	        ingredient.setDistance(dist);
	    }
	    ingredientList.sort(Comparator.comparingDouble(ItemWithImgDTO::getDistance));
	    request.setAttribute("ingredientList", ingredientList);

	    // 기타 데이터
	    List<PostDetailDTO> recipeList = mainDAO.selectMainRecipeList();
	    request.setAttribute("recipeList", recipeList);

	    List<AdminImageDTO> bannerList = mainDAO.selectAdminImageDTO();
	    request.setAttribute("bannerList", bannerList);

	    result.setPath("/main.jsp");
	    result.setRedirect(false);
	    return result;
	}
}