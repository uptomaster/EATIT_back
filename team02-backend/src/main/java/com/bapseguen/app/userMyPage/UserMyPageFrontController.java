package com.bapseguen.app.userMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

/**
 * Servlet implementation class userMyPageFrontController
 */
public class UserMyPageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMyPageFrontController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("현재 경로 : " + target);
		Result result = new Result();
		
		switch (target) {
		case "/UserMyPage/generalCheckPw.my":
			System.out.println("내 정보 수정 페이지 요청");
			request.getRequestDispatcher("/app/userMyPage/generalCheckPw.jsp").forward(request, response);
			return;
			
		case "/UserMyPage/chkPwOk.my":
            result = new GeneralCheckPwOkController().execute(request, response);
            break;
            
		case "/UserMyPage/editUserInfo.my":
		    result = new EditUserInfoController().execute(request, response);
		    break;
		    
		case "/UserMyPage/updateMember.my":
		    result = new UpdateMemberController().execute(request, response);
		    break;
		    
		case "/UserMyPage/phoneCode.my":
		    result = new PhoneCodeController().execute(request, response);
		    break;
		    
		case "/UserMyPage/withdrawalAgreement.my":
		    // 탈퇴 동의/비번 확인 페이지 진입
		    request.getRequestDispatcher("/app/userMyPage/withdrawalAgreement.jsp").forward(request, response);
		    break;

		case "/UserMyPage/withdrawOk.my":
		    // 실제 탈퇴 처리
		    result = new WithdrawOkController().execute(request, response);
		    break;
		
		
		
		
		}
		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}
