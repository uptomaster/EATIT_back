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
        // -------------------- Cart (장바구니) --------------------
        case "/cart/view.ct":
            System.out.println("장바구니 페이지 요청");
            result = new PaymentApproveOkController().execute(request, response);
            break;
        case "/cart/addOk.ct":
            System.out.println("장바구니 담기 처리 요청");
            result = new CartAddOkController().execute(request, response);
            break;
        case "/cart/updateOk.ct":
            System.out.println("장바구니 수량 변경 처리 요청");
            result = new CartUpdateOkController().execute(request, response);
            break;
        case "/cart/removeOk.ct":
            System.out.println("장바구니 항목 삭제 처리 요청");
            result = new PaymentReadyController().execute(request, response);
            break;

        // -------------------- Order (주문) --------------------
        case "/order/createOk.or":
            System.out.println("주문 생성 처리 요청 (장바구니 → 주문)");
            result = new OrderCreateOkController().execute(request, response);
            break;
        case "/order/list.or":
            System.out.println("주문 목록 페이지 요청");
            result = new OrderListController().execute(request, response);
            break;
        case "/order/detail.or":
            System.out.println("주문 상세 페이지 요청");
            result = new OrderDetailController().execute(request, response);
            break;
        case "/order/cancelOk.or":
            System.out.println("주문 취소 처리 요청");
            result = new OrderCancelOkController().execute(request, response);
            break;

        // -------------------- Payment (결제) --------------------
        case "/payment/ready.pm":
            System.out.println("결제 준비(결제창 진입) 요청");
            result = new PaymentReadyController().execute(request, response);
            break;
        case "/payment/approveOk.pm":
            System.out.println("결제 승인 처리 요청");
            result = new PaymentApproveOkController().execute(request, response);
            break;
        case "/payment/cancelOk.pm":
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
