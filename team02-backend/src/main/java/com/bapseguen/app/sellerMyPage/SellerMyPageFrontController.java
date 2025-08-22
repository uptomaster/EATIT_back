package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("SellerMyPageFrontController 현재 경로 : "+target);
		Result result = new Result();
		
		switch(target) {
		case "/SellerMyPage/storeInfo.se":
			System.out.println("사업장관리 페이지 이동 요청");
			result = new SellerStoreInfoController().execute(request, response);
			break;

         // Food
         case "/seller/food/addOk.se":
        	 System.out.println("음식 추가 완료 요청");
        	 result = new FoodAddOkController().execute(request, response); 
        	 break;
         case "/seller/food/detailOk.se":       
        	 result = new FoodDetailOkController().execute(request, response); 
        	 break;
         case "/seller/food/editOk.se":         
        	 result = new FoodEditOkController().execute(request, response); 
        	 break;
         case "/seller/food/deleteOk.se":       
        	 result = new FoodDeleteOkController().execute(request, response); 
        	 break;
         case "/seller/food/listOk.se":         
        	 result = new FoodListOkController().execute(request, response); 
        	 break;
         case "/seller/food/alreadyOk.se":      
        	 result = new AlreadyFoodOkController().execute(request, response); 
        	 break;

         // Ingredient
         case "/seller/ingredient/addOk.se":    
        	 result = new IngredientAddOkController().execute(request, response); 
        	 break;
         case "/seller/ingredient/detailOk.se": 
        	 result = new IngredientDetailOkController().execute(request, response); 
        	 break;
         case "/seller/ingredient/editOk.se":   
        	 result = new IngredientEditOkController().execute(request, response); 
        	 break;
         case "/seller/ingredient/deleteOk.se": 
        	 result = new IngredientDeleteOkController().execute(request, response); 
        	 break;
         case "/seller/ingredient/listOk.se":   
        	 result = new IngredientListOkController().execute(request, response); 
        	 break;

         // Item Image
         case "/seller/itemImage/addOk.se":     
        	 result = new ItemImageAddOkController().execute(request, response); 
        	 break;

         // Store info
         case "/seller/infoOk.se":              
        	 result = new StoreInfoOkController().execute(request, response); 
        	 break;

         // Sales
         case "/seller/sales/todayOk.se":       
        	 result = new TodaySaleHistoryOkController().execute(request, response); 
        	 break;
         case "/seller/sales/totalOk.se":       
        	 result = new TotalSaleHistoryOkController().execute(request, response); 
        	 break;
        	 
				
		// 원산지
         case "/seller/origin/addOk.se":     
			 System.out.println("원산지 정보 완료 요청 / originAddOk컨트롤러로 이동");
			 result = new OriginAddOkController().execute(request, response); 
			 break;
         case "/seller/origin/listOk.se": 
        	 System.out.println("원산지 목록 ");
		 	 result = new OriginListOkController().execute(request, response); 
		 	 break;
		  case "/sellerMypage/origin/updateOk.se":     
		 	 result = new OriginUpdateOkController().execute(request, response); 
		 	 break;
		  case "/sellerMypage/origin/deleteOk.se":     
		 	 result = new OriginDeleteOkController().execute(request, response); 
		 	 break;
		  case "/sellerMypage/originAlreadyOk.se":    
		 	 result = new AlreadyOriginOkController().execute(request, response); 
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

}
