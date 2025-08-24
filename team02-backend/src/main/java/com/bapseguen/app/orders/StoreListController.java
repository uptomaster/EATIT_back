package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.item.dao.ItemDAO;

public class StoreListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Result result = new Result();
        ItemDAO itemDAO = new ItemDAO();

        // -------------------- 파라미터 --------------------
        String itemType = request.getParameter("itemType");
        if (itemType == null || itemType.isBlank()) {
            itemType = "FOOD"; // 기본은 음식페이지임.
        }

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        int limit = 10; // 페이지당 10개씩
        int offset = (page - 1) * limit;

        // DAO 호출
        List<ItemDTO> items = itemDAO.selectAllItems(itemType, offset, limit);
        int totalCount = itemDAO.countAllItems(itemType);
        int totalPages = (int) Math.ceil((double) totalCount / limit);

        // request 바인딩
        request.setAttribute("items", items);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemType", itemType);

        // 뷰 지정
        result.setPath("/app/orders/storeList.jsp");
        result.setRedirect(false); // forward

        return result;
    }
}
