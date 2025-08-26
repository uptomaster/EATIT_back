package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.banner.dao.BannerDAO;
import com.bapseguen.app.dto.BannerDTO;

public class BannerUpdateOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        Integer adminNo = (Integer) request.getSession().getAttribute("memberNumber");

        BannerDTO dto = new BannerDTO();
        dto.setBannerNumber(Integer.parseInt(request.getParameter("bannerNumber")));
        dto.setBannerTitle(request.getParameter("bannerTitle"));
        dto.setImageNumber(Integer.parseInt(request.getParameter("adminImageNumber")));
        dto.setBannerEndDate(request.getParameter("bannerEndDate"));
        dto.setBannerIsActive("Y".equals(request.getParameter("bannerIsActive")));
        dto.setMemberNumber(adminNo);

        new BannerDAO().update(dto);

        Result result = new Result();
        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/admin/banner/list.ad");
        return result;
    }
}
