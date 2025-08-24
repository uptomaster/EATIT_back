package com.bapseguen.app.cartList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

public class CartListFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = request.getRequestURI().substring(request.getContextPath().length());
		Result result = null;

		// 장바구니 관련 요청 분기 처리
		switch (target) {
		
			// 장바구니 화면 보기(회원의 OPEN 상태의 장바구니 + 담긴 메뉴 목록 조회)
			case "/cartList/view.cl":
				result = new CartListViewController().execute(request, response);
				break;

			// 메뉴 담기(없으면 장바구니 생성 후 메뉴 담기)
			case "/cartList/addItemOk.cl":
				result = new CartListAddItemOkController().execute(request, response);
				break;

			// 장바구니 단건 삭제
			case "/cartList/deleteItemOk.cl":
				result = new CartListDeleteItemOkController().execute(request, response);
				break;

				
			// 장바구니 전체 비우기
			case "/cartList/clearOk.cl":
				result = new CartListClearOkController().execute(request, response);
				break;
				
				
			// 가게 변경(빈 OPEN상태의 장바구니일떄만 사업자번호(business_number 변경)
			// mapper id : cartList.setStore, 
			// DAO 메소드명 : updateCartBusinessNumber(CartDTO dto)
			
			case "/cartList/changeStoreConfirm.cl":
				result = new CartListChangeStoreConfirmController().execute(request, response); 
				break;
				
				
			case "/cartList/changeStoreOk.cl":
				result = new CartListChangeStoreOkController().execute(request, response);
				break;
				
			default:
				// 알 수 없는 경로로 입력했을 때 404 페이지로 포워드하기
			    request.getRequestDispatcher("/errors/404.jsp").forward(request, response);
			    return;		
		}

		// 결과에 따라 이동
		if (result != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}
