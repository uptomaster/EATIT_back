package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.StoreFavoriteDTO;
import com.bapseguen.app.store.dao.MyStoreFavoriteDAO;

public class MyStoreFavoriteListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");

        Result result = new Result();

        // 로그인 확인
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        // ✅ 페이징 처리
        int page = 1;
        int rowCount = 10; // 한 페이지에 10개 출력
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException ignore) {}
        }
        int startRow = (page - 1) * rowCount + 1;
        int endRow = startRow + rowCount - 1;

        // DAO 호출
        MyStoreFavoriteDAO favDAO = new MyStoreFavoriteDAO();
        List<StoreFavoriteDTO> favList = favDAO.selectAll(memberNumber, startRow, endRow);

        // 전체 개수 조회해서 maxPage 계산 (DAO에 count 메서드 필요)
        int totalCount = favDAO.countByMember(memberNumber);
        int maxPage = (int) Math.ceil((double) totalCount / rowCount);

        // JSP에 전달
        request.setAttribute("favList", favList);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);

        result.setPath("/app/orders/myFavorite.jsp");
        result.setRedirect(false);
        return result;
    }
}
