
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
import com.bapseguen.app.userMyPage.PhoneCodeController;
import com.bapseguen.app.userMyPage.UserMypageSMSController;
import com.bapseguen.app.userMyPage.WithdrawOkController;

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
			System.out.println("[판페f] 비밀번호 확인 페이지 요청");
			request.getRequestDispatcher("/app/sellerMyPage/sellerCheckPw.jsp").forward(request, response);
//			result = new CheckSellerPwController().execute(request, response);
			break;
		case "/sellerMyPage/chkPwOk.se":
			System.out.println("[판페f] 비밀번호 확인완료 요청");
			result = new CheckSellerPwOkController().execute(request, response);
			break;

		/********************************************************************/
		// // 사업장 관리 페이지
		// 이 페이지에서 음식 메뉴 목록, 재료 메뉴 목록 출력함
		case "/sellerMyPage/storeInfo.se":
			System.out.println("[판페f] 사업장관리 페이지 이동 요청");
			result = new SellerStoreInfoController().execute(request, response);
			break;
			// 이 페이지에서 음식 메뉴 목록, 재료 메뉴 목록 출력함
		case "/sellerMyPage/storeIngre.se":
			System.out.println("[판페f] 사업장관리 페이지 이동 요청");
			result = new SellerStoreIngreController().execute(request, response);
			break;
			// 이 페이지에서 음식 메뉴 목록, 재료 메뉴 목록 출력함
		case "/sellerMyPage/storeReview.se":
			System.out.println("[판페f] 사업장관리 페이지 이동 요청");
			result = new SellerStoreReviewController().execute(request, response);
			break;
			
		case "/sellerMyPage/storeImage.se":
			System.out.println("[판페f] 사업장관리 페이지 이동 요청");
		    request.getRequestDispatcher("/app/sellerMyPage/addStoreImage.jsp").forward(request, response);
			break;
		case "/sellerMyPage/storeImageOk.se":
			System.out.println("[판페f] 사업장관리 페이지 이동 요청");
			result = new SellerStoreImageOkController().execute(request, response);
			break;
			

		/********************************************************************/
		// Food
		case "/sellerMyPage/addFood.se":
			System.out.println("[판페f]음식 추가 페이지 요청");
			result = new FoodAddController().execute(request, response);
			break;
		case "/sellerMyPage/addFoodOk.se":
			System.out.println("[판페f]음식 추가 완료 요청");
			result = new FoodAddOkController().execute(request, response);
			break;
		case "/sellerMyPage/detailFoodOk.se":
			System.out.println("[판페f]음식 상세 페이지 완료 요청");
			result = new FoodDetailOkController().execute(request, response);
			break;
		case "/sellerMyPage/editFood.se":
			System.out.println("[판페f]음식 수정 페이지 요청");
			result = new FoodEditController().execute(request, response);
			break;
		case "/sellerMyPage/editFoodOk.se":
			System.out.println("[판페f]음식 수정 페이지 요청");
			result = new FoodEditOkController().execute(request, response);
			break;
        case "/sellerMyPage/deleteFoodOk.se":      
        	System.out.println("[판페f]음식 삭제 요청");
        	result = new FoodDeleteOkController().execute(request, response); 
        	break;

    	/********************************************************************/
	     // Ingredient
		case "/sellerMyPage/addIngre.se":
			System.out.println("[판페f]재료 추가 페이지 요청");
			result = new IngredientAddController().execute(request, response);
			 // 컨트롤러 사라짐..?
			break;
		case "/sellerMyPage/addIngreOk.se":
			System.out.println("[판페f]재료 추가 완료 요청");
			result = new IngredientAddOkController().execute(request, response);
			break;
		case "/sellerMyPage/detailIngreOk.se":
			System.out.println("[판페f]재료 상세 페이지 완료 요청");
			result = new IngredientDetailOkController().execute(request, response);
			
			break;
		case "/sellerMyPage/editIngre.se":
			System.out.println("[판페f]재료 수정 페이지 요청");
			result = new IngredientEditController().execute(request, response);
			
			break;
		case "/sellerMyPage/editIngreOk.se":
			System.out.println("[판페f]재료 수정 페이지 완료 요청");
			result = new IngredientEditOkController().execute(request, response);
			
			break;
        case "/sellerMyPage/deleteIngreOk.se":    
        	System.out.println("[판페f] 재료 삭제 요청");
        	 result = new IngredientDeleteOkController().execute(request, response);
        	 break;
		case "/sellerMyPage/IngrelistOk.se":
			result = new FoodListOkController().execute(request, response);
			break;
		//
		/********************************************************************/
		// // 내 게시글 관리
		case "/sellerMyPage/myPosts.se":
			System.out.println("[판페f] 내 게시글 관리 페이지 요청");
			result = new SellerMyPostController().execute(request, response);
			break;
		case "/sellerMyPage/myComments.se":
			System.out.println("[판페f] 내 댓글 관리 페이지 요청");
			result = new SellerMyCommentController().execute(request, response);
			break;
		case "/sellerMyPage/myReviews.se":
			System.out.println("[판페f] 내 리뷰 관리 페이지 요청");
			result = new SellerMyReviewController().execute(request, response);
			break;
		case "/sellerMyPage/sellerfoodPurchaseList.se":
			System.out.println("[판페f] 내 음식 구매목록 페이지 요청");
			result = new SellerfoodPurchaseController().execute(request, response);
			break;
		case "/sellerMyPage/selleringredientPurchaseList.se":
			System.out.println("[판페f] 내 재료 구매목록 페이지 요청");
			result = new SellerIngrePurchaseController().execute(request, response);
			break;
		case "/sellerMyPage/sellerwriteReview.se":
			System.out.println("[판페f] 리뷰 작성 페이지 요청");
			result = new sellerwriteReviewContorller().execute(request, response);
			break;
		case "/sellerMyPage/sellerwriteReviewOk.se":
			System.out.println("[판페f] 리뷰 작성 페이지 요청");
			result = new sellerwriteReviewOkContorller().execute(request, response);
			break;
		/********************************************************************/
		case "/sellerMyPage/todaySaleList.se":
			System.out.println("[판페f] 오늘 판매내역 페이지 요청");
		    result = new TodaySaleHistoryOkController().execute(request, response);
		    break;

		case "/sellerMyPage/totalSale.se":
			System.out.println("[판페f] 총 판매내역 페이지 요청");
		    result = new TotalSaleHistoryOkController().execute(request, response); 
		    break;
			
		/********************************************************************/
		case "/sellerMyPage/editSellerInfo.se":
			System.out.println("[판페f] 판매자 내정보수정 요청");
			result = new EditSellerInfoController().execute(request, response);
			break;
		case "/sellerMyPage/editSellerInfoOk.se":
			System.out.println("[판페f] 판매자 내정보수정 완료 요청");
			result = new EditSellerInfoOkController().execute(request, response);
			break;
		
		// 휴대폰 인증번호 전송 요청
		case "/sellerMyPage/sellerMyPageSmsSend.se":
		    System.out.println("[판페f] 변경할 전화번호 인증번호 전송 요청");
		    result=new UserMypageSMSController().execute(request, response);
		    break;	
		    
		// 휴대폰 인증번호 
		case "/sellerMyPage/phoneCode.se":
			System.out.println("[판페f] 변경할 전화번호 인증번호 전송 요청");
		    result = new PhoneCodeController().execute(request, response);
		    break;
		    
	    // 탈퇴 동의/비번 확인 페이지 진입
		case "/sellerMyPage/withdrawalAgreement.se":
			System.out.println("[판페f] 변경할 전화번호 인증번호 전송 요청");
		    request.getRequestDispatcher("/app/sellerMyPage/withdrawalAgreement.jsp").forward(request, response);
		    break;

	    // 실제 탈퇴 처리
		case "/sellerMyPage/withdrawOk.se":
			System.out.println("[판페f] 변경할 전화번호 인증번호 전송 요청");
		    result = new WithdrawOkController().execute(request, response);
		    break;

		/********************************************************************/
			// 원산지 목록 페이지
		case "/sellerMyPage/originList.se":
			System.out.println("[판페f] 원산지 목록 출력 요청");
		    result = new OriginListOkController().execute(request, response);
		    break;

		// 추가
		case "/sellerMyPage/originAddOk.se":
			System.out.println("[판페f] 원산지 추가 요청");
		    result = new OriginAddOkController().execute(request, response);
		    break;

		// 단건 조회(수정 모달)
		case "/sellerMyPage/originDetail.se":
			System.out.println("[판페f] 원산지 삭제 요청");
		    result = new OriginDetailController().execute(request, response);
		    break;

		// 수정 저장
		case "/sellerMyPage/originEditOk.se":
			System.out.println("[판페f] 원산지 수정 완료 요청");
		    result = new OriginEditOkController().execute(request, response);
		    break;
		/********************************************************************/
		}

		if (result != null) {
			System.out.println("result: " + result);
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				System.out.println("syso.getPath(): " + result.getPath());
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		} // result 가
		
		
	}
}
