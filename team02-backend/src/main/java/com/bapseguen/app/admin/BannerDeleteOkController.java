package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.banner.dao.BannerDAO;

public class BannerDeleteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int bannerNumber = Integer.parseInt(request.getParameter("bannerNumber"));
        new BannerDAO().delete(bannerNumber);

        Result result = new Result();
        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/admin/banner/list.ad");
        return result;
    }
}
