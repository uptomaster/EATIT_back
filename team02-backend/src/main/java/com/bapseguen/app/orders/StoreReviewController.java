package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.ReviewWithUserDTO;
import com.bapseguen.app.review.dao.ReviewDAO;

public class StoreReviewController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        ReviewDAO reviewDAO = new ReviewDAO();

        String businessNumber = request.getParameter("businessNumber");
        if (businessNumber == null || businessNumber.isEmpty()) {
            result.setPath(request.getContextPath() + "/orders/storeList.or");
            result.setRedirect(true);
            return result;
        }

        // 리뷰 목록 가져오기
        List<ReviewWithUserDTO> reviews = reviewDAO.selectReviewsByBusiness(businessNumber);

        request.setAttribute("businessNumber", businessNumber);
        request.setAttribute("reviews", reviews);

        result.setPath("/app/orders/reviewList.jsp");
        result.setRedirect(false);
        return result;
    }
}
