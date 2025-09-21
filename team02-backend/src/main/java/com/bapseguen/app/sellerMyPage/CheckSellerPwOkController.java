package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class CheckSellerPwOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
        Result result = new Result();
        HttpSession session = request.getSession(false);

        // 세션값이 없을 때 → 로그인 페이지로 이동
        if (session == null) {
            System.out.println("로그아웃 상태에서 마이페이지 접근");
            result.setPath("/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        // 세션에서 memberNumber 가져오기
        Integer memberNumber = (int) session.getAttribute("memberNumber");
        System.out.println("세션의 회원번호 : " + memberNumber);

        // 입력 비밀번호
        String inputPw = request.getParameter("sellerPw");
        System.out.println("입력된 pw: " + inputPw);

        // 사업자번호 저장
        String business = sellerDAO.getBusinessNumber(memberNumber);
        session.setAttribute("businessNumber", business);
        System.out.println("사업자번호: " + business);

        boolean ok = false;
        if (memberNumber != null && inputPw != null && !inputPw.isEmpty()) {
            UserMyPageDAO dao = new UserMyPageDAO();
            ok = dao.checkPassword(memberNumber, inputPw);
        }

        if (ok) {
            // 성공 → 사업장 관리 페이지로 redirect
            result.setPath("/sellerMyPage/storeInfo.se");
            result.setRedirect(true);
        } else {
            // 실패 → 다시 비밀번호 확인 페이지로 forward
            request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            result.setPath("/app/sellerMyPage/sellerCheckPw.jsp");
            result.setRedirect(false);
        }

        return result;
    }
}
