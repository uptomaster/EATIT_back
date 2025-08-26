package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class BannerWriteController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Result result = new Result();
        // forward일 때는 contextPath 붙이지 않음
        result.setPath("/app/admin/bannerWrite.jsp");
        result.setRedirect(false);
        System.out.println("[BannerWriteController] forward 경로 → " + result.getPath());

        return result;
    }
}
