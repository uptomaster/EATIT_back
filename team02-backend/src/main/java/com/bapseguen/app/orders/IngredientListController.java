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
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.item.dao.ItemDAO;

public class IngredientListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ItemDAO itemDAO = new ItemDAO();

        // 기본 파라미터
        String q = request.getParameter("q");
        // 인기순, 최신순, 가격순
        String sort = request.getParameter("sort");
        String pageParam = request.getParameter("page");

        int page = 1;
        int limit = 8;
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException ignore) {}
        }
        int offset = (page - 1) * limit;

        // itemType은 무조건 INGREDIENT
        String itemType = "INGREDIENT";

        Map<String, Object> params = new HashMap<>();
        params.put("itemType", itemType);
        params.put("q", q);
        params.put("offset", offset);
        params.put("limit", limit);

        List<ItemWithImgDTO> items = itemDAO.searchItems(params);
        int totalCount = itemDAO.countSearchItems(params);
        int totalPages = (int) Math.ceil(totalCount / (double) limit);

        // JSP로 전달
        request.setAttribute("items", items);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("q", q);
        request.setAttribute("sort", sort);
        request.setAttribute("itemType", itemType);

        result.setPath("/app/orders/ingredientList.jsp");
        result.setRedirect(false);
        return result;
    }
}
