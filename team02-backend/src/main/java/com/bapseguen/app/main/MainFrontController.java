package com.bapseguen.app.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;
import com.bapseguen.app.community.CommunityFrontController;
import com.bapseguen.app.community.FaqController;
import com.bapseguen.app.community.FreeBoardReadOkController;
import com.bapseguen.app.community.RecipeListOkController;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.orders.IngredientDetailController;
import com.bapseguen.app.orders.IngredientListController;
import com.bapseguen.app.orders.StoreDetailController;
import com.bapseguen.app.orders.StoreListController;
import com.google.gson.Gson;

/**
 * Servlet implementation class MainFrontController
 */
public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("MainFrontController 현재 경로 : " + target);

		Result result = null;

		switch (target) {
		// 메인 목록 조회
		case "/main.ma":
			System.out.println("메인 각 리스트 페이지 처리 요청");
			result = new MainListController().execute(request, response);
			break;
		
		 case "/storeDistanceList":
	            System.out.println("[FrontController] 거리순 가게 목록 요청 처리 시작");
	            new StoreDistanceController().doPost(request, response);
	            new StoreDistanceController().doPost(request, response);
	            System.out.println("[FrontController] 거리순 가게 목록 요청 처리 완료");
	            return;
		
		/*
		 * // 가게 상세 페이지 (storeDetail.jsp forward, 음식/재료 탭 포함) case
		 * "/orders/storeDetail.or": System.out.println("가게 상세 페이지 이동 요청"); result = new
		 * StoreDetailController().execute(request, response); break;
		 */

		// 구매 메인: 음식점/상품 목록 페이지 (storeList.jsp forward)
		case "/orders/storeList.or":
			System.out.println("가게 목록 페이지 이동 요청");
			result = new StoreListController().execute(request, response);
			break;

		// 재료 목록 페이지(ingredientList.jsp forward)
		case "/orders/ingredientList.or":
			System.out.println("재료 목록 페이지 이동 요청");
			result = new IngredientListController().execute(request, response);
			break;

		// 재료 상세 페이지(ingredientDetail.jsp forward, 음식 재료 탭 포함)
		case "/orders/ingredientDetail.or":
			System.out.println("재료 상세 페이지 이동 요청");
			result = new IngredientDetailController().execute(request, response);
			break;

		// 레시피 리스트 페이지
		case "/community/recipeListOk.co":
			System.out.println("레시피 리스트 페이지 이동 요청");
			result = new RecipeListOkController().execute(request, response);
			break;

		// 레시피 리스트 페이지
		case "/community/viewOtherPost.co":
			System.out.println("레시피 리스트 페이지 이동 요청");
			result = new FreeBoardReadOkController().execute(request, response);
			break;
		//나무모달	
	    case "/main/gradeInfo.ma":
	        result = new GradeInfoOkController().execute(request, response);
	        break;			

		}

		if (result != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}
