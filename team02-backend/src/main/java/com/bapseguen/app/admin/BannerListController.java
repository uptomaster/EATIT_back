package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.banner.dao.BannerDAO;
import com.bapseguen.app.dto.BannerDTO;

public class BannerListController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("==============================================");
		System.out.println("[ADMIN] 배너 목록 조회 요청");
		System.out.println("==============================================");


        BannerDAO dao = new BannerDAO();
        List<BannerDTO> bannerList = dao.list();

        // JSP에서 사용할 데이터 전달
        request.setAttribute("bannerList", bannerList);

        Result result = new Result();
        // 규칙: JSP는 /app/... 경로
        result.setPath(request.getContextPath() + "/app/admin/bannerList.jsp");
        result.setRedirect(false); // forward
        return result;
    }
	
}
