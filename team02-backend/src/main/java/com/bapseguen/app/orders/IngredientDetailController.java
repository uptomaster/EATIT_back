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

public class IngredientDetailController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ItemDAO itemDAO = new ItemDAO();

        // 파라미터 파싱
        int itemNumber = -1;
        try {
            itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
        } catch (NumberFormatException ignore) {}

        if (itemNumber <= 0) {
            // 잘못된 접근 시  목록으로 돌려보냄
            result.setPath(request.getContextPath() + "/orders/ingredientList.or");
            result.setRedirect(true);
            return result;
        }

        // 상품 상세 조회
        ItemWithImgDTO item = itemDAO.selectItemDetail(itemNumber);

        if (item == null || !"INGREDIENT".equalsIgnoreCase(item.getItemType())) {
            // 없는 상품이거나 음식인데 재료 페이지로 들어왔을 경우
            result.setPath(request.getContextPath() + "/orders/ingredientList.or");
            result.setRedirect(true);
            return result;
        }

        // 상품 이미지들 조회
        List<ItemImageDTO> images = itemDAO.selectItemImages(itemNumber);

        // JSP로 전달
        request.setAttribute("item", item);
        request.setAttribute("images", images);

        result.setPath("/app/orders/ingredientDetail.jsp");
        result.setRedirect(false); // forward
        return result;
    }
}
