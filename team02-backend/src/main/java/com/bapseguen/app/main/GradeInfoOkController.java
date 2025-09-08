package com.bapseguen.app.main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.GeneralMemberDTO;
import com.bapseguen.app.dto.SellerMemberDTO;
import com.bapseguen.app.main.dao.MainDAO;

public class GradeInfoOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();

        Integer memberNumber = (session == null) ? null : (Integer) session.getAttribute("memberNumber");
        if (memberNumber == null) {
            out.write("{\"ok\":false,\"reason\":\"NO_LOGIN\"}");
            return null; // JSON 직답
        }

        String memberType = (session != null) ? (String) session.getAttribute("memberType") : null;
        MainDAO dao = new MainDAO();
        if (memberType == null || memberType.isEmpty()) {
            memberType = dao.getMemberType(memberNumber); // fallback
        }

        String treeGrade = "";
        int totalPayment = 0;

        if ("GENERAL".equalsIgnoreCase(memberType)) {
            GeneralMemberDTO dto = dao.getGeneralGradeInfo(memberNumber);
            if (dto != null) {
                treeGrade = dto.getGeneralTreeGrade();
                totalPayment = dto.getGeneralPaymentAmount();
            }
        } else if ("SELLER".equalsIgnoreCase(memberType)) {
            SellerMemberDTO dto = dao.getSellerGradeInfo(memberNumber);
            if (dto != null) {
                treeGrade = dto.getSellerTreeGrade();
                totalPayment = dto.getSellerPaymentAmount();
            }
        }

        out.printf(
            "{\"ok\":true,\"memberType\":\"%s\",\"treeGrade\":\"%s\",\"totalPayment\":%d}",
            (memberType == null ? "" : memberType),
            (treeGrade == null ? "" : treeGrade),
            totalPayment
        );
        return null;
    }
}
