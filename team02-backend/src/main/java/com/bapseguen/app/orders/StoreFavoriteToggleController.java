package com.bapseguen.app.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.StoreFavoriteDTO;
import com.bapseguen.app.store.dao.MyStoreFavoriteDAO;

public class StoreFavoriteToggleController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");

        Result result = new Result();

        // 로그인 안 된 경우 → 로그인 페이지로
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        String storeNumber = request.getParameter("storeNumber");

        if (storeNumber == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        MyStoreFavoriteDAO dao = new MyStoreFavoriteDAO();
        StoreFavoriteDTO dto = new StoreFavoriteDTO();
        dto.setMemberNumber(memberNumber);
        dto.setBusinessNumber(storeNumber);

        // 토글 처리
        boolean exists = dao.exists(dto);
        String msg;
        if (exists) {
            dao.delete(dto);
            msg = "찜 해제되었습니다.";
        } else {
            dao.insert(dto);
            msg = "찜 완료되었습니다.";
        }

        session.setAttribute("favMessage", msg);

        // 상품 상세로 갈 필요 없이, 찜 목록 페이지로 리다이렉트
        result.setPath(request.getContextPath() + "/orders/myFavorite.or");
        result.setRedirect(true);
        return result;
    }
}
