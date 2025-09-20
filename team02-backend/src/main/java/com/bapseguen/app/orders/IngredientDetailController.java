package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.item.dao.ItemDAO;
import com.bapseguen.app.store.dao.MyStoreFavoriteDAO;

public class IngredientDetailController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ItemDAO itemDAO = new ItemDAO();

        int itemNumber = -1;
        try {
            itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
        } catch (NumberFormatException ignore) {}

        if (itemNumber <= 0) {
            result.setPath(request.getContextPath() + "/orders/ingredientList.or");
            result.setRedirect(true);
            return result;
        }

        ItemWithImgDTO item = itemDAO.selectItemDetail(itemNumber);
        if (item == null) {
            result.setPath(request.getContextPath() + "/orders/ingredientList.or");
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

        // 같은 가게 재료 목록
        List<ItemWithImgDTO> ingredientList =
                itemDAO.list(item.getBusinessNumber(), "INGREDIENT", offset, limit);
        int totalCount = itemDAO.count(item.getBusinessNumber(), "INGREDIENT");
        int maxPage = (int) Math.ceil((double) totalCount / limit);

        // 로그인한 회원의 찜 여부 확인
        HttpSession session = request.getSession();
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        boolean isFavorited = false;
        if (memberNumber != null) {
            MyStoreFavoriteDAO favDAO = new MyStoreFavoriteDAO();
            isFavorited = favDAO.isFavorited(memberNumber, item.getBusinessNumber());
        }

        System.out.println("itemNumber = " + item.getItemNumber());
        System.out.println("businessNumber = " + item.getBusinessNumber());
        System.out.println("storeName = " + item.getStoreName());

        // JSP로 데이터 전달
        request.setAttribute("item", item);
        request.setAttribute("images", images);
        request.setAttribute("ingredientList", ingredientList);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("isFavorited", isFavorited);

        result.setPath("/app/orders/ingredientDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
