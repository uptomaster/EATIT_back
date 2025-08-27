package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.notice.dao.NoticeListDAO;
import com.bapseguen.app.dto.view.NoticeListDTO;

public class NoticeListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 공지사항 목록 요청");

        NoticeListDAO dao = new NoticeListDAO();

        String temp = request.getParameter("page");
        int page = (temp == null || temp.equals("")) ? 1 : Integer.parseInt(temp);

        int rowCount = 10;
        int startRow = (page - 1) * rowCount;

        List<NoticeListDTO> noticeList = dao.selectList(startRow, rowCount);
        int totalCount = dao.countList();
        int totalPage = (int) Math.ceil((double) totalCount / rowCount);

        request.setAttribute("noticeList", noticeList);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        Result result = new Result();
        result.setPath("/app/admin/noticeList.jsp");
        return result;
    }
}
