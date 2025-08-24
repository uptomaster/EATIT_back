package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.BannerDTO;

public class BannerListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 배너 목록");
        Result result = new Result();
        
        // 세션이 없거나, ADMIN 회원유형이 아닌 경우(관리자 페이지는 이거 공통으로 들어갑니다.)
        HttpSession s = request.getSession(false);
        if (s == null || !"ADMIN".equals(String.valueOf(s.getAttribute("memberType")))) {
            
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        
        List<BannerDTO> list = new AdminDAO().selectBannerList();
        request.setAttribute("bannerList", list);

        result.setPath("/app/admin/banner/bannerList.jsp");
        result.setRedirect(false);
        return result;
    }
}
