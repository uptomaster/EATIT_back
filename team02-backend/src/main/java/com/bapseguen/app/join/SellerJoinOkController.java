package com.bapseguen.app.join;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.dto.SellerMemberDTO;
import com.bapseguen.app.dto.StoreDTO;
import com.bapseguen.app.join.dao.JoinDAO;

public class SellerJoinOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(request.getParameter("seller_input_id"));
        memberDTO.setMemberPassword(request.getParameter("seller_input_pw"));
        memberDTO.setMemberType("SELLER");

        SellerMemberDTO sellerDTO = new SellerMemberDTO();
        sellerDTO.setSellerName(request.getParameter("seller_input_name"));
        sellerDTO.setSellerBirthdate(request.getParameter("seller_input_birth")); 
        sellerDTO.setSellerPhoneNumber(request.getParameter("seller_input_phone"));

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setBusinessNumber(request.getParameter("seller_input_store_number"));
        storeDTO.setStoreName(request.getParameter("seller_input_store_name"));
        storeDTO.setStoreOpenDate(request.getParameter("seller_input_store_open_date")); 
        storeDTO.setStoreTel(request.getParameter("seller_input_store_call"));
        storeDTO.setStoreAddress(request.getParameter("seller_input_store_address"));
        storeDTO.setStoreAddressDetail(request.getParameter("seller_input_store_address_detail"));
        storeDTO.setStoreZipCode(request.getParameter("seller_input_store_zip"));

        new JoinDAO().joinSeller(memberDTO, sellerDTO, storeDTO);

        Result result = new Result();
        result.setRedirect(true);
        result.setPath(request.getContextPath()+ "/join/successJoin.jo");
        return result;
    }
}