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

        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        String storeNumber = request.getParameter("storeNumber");
        String itemNumber = request.getParameter("itemNumber"); // ✅ 상세 유지용

        MyStoreFavoriteDAO dao = new MyStoreFavoriteDAO();
        StoreFavoriteDTO dto = new StoreFavoriteDTO();
        dto.setMemberNumber(memberNumber);
        dto.setBusinessNumber(storeNumber);

        boolean exists = dao.exists(dto);

        String msg;
        if (exists) {
            dao.delete(dto);
            msg = "찜 해제되었습니다.";
        } else {
            dao.insert(dto);
            msg = "찜 완료되었습니다.";
        }

        // 상세페이지 그대로 forward
        request.setAttribute("favMessage", msg);
        result.setPath("/orders/storeDetail.or?itemNumber=" + itemNumber);
        result.setRedirect(false);
        return result;
    }
}

