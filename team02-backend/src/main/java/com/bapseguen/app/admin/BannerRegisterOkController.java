package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.BannerDTO;

public class BannerRegisterOkController implements Execute {

//	아직 미완성
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 배너 등록 처리");
        Result result = new Result();
        
        HttpSession s = request.getSession(false);
        if (s == null || !"ADMIN".equals(String.valueOf(s.getAttribute("memberType")))) {

        	result.setPath(request.getContextPath() + "/admin/login.ad");
        	result.setRedirect(true);
            return result;
        }

        return result;
    }
}
