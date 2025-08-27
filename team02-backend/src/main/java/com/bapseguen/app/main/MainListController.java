package com.bapseguen.app.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;
import com.bapseguen.app.dto.BannerDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.app.main.dao.MainDAO;

public class MainListController {
	
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        System.out.println("==== MainListController 실행 ====");

        MainDAO mainDAO = new MainDAO();
        Result result = new Result();
        
        // 배너 목록 출력
        List<BannerDTO> bannerList = mainDAO.selectMainBannerList();
        request.setAttribute("bannerList", bannerList);
        System.out.println("배너 목록 : " + bannerList);
        
        // 가게 목록 출력
        List<MainStoreListDTO> storeList = mainDAO.selectMainStoreList();
        request.setAttribute("storeList", storeList);
        System.out.println("가게 목록 : " + storeList);
        
        // 재료 목록 출력
        List<ItemWithImgDTO> ingredientList = mainDAO.selectMainIngredientList();
        request.setAttribute("ingredientList", ingredientList);
        System.out.println("재료 목록 : " + ingredientList);
        
        // 레시피 목록 출력
        List<PostDetailDTO> recipeList = mainDAO.selectMainRecipeList();
        request.setAttribute("recipeList", recipeList);
        System.out.println("레시피 목록 : " + recipeList);
       
        result.setPath("/main.jsp");
        result.setRedirect(false);
        return result;
	}
}
