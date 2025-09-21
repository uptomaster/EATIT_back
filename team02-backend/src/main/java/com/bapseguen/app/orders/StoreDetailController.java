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

public class StoreDetailController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ItemDAO itemDAO = new ItemDAO();

        // -------------------- 파라미터 --------------------
        String businessNumber = request.getParameter("businessNumber"); // 찜 목록에서 넘어올 수 있음
        int itemNumber = -1;
        try {
            itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
        } catch (NumberFormatException ignore) {}

        // itemNumber 없고 businessNumber만 있을 때 → 첫 상품 찾기
        if (itemNumber <= 0 && businessNumber != null && !businessNumber.isBlank()) {
            ItemWithImgDTO firstItem = itemDAO.selectFirstItemByStore(businessNumber);
            if (firstItem != null) {
                itemNumber = firstItem.getItemNumber();
            }
        }

        // 여전히 itemNumber 못 찾았으면 목록으로 돌려보냄
        if (itemNumber <= 0) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        // -------------------- 상품/가게 조회 --------------------
        ItemWithImgDTO item = itemDAO.selectItemDetail(itemNumber);
        if (item == null) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        // businessNumber가 비어있다면 item에서 가져오기
        if (businessNumber == null || businessNumber.isBlank()) {
            businessNumber = item.getBusinessNumber();
        }

        // 상품 이미지
        List<ItemImageDTO> images = itemDAO.selectItemImages(itemNumber);

        // -------------------- 페이징 --------------------
        int page = 1;
        int limit = 5;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {}
        int offset = (page - 1) * limit;

        // 같은 가게 음식 목록
        List<ItemWithImgDTO> itemList =
                itemDAO.list(businessNumber, "FOOD", offset, limit);
        int totalCount = itemDAO.count(businessNumber, "FOOD");
        int maxPage = (int) Math.ceil((double) totalCount / limit);

        // -------------------- 찜 여부 --------------------
        HttpSession session = request.getSession();
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        boolean isFavorited = false;
        if (memberNumber != null) {
            MyStoreFavoriteDAO favDAO = new MyStoreFavoriteDAO();
            isFavorited = favDAO.isFavorited(memberNumber, businessNumber);
        }

        // -------------------- 로그 --------------------
        System.out.println("itemNumber = " + item.getItemNumber());
        System.out.println("businessNumber = " + businessNumber);
        System.out.println("storeName = " + item.getStoreName());

        // -------------------- JSP 전달 --------------------
        request.setAttribute("item", item);
        request.setAttribute("images", images);
        request.setAttribute("itemList", itemList);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("isFavorited", isFavorited);

        result.setPath("/app/orders/storeDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
