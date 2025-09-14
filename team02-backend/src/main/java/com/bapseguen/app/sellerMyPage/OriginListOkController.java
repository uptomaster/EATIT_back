package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.OriginDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class OriginListOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        HttpSession session = request.getSession(false);
        String businessNumber = (session != null) ? (String) session.getAttribute("businessNumber") : null;

        SellerMyPageDAO dao = new SellerMyPageDAO();
        List<OriginDTO> list = dao.selectOriginListByBusiness(businessNumber);
        request.setAttribute("originList", list);

        // 수정 모달 열기용 (예: /sellerMyPage/originList.se?edit=123)
        String editParam = request.getParameter("edit");
        if (editParam != null && !editParam.isBlank()) {
            try {
                int originNumber = Integer.parseInt(editParam);
                OriginDTO editOrigin = dao.selectOriginOne(originNumber);
                if (editOrigin != null) {
                    request.setAttribute("editOrigin", editOrigin); // JSP에서 모달 input 채우기
                    request.setAttribute("openEditModal", true);     // JSP에서 모달 자동 오픈 트리거
                }
            } catch (NumberFormatException ignore) {}
        }

        Result result = new Result();
        result.setPath("/app/sellerMyPage/originList.jsp"); // forward
        return result;
	}
	 
}
