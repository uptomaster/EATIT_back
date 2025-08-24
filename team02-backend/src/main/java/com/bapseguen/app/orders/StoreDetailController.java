package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.item.dao.ItemDAO;

public class StoreDetailController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ItemDAO itemDAO = new ItemDAO();

        // 파라미터
        int itemNumber = -1;
        try {
            itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
        } catch (NumberFormatException e) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        // DAO 호출
        ItemDTO item = itemDAO.selectItemDetail(itemNumber);
        List<ItemImageDTO> images = itemDAO.selectItemImages(itemNumber);

        if (item == null) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        // request 바인딩
        request.setAttribute("item", item);
        request.setAttribute("images", images);

        // 뷰 지정 
        result.setPath("/app/orders/storeDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
