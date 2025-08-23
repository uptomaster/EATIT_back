package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Result;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.login.dao.LoginDAO;

/**
 * Servlet implementation class sellerMyPageFrontController
 */
public class SellerMyPageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerMyPageFrontController() {
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

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("SellerMyPageFrontController 현재 경로 : " + target);
		Result result = new Result();
		System.out.println();

//		HttpSession session = request.getSession(false);
//		int memberNumber = (int) session.getAttribute("memberNumber");
//		String businessNumber = (String) session.getAttribute("businessNumber");
//      System.out.println("[판페] 세션값 memberNumber : " + memberNumber);
//      System.out.println("[판페] 세션값 memberType   : " + businessNumber);

		switch (target) {
		// // 마이페이지 접근
		// 마이페이지 접근 전 비밀번호 확인 페이지 요청
		case "/sellerMyPage/chkPw.se":
			System.out.println("[판페] 비밀번호 확인 페이지 요청");
			result = new CheckSellerPwController().execute(request, response);
			break;
		case "/sellerMyPage/chkPwOk.se":
			System.out.println("[판페] 비밀번호 확인완료 요청");
			result = new CheckSellerPwOkController().execute(request, response);
			break;
		case "/sellerMyPage/storeInfo.se":
			System.out.println("사업장관리 페이지 이동 요청");
			result = new SellerStoreInfoController().execute(request, response);
			break;

		/********************************************************************/

		// 개인정보 수정
		case "/sellerMyPage/editSellerInfo.se":
			System.out.println("판매자 정보 수정페이지 요청");
			result = new EditSellerInfoController().execute(request, response);
			break;

		// Food
		case "/sellerMyPage/addFood.se":
			System.out.println("음식 추가 페이지 요청");
			result = new FoodAddController().execute(request, response);
			break;
		case "/sellerMyPage/addFookOk.se":
			System.out.println("음식 추가 완료 요청");
			result = new FoodAddOkController().execute(request, response);
			break;
		case "/sellerMyPage/detailFoodOk.se":
			result = new FoodDetailOkController().execute(request, response);
			break;
		case "/seller/editFoodOk.se":
			result = new FoodEditOkController().execute(request, response);
			break;
//         case "/seller/food/deleteOk.se":       
//        	 result = new FoodDeleteOkController().execute(request, response); 
//        	 break;
//		case "/sellerMyPage/FoodlistOk.se":
//			result = new FoodListOkController().execute(request, response);
//			break;
//         case "/seller/food/alreadyOk.se":      
//        	 result = new AlreadyFoodOkController().execute(request, response); 
//        	 break;

//         // Ingredient
//         case "/seller/ingredient/addOk.se":    
//        	 result = new IngredientAddOkController().execute(request, response); 
//        	 break;
//         case "/seller/ingredient/detailOk.se": 
//        	 result = new IngredientDetailOkController().execute(request, response); 
//        	 break;
//         case "/seller/ingredient/editOk.se":   
//        	 result = new IngredientEditOkController().execute(request, response); 
//        	 break;
//         case "/seller/ingredient/deleteOk.se": 
//        	 result = new IngredientDeleteOkController().execute(request, response); 
//        	 break;
//         case "/seller/ingredient/listOk.se":   
//        	 result = new IngredientListOkController().execute(request, response); 
//        	 break;
//
//         // Item Image
//         case "/seller/itemImage/addOk.se":     
//        	 result = new ItemImageAddOkController().execute(request, response); 
//        	 break;
//
//         // Store info
//         case "/seller/infoOk.se":              
//        	 result = new StoreInfoOkController().execute(request, response); 
//        	 break;
//
//         // Sales
//         case "/seller/sales/todayOk.se":       
//        	 result = new TodaySaleHistoryOkController().execute(request, response); 
//        	 break;
//         case "/seller/sales/totalOk.se":       
//        	 result = new TotalSaleHistoryOkController().execute(request, response); 
//        	 break;
//        	 
//				
//		// 원산지
//         case "/seller/origin/addOk.se":     
//			 System.out.println("원산지 정보 완료 요청 / originAddOk컨트롤러로 이동");
//			 result = new OriginAddOkController().execute(request, response); 
//			 break;
//         case "/seller/origin/listOk.se": 
//        	 System.out.println("원산지 목록 ");
//		 	 result = new OriginListOkController().execute(request, response); 
//		 	 break;
//		  case "/sellerMypage/origin/updateOk.se":     
//		 	 result = new OriginUpdateOkController().execute(request, response); 
//		 	 break;
//		  case "/sellerMypage/origin/deleteOk.se":     
//		 	 result = new OriginDeleteOkController().execute(request, response); 
//		 	 break;
//		  case "/sellerMypage/originAlreadyOk.se":    
//		 	 result = new AlreadyOriginOkController().execute(request, response); 
//		 	 break;
		}

		if (result != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
 		} //result 가 
	}

}
