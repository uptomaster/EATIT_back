package com.bapseguen.app.cartList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

public class CartListFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = request.getRequestURI().substring(request.getContextPath().length());
		Result result = null;

		// 장바구니 관련 요청 분기
		switch (target) {
			case "/cartList/view.cl":
				result = new CartListViewController().execute(request, response);
				break;

			case "/cartList/addItemOk.cl":
				result = new CartListAddItemOkController().execute(request, response);
				break;

			case "/cartList/deleteItemOk.cl":
				result = new CartListDeleteItemOkController().execute(request, response);
				break;

			case "/cartList/clearOk.cl":
				result = new CartListClearOkController().execute(request, response);
				break;
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
