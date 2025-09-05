package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BoardRecipeDeleteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.deleteBoardRecipe(postNumber);

        Result result = new Result();
        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/admin/boardRecipe/list.ad");
        return result;
    }
}
