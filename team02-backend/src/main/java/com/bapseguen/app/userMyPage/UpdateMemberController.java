package com.bapseguen.app.userMyPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class UpdateMemberController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	System.out.println("====UpdateMemberController 실행====");
    	
    	 HttpSession session = request.getSession(false);
         Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;

         if (memberNumber == null) {
             Result result = new Result();
             result.setRedirect(true);
             result.setPath(request.getContextPath() + "/login/login.lo?login=required");
             return result;
         }

         String newPw     = request.getParameter("newPassword");
         String confirmPw = request.getParameter("confirmPassword");
         String newPhone  = request.getParameter("newPhone");

         UserMyPageDAO userMyPagedao = new UserMyPageDAO();

         // 비밀번호 변경
         if(newPw != null && !newPw.isEmpty()){
             if(newPw.equals(confirmPw)){
            	 userMyPagedao.updatePassword(memberNumber, newPw);
             } else {
                 request.setAttribute("updateError", "비밀번호 확인이 일치하지 않습니다.");
                 request.setAttribute("myPage", userMyPagedao.myPageSelect(memberNumber));
                 Result result = new Result();
                 result.setRedirect(false);
                 result.setPath("/app/userMyPage/editUserInfo.jsp");
                 return result;
             }
         }

         // 전화번호 변경
         if(newPhone != null && !newPhone.isEmpty()){
        	 userMyPagedao.updatePhone(memberNumber, newPhone.replaceAll("\\D", ""));
         }

         // 성공 시
         Result result = new Result();
         result.setRedirect(true);
         result.setPath(request.getContextPath() + "/userMyPage/editUserInfo.my?updated=1");
         return result;
     
		
    }
}