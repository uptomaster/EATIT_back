package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.dto.SellerMemberDTO;
import com.bapseguen.app.dto.StoreDTO;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.join.dao.JoinDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class EditSellerInfoOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===EditSellerInfoOkController 접근 성공===");
		
	
		request.setCharacterEncoding("UTF-8");
        Result result = new Result();

		//1. 세션에서 memberNumber 가져오기
		HttpSession session = request.getSession();
		int memberNumber = (int) session.getAttribute("memberNumber");
		
		String businessNumber = (String) session.getAttribute("businessNumber");
		System.out.println("member  "+memberNumber+"businees  "+businessNumber);
		//사용한 값 DTO에 넣기
		//memberDTO
		MemberDTO memberDTO = new MemberDTO();
		String password = request.getParameter("newPassword");
		memberDTO.setMemberNumber(memberNumber);
        memberDTO.setMemberPassword(password);
        System.out.println("password"+password);
        
        memberDTO.setMemberType("SELLER");
        
		//sellerDTO
        SellerMemberDTO sellerDTO = new SellerMemberDTO();
        sellerDTO.setSellerName(request.getParameter("sellerName"));
        String phoneNumber = request.getParameter("newPhone");
        String sellerPhoneNumber = request.getParameter("sellerPhoneNumber");
        System.out.println("phoneNumber  "+phoneNumber+"  sellerPhoneNumber  "+sellerPhoneNumber);
        sellerDTO.setSellerPhoneNumber(phoneNumber);
        sellerDTO.setSellerUpdatedDate("SYSDATE");
        
		//storeDTO
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setBusinessNumber(businessNumber);
        storeDTO.setStoreOpenTime(request.getParameter("storeOpenTime"));
        storeDTO.setStoreCloseTime(request.getParameter("storeCloseTime"));
        System.out.println(storeDTO);
//        storeDTO.setStoreAddress(request.getParameter("seller_input_store_address"));
//        storeDTO.setStoreAddressDetail(request.getParameter("seller_input_store_address_detail"));
//        storeDTO.setStoreZipCode(request.getParameter("seller_input_store_zip"));
//        double longitude = Double.parseDouble(request.getParameter("seller_input_store_zip"));
//        storeDTO.setLongitude(longitude);
//        double latitude = Double.parseDouble(request.getParameter("seller_input_store_zip"));
//        storeDTO.setLatitude(latitude);
        
		//DAO로 쿼리문 실행시키기
		SellerMyPageDAO sellerdao = new SellerMyPageDAO();
		sellerdao.updatePassword(memberNumber,password );
		sellerdao.updatePhone(memberNumber,phoneNumber );
//		sellerdao.updateStoreaddress(storeDTO);
		sellerdao.updateStoreTime(storeDTO);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('수정이 완료되었습니다.'); location.href='" 
		            + request.getContextPath() + "/sellerMyPage/editSellerInfo.se';</script>");
		out.close();

		
		//결과 완료후 /sellerMyPage/editSellerInfo.se 로 이동하기
		result.setPath("/sellerMyPage/editSellerInfo.se");
		result.setRedirect(true);
		return result;

	}

}
