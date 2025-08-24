package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            itemType = "FOOD"; // 기본 음식
        }

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        String keyword = request.getParameter("q"); // 검색어 (null 허용)

        int limit = 10;
        int offset = (page - 1) * limit;

        // DAO 호출 
        Map<String,Object> params = new HashMap<>();
        params.put("itemType", itemType);
        params.put("offset", offset);
        params.put("limit", limit);
        params.put("q", keyword);

        List<ItemDTO> items = itemDAO.searchItems(params);
        int totalCount = itemDAO.countSearchItems(params);
        int totalPages = (int) Math.ceil((double) totalCount / limit);

        // request 바인딩 
        request.setAttribute("items", items);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("itemType", itemType);
        request.setAttribute("q", keyword);

        // 경로 포워딩하기
        result.setPath("/app/orders/storeList.jsp");
        result.setRedirect(false);
        return result;
    }
}
