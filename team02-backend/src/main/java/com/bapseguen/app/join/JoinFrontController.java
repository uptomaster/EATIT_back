package com.bapseguen.app.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

/**
 * Servlet implementation class joinFrontController
 */
public class JoinFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFrontController() {
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
        case "/join/generalAgreement.jo":
            System.out.println("일반 약관동의 페이지 요청");
            request.getRequestDispatcher("/app/join/joinAgreementUser.jsp").forward(request, response);
            break;

        case "/join/sellerAgreement.jo":
            System.out.println("판매자 약관동의 페이지 요청");
            request.getRequestDispatcher("/app/join/joinAgreementSeller.jsp").forward(request, response);
            break;
            
        case "/join/generalJoin.jo":
            System.out.println("일반 회원가입 페이지 요청");
            request.getRequestDispatcher("/app/join/userInfoInput.jsp").forward(request, response);
            break;

        case "/join/sellerJoin.jo":
            System.out.println("판매자 회원가입 페이지 요청");
            request.getRequestDispatcher("/app/join/sellerInfoInput.jsp").forward(request, response);
            break;

        case "/join/checkId.jo":
            System.out.println("아이디 중복 체크 요청");
            result = new CheckIdOkController().execute(request, response);
            break;

        case "/join/generalJoinOk.jo":
            System.out.println("일반 회원가입 처리 요청");
            result = new GeneralJoinOkController().execute(request, response);
            break;

        case "/join/sellerJoinOk.jo":
            System.out.println("판매자 회원가입 처리 요청");
            result = new SellerJoinOkController().execute(request, response);
            break;
            
        case "/join/successJoin.jo":
            System.out.println("회원가입성공 페이지 요청");
            request.getRequestDispatcher("/app/join/successJoin.jsp").forward(request, response);
            break;        
        
    	case "/join/sendSMS.jo":
	        System.out.println("문자인증 처리");
	        result = new JoinSMSController().execute(request, response);
	        break;
    	case "/join/sendSMSOK.jo":
	        System.out.println("문자인증 완료 처리");
	        result = new VerifyCodeController().execute(request, response);
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