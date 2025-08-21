package com.bapseguen.app.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

/**
 * Servlet implementation class loginFrontController
 */
public class LoginFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginFrontController() {
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
		System.out.println("현재 경로 : " + target);
		Result result = new Result();

		switch (target) {
		case "/login/login.lo":
			System.out.println("로그인 페이지 요청");
//	        request.getRequestDispatcher("/app/member/login.jsp").forward(request, response);
			result = new LoginController().execute(request, response);
			break;
		case "/login/loginOk.lo":
			System.out.println("로그인 처리 요청");
			result = new LoginOkController().execute(request, response);
			break;
		case "/login/logoutOk.lo":
			System.out.println("로그아웃 처리 요청");
			result = new LogoutOkController().execute(request, response);
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
