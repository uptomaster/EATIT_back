package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.item.dao.ItemDAO;

public class StoreDetailController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ItemDAO itemDAO = new ItemDAO();

        int itemNumber = -1;
        try {
            itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
        } catch (NumberFormatException e) {}

        if (itemNumber <= 0) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        ItemWithImgDTO item = itemDAO.selectItemDetail(itemNumber);
        if (item == null) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        List<ItemImageDTO> images = itemDAO.selectItemImages(itemNumber);

        int page = 1;
        int limit = 5;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {}
        int offset = (page - 1) * limit;

        // 같은 가게 음식 목록
        List<ItemWithImgDTO> itemList =
                itemDAO.list(item.getBusinessNumber(), "FOOD", offset, limit);
        int totalCount = itemDAO.count(item.getBusinessNumber(), "FOOD");
        int maxPage = (int) Math.ceil((double) totalCount / limit);

        request.setAttribute("item", item);
        request.setAttribute("images", images);
        request.setAttribute("itemList", itemList);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);

        result.setPath("/app/orders/storeDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
