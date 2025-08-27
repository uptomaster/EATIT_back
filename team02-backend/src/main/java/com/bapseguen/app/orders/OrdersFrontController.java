package com.bapseguen.app.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class OrdersFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private boolean isGet(HttpServletRequest r) {
		return "GET".equalsIgnoreCase(r.getMethod());
	}

	private boolean isPost(HttpServletRequest r) {
		return "POST".equalsIgnoreCase(r.getMethod());
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("[ORDER] " + request.getMethod() + " " + target);

		Result result = null;
		switch (target) {
		// ---- Store / Item ----
		case "/orders/storeList.or":
			result = new StoreListController().execute(request, response);
			break; // GET
		case "/orders/storeDetail.or":
			result = new StoreDetailController().execute(request, response);
			break; // GET
		case "/orders/ingredientList.or":
			result = new IngredientListController().execute(request, response);
			break; // GET
		case "/orders/ingredientDetail.or":
			result = new IngredientDetailController().execute(request, response);
			break; // GET

		// ---- Orders ----
		case "/orders/createOk.or":
			if (!isPost(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			result = new OrderCreateOkController().execute(request, response);
			break;

		case "/orders/list.or":
			if (!isGet(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			result = new OrderListController().execute(request, response);
			break;

		case "/orders/detail.or":
			if (!isGet(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			result = new OrderDetailController().execute(request, response);
			break;

		case "/orders/cancelOk.or":
			if (!isPost(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			result = new OrderCancelOkController().execute(request, response);
			break;

		// ---- Payment (Toss) ----
		case "/orders/paymentReady.or": // 주문 생성 직후 결제창 진입 (GET/POST 허용)
			result = new PaymentReadyController().execute(request, response);
			break;

		case "/orders/paymentApproveOk.or": // successUrl (GET)
			if (!isGet(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			result = new PaymentApproveOkController().execute(request, response);
			break;

		case "/orders/paymentCancelOk.or": // failUrl (GET)
			if (!isGet(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			result = new PaymentCancelOkController().execute(request, response);
			break;

		case "/orders/paymentSuccess.or": // successUrl (GET)
			if (!isGet(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			result = new PaymentSuccessController().execute(request, response);
			break;

		case "/orders/paymentFail.or": // failUrl (GET)
			if (!isGet(request)) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			// 실패 처리 컨트롤러가 따로 없으면 PaymentCancelOkController로 재사용해도 됨
			result = new PaymentCancelOkController().execute(request, response);
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		if (result != null) {
			if (result.isRedirect())
				response.sendRedirect(result.getPath());
			else
				request.getRequestDispatcher(result.getPath()).forward(request, response);
		}
	}
}
