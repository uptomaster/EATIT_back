package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.StoreDTO;
import com.bapseguen.app.dto.view.ReviewWithUserDTO;
import com.bapseguen.app.review.dao.ReviewDAO;

public class StoreReviewController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ReviewDAO reviewDAO = new ReviewDAO();

        // -------------------- 파라미터 --------------------
        String businessNumber = request.getParameter("businessNumber");
        if (businessNumber == null || businessNumber.isEmpty()) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        // -------------------- 페이징 --------------------
        int page = 1;
        int limit = 5; // 한 페이지당 리뷰 개수
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException ignore) {}

        int offset = (page - 1) * limit;

        // 전체 리뷰 개수
        int totalCount = reviewDAO.countReviewsByBusiness(businessNumber);
        int maxPage = (int) Math.ceil((double) totalCount / limit);

        // -------------------- DAO 호출 --------------------
        List<ReviewWithUserDTO> reviews = reviewDAO.selectReviewsByBusiness(businessNumber);

        int start = Math.min(offset, reviews.size());
        int end = Math.min(offset + limit, reviews.size());
        List<ReviewWithUserDTO> pageList = reviews.subList(start, end);

        // -------------------- 평균 별점 --------------------
        double avgRating = reviewDAO.selectAvgRatingByBusiness(businessNumber);

        // -------------------- 가게 정보 --------------------
        StoreDTO storeInfo = reviewDAO.selectStoreInfo(businessNumber);

        // -------------------- 바인딩 --------------------
        request.setAttribute("avgRating", avgRating);
        request.setAttribute("businessNumber", businessNumber);
        request.setAttribute("reviews", pageList);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("totalCount", totalCount);

        if (storeInfo != null) {
            request.setAttribute("storeName", storeInfo.getStoreName());
            request.setAttribute("storeTel", storeInfo.getStoreTel());
            request.setAttribute("storeAddress", storeInfo.getStoreAddress());
            request.setAttribute("storeImageSystemName", storeInfo.getStoreImageSystemName());
            request.setAttribute("storeImageOriginalName", storeInfo.getStoreImageOriginalName());
        }

        result.setPath("/app/orders/reviewList.jsp");
        result.setRedirect(false);
        return result;
    }
}
