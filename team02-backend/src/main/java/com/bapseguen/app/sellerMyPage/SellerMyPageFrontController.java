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
			System.out.println("[판페f] 비밀번호 확인 페이지 요청");
			result = new CheckSellerPwController().execute(request, response);
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
			
		case "/sellerMyPage/detailFoodOk.se":
			System.out.println("[판페f] 음식 메뉴 상세보기");
			result = new FoodDetailOkController().execute(request, response);
			break;
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


		/********************************************************************/

		}

		if (result != null) {
			System.out.println("result: "+result);
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				System.out.println("syso.getPath(): "+result.getPath());
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
 		} //result 가 
	}

}
