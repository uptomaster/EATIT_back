package com.bapseguen.app.join;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.dto.GeneralMemberDTO;
import com.bapseguen.app.join.dao.JoinDAO;

public class GeneralJoinOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(request.getParameter("user_input_id"));
        memberDTO.setMemberPassword(request.getParameter("user_input_pw"));

        GeneralMemberDTO generalDTO = new GeneralMemberDTO();
        generalDTO.setGeneralName(request.getParameter("user_input_name"));
        generalDTO.setGeneralBirthDate(request.getParameter("user_input_birth"));
        generalDTO.setGeneralPhoneNumber(request.getParameter("user_input_phone"));

        JoinDAO joinDAO = new JoinDAO();
        joinDAO.joinGeneral(memberDTO, generalDTO);

        Result result = new Result();
        result.setRedirect(true);
        result.setPath(request.getContextPath());
        return result;
    }
}