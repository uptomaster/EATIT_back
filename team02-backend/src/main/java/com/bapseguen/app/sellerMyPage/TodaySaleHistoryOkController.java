package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class TodaySaleHistoryOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String businessNumber = request.getParameter("businessNumber");
        List<Map<String, Object>> list = new SellerMyPageDAO().todaySaleHistory(businessNumber);

        request.setAttribute("todaySales", list);

        Result result = new Result();
//        result.setRedirect(false);
//        result.setPath("/app/store/todaySales.jsp");
        return result;
    }
}