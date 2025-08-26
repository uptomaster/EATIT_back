package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.banner.dao.BannerDAO;
import com.bapseguen.app.dto.BannerDTO;

public class BannerWriteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        // 세션에서 로그인한 관리자 번호 가져오기
        Integer adminNo = (Integer) request.getSession().getAttribute("memberNumber");

        // DTO 생성 및 값 매핑
        BannerDTO dto = new BannerDTO();
        dto.setBannerTitle(request.getParameter("bannerTitle"));
        dto.setImageNumber(Integer.parseInt(request.getParameter("adminImageNumber")));
        dto.setBannerEndDate(request.getParameter("bannerEndDate"));
        dto.setBannerIsActive("Y".equals(request.getParameter("bannerIsActive")));
        dto.setMemberNumber(adminNo);

        // DB insert 실행
        new BannerDAO().insert(dto);

        // 등록 완료 후 목록으로 리다이렉트
        Result result = new Result();
        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/admin/banner/list.ad");
        return result;
    }
}
