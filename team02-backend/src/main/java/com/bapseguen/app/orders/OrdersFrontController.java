package com.bapseguen.app.orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

/**
 * Servlet implementation class buyFrontController
 */
public class OrdersFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("[ORDER] 현재 경로 : " + target);

        Result result = null;

        switch (target) {
     // -------------------- Store (가게 / 상품 목록) --------------------
     // 구매 메인: 음식점/상품 목록 페이지 (storeList.jsp forward)
     case "/orders/storeList.or":
         result = new StoreListController().execute(request, response);
         break;

     // 가게 상세 페이지 (storeDetail.jsp forward, 음식/재료 탭 포함)
     case "/orders/storeDetail.or":
         result = new StoreDetailController().execute(request, response);
         break;


     // -------------------- Order (주문) --------------------
     // 장바구니 → 주문 확정 처리 (DB에 주문/주문상품 insert)
     case "/orders/createOk.or":
         result = new OrderCreateOkController().execute(request, response);
         break;

     // 회원의 주문 목록 조회 (마이페이지에서 사용, orderList.jsp forward)
     case "/orders/list.or":
         result = new OrderListController().execute(request, response);
         break;

     // 주문 상세 조회 (주문 + 주문상품 join, orderDetail.jsp forward)
     case "/orders/detail.or":
         result = new OrderDetailController().execute(request, response);
         break;

     // 주문 취소 처리 (DB에서 상태 update)
     case "/orders/cancelOk.or":
         result = new OrderCancelOkController().execute(request, response);
         break;


         // -------------------- Payment (결제) --------------------
     case "/orders/paymentReady.or":
         System.out.println("결제 준비(결제창 진입) 요청");
         result = new PaymentReadyController().execute(request, response);
         break;
     case "/orders/paymentApproveOk.or":
         System.out.println("결제 승인 처리 요청");
         result = new PaymentApproveOkController().execute(request, response);
         break;
     case "/orders/paymentCancelOk.or":
         System.out.println("결제 취소 처리 요청");
         result = new PaymentCancelOkController().execute(request, response);
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
