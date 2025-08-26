package com.bapseguen.app.findPw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.findPw.dao.FindPwDAO;

public class UpdatePwOkController implements Execute {
	  @Override
	  public Result execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    Result result = new Result();
	    FindPwDAO dao = new FindPwDAO();

	    Integer memberNumber = (Integer) request.getSession().getAttribute("findPwMemberNumber");
	    String newPw  = request.getParameter("findPw_edit_newPw");
	    String newPwC = request.getParameter("findPw_edit_newPw_chk");

	    if (memberNumber == null || newPw == null || newPw.isEmpty() || (newPwC != null && !newPw.equals(newPwC))) {
	      result.setRedirect(true);
	      result.setPath(request.getContextPath() + "/app/findPw/editPw.jsp?pwChanged=0");
	      return result;
	    }

	    int updated = dao.updatePwAndTouchDates(memberNumber, newPw);
	    request.getSession().removeAttribute("findPwMemberNumber");

	    String ctx = request.getContextPath();
	    if (updated > 0) {
	      result.setRedirect(true);
	      result.setPath(ctx + "/app/login/login.jsp?pwChanged=1");   //성공 플래그
	    } else {
	      result.setRedirect(true);
	      result.setPath(ctx + "/app/findPw/editPw.jsp?pwChanged=0");
	    }
	    return result;
	  }
	}
