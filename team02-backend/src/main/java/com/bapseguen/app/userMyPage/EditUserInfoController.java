package com.bapseguen.app.userMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.MyPageDTO;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class EditUserInfoController implements Execute {


	@Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    	System.out.println("====EditUserInfoController 실행====");
        
        Result result = new Result();
		UserMyPageDAO userMyPageDAO = new UserMyPageDAO();
		
		// 로그인된 사용자 번호 가져오기 (세션)
		Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");

		if (memberNumber == null) {
			result.setPath("/member/login.me"); // 로그인 안된 경우 로그인 페이지로
			result.setRedirect(true);
			return result;
		}

		// 내 정보 조회
		MyPageDTO myPage = userMyPageDAO.myPageSelect(memberNumber);
		request.setAttribute("myPage", myPage);

		// JSP로 포워딩
        result.setRedirect(false);
        result.setPath("/app/userMyPage/editUserInfo.jsp");
        return result;
        
    }
}