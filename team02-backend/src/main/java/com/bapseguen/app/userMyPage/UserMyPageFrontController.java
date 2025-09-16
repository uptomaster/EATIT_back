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
		case "/userMyPage/generalCheckPw.my":
			System.out.println("마이페이지 진입 비밀번호 재확인 페이지 요청");
			request.getRequestDispatcher("/app/userMyPage/generalCheckPw.jsp").forward(request, response);
			break;
		case "/userMyPage/generalCheckPwOk.my":
			System.out.println("비밀번호 재확인 처리 요청");
            result = new GeneralCheckPwOkController().execute(request, response);
            break;
 
		case "/userMyPage/editUserInfo.my":
			System.out.println("내 정보 수정 페이지 처리 요청");
		    result = new EditUserInfoController().execute(request, response);
		    break;
		case "/userMyPage/updateMember.my":
			System.out.println("내 정보 수정 처리 요청 ");
		    result = new UpdateMemberController().execute(request, response);
		    break;
		case "/userMyPage/checkPwOk.my":
		    //System.out.println("내 정보 수정 비밀번호 비교 ");
		    new CheckPwOkController().execute(request, response);
		    return;
		case "/userMyPage/userMyPageSmsSend.my":
		    System.out.println("변경할 전화번호 인증번호 전송 요청");
		    result=new UserMypageSMSController().execute(request, response);
		    break;
        
		case "/userMyPage/editUserInfoOk.my":
			System.out.println("내 정보 수정 완료 페이지 처리 요청");
		    result = new EditUserInfoOkController().execute(request, response);
		    break;    
		    
		case "/userMyPage/foodPurchaseListOk.my":
			System.out.println("마이페이지 음식 구매목록 페이지 처리 요청");
			result = new FoodPurchaseListOkController().execute(request, response);
			break;
		case "/userMyPage/ingredientPurchaseListOk.my":
			System.out.println("마이페이지 음식 구매목록 페이지 처리 요청");
			result = new IngredientPurchaseListOkController().execute(request, response);
			break;    

			
		    
		case "/userMyPage/phoneCode.my":
		    result = new PhoneCodeController().execute(request, response);
		    break;
		    
		case "/userMyPage/withdrawalAgreement.my":
		    // 탈퇴 동의/비번 확인 페이지 진입
		    request.getRequestDispatcher("/app/userMyPage/withdrawalAgreement.jsp").forward(request, response);
		    break;

		case "/userMyPage/withdrawOk.my":
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
