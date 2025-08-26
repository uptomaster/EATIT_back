package com.bapseguen.app.findPw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.findPw.dao.FindPwDAO;

public class TempPwController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    Result result = new Result();
	    FindPwDAO findPwDAO = new FindPwDAO();

	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json; charset=UTF-8");

	    String memberNumberStr = request.getParameter("memberNumber");
	    String tempPw = request.getParameter("tempPw");

	    boolean ok = false;
	    String reason = null;
	    int updated = 0;

	    try (java.io.PrintWriter out = response.getWriter()) {
	        try {
	            System.out.println("[TempPw] in memberNumberStr=" + memberNumberStr + ", tempPw(len)=" + (tempPw==null?0:tempPw.length()));

	            if (memberNumberStr == null || memberNumberStr.isBlank() || tempPw == null || tempPw.isBlank()) {
	                reason = "invalid_input";
	            } else {
	                int memberNumber = Integer.parseInt(memberNumberStr);

	                updated = findPwDAO.updatePwAndTouchDates(memberNumber, tempPw);
	                System.out.println("[TempPw] updated rows=" + updated);

	                if (updated > 0) {
	                    request.getSession().setAttribute("findPwMemberNumber", memberNumber);
	                    ok = true;
	                } else {
	                    reason = "no_row_updated";
	                }
	            }
	        } catch (NumberFormatException nfe) {
	            reason = "bad_member_number";
	            nfe.printStackTrace();
	        } catch (Exception ex) {
	            reason = ex.getClass().getSimpleName(); // 예: BindingException, SQLIntegrityConstraintViolationException, ORA-...
	            ex.printStackTrace();
	        }

	        if (ok) {
	            out.print("{\"ok\":true}");
	        } else {
	            out.print("{\"ok\":false,\"reason\":\"" + (reason == null ? "unknown" : reason) + "\",\"updated\":" + updated + "}");
	        }
	        out.flush();
	    }

	    result.setRedirect(false);
	    result.setPath(null);
	    return result;
	}

	
}
