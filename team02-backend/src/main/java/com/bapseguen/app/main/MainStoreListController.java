package com.bapseguen.app.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.main.dao.MainDAO;

public class MainStoreListController {
	
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        System.out.println("==== MainStoreListController 실행 ====");

        MainDAO mainDAO = new MainDAO();
        Result result = new Result();

        // 현재 페이지 번호 파라미터 받기, 기본 1페이지
        String pageParam = request.getParameter("page");
        int page = 1;
        if(pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
                if(page < 1) page = 1;
                if(page > 2) page = 2; // 최대 2페이지 제한
            } catch(NumberFormatException e) {
                page = 1;
            }
        }
        
        int pageSize = 4;
        int offset = (page - 1) * pageSize;

        // 페이징 조건 전달을 위한 Map 생성
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("offset", offset);
        pageMap.put("limit", pageSize);

        List<MainStoreListDTO> storeList = mainDAO.selectStoreList(pageMap);
        request.setAttribute("storeList", storeList);
        request.setAttribute("currentPage", page);

        result.setPath("/main.jsp");
		return result;
	}
}
