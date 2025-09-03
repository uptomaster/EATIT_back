package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class FaqWriteController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[ADMIN] FAQ 작성 페이지 요청");

        Result result = new Result();
        result.setPath("/app/admin/faqWrite.jsp");
        result.setRedirect(false);
        return result;
    }
}
