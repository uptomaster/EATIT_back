package com.bapseguen.app.sellerMyPage;

public class TodaySaleHistoryOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String businessNumber = request.getParameter("businessNumber");
        List<Map<String, Object>> list = new StoreManageDAO().todaySaleHistory(businessNumber);

        request.setAttribute("todaySales", list);

        Result result = new Result();
        result.setRedirect(false);
        result.setPath("/app/store/todaySales.jsp");
        return result;
    }
}