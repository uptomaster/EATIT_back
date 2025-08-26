package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.banner.dao.BannerDAO;
import com.bapseguen.app.dto.BannerDTO;

public class BannerDetailController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("[BannerDetailController] 배너 상세 조회 요청");

        int bannerNumber = Integer.parseInt(request.getParameter("bannerNumber"));

        BannerDAO dao = new BannerDAO();
        BannerDTO banner = dao.detail(bannerNumber);

        request.setAttribute("banner", banner);

        Result result = new Result();
        // 규칙: JSP는 /app/... 경로
        result.setPath(request.getContextPath() + "/app/admin/bannerDetail.jsp");
        result.setRedirect(false); // forward
        return result;
    }
}
