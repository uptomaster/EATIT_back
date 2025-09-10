// com.bapseguen.app.findPw.StartResetController
package com.bapseguen.app.findPw;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class StartResetController implements Execute {
  @Override
  public Result execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=UTF-8");

    String memberNumberStr = request.getParameter("memberNumber");

    boolean ok = false;
    try (java.io.PrintWriter out = response.getWriter()) {
      if (memberNumberStr == null || memberNumberStr.isBlank()) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.print("{\"ok\":false,\"reason\":\"invalid_member_number\"}");
      } else {
        int memberNumber = Integer.parseInt(memberNumberStr);
        request.getSession(true).setAttribute("pwResetMemberNumber", memberNumber);
        ok = true;
        out.print("{\"ok\":true}");
      }
      out.flush();
    } catch (NumberFormatException nfe) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    Result result = new Result();
    result.setRedirect(false);
    result.setPath(null); // AJAX 응답
    return result;
  }
}
