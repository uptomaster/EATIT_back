// com.bapseguen.app.findPw.UpdatePwOkController
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

    // 인증완료 단계에서 StartResetController가 세션에 저장한 값
    Integer memberNumber = (Integer) request.getSession().getAttribute("pwResetMemberNumber");

    String newPw  = request.getParameter("findPw_edit_newPw");
    String newPwC = request.getParameter("findPw_edit_newPw_chk");

    String ctx = request.getContextPath();

    // 세션이 없거나 입력이 이상하면 되돌리기
    if (memberNumber == null || newPw == null || newPw.isEmpty() || !newPw.equals(newPwC)) {
      result.setRedirect(true);
      result.setPath(ctx + "/app/findPw/editPw.jsp?pwChanged=0");
      return result;
    }

    
    int updated = dao.updatePwAndTouchDates(memberNumber, newPw);

    // 세션 정리
    request.getSession().removeAttribute("pwResetMemberNumber");

    if (updated > 0) {
      result.setRedirect(true);
      result.setPath(ctx + "/app/login/login.jsp?pwChanged=1");
    } else {
      result.setRedirect(true);
      result.setPath(ctx + "/app/findPw/editPw.jsp?pwChanged=0");
    }
    return result;
  }
}
