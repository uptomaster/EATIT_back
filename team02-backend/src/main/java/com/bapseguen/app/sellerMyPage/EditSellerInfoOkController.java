package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class EditSellerInfoOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===EditSellerInfoOkController 접근 성공===");
		
		Result result = new Result();
        SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
        SellerInfoDTO dto = new SellerInfoDTO();
        int updateCount = 0;

		HttpSession session = request.getSession();
		int memberNumber = (int) session.getAttribute("memberNumber");
		String newPassword = request.getParameter("newPassword");
		String newPhone = request.getParameter("newPhone");
		
		System.out.println("memberNumber: " + memberNumber);
		System.out.println("newPassword: " + newPassword);
		System.out.println("newPhone: " + newPhone);
        
        dto.setMemberNumber(Integer.parseInt(request.getParameter("memberNumber")));
        dto.setMemberPassword(request.getParameter("memberPassword"));
        dto.setSellerName(request.getParameter("sellerName"));
        dto.setSellerPhoneNumber(request.getParameter("sellerPhoneNumber"));
        dto.setStoreName(request.getParameter("storeName"));
        dto.setStoreTel(request.getParameter("storeTel"));
        dto.setStoreAddress(request.getParameter("storeAddress"));
        dto.setStoreAddressDetail(request.getParameter("storeAddressDetail"));
        dto.setStoreZipCode(request.getParameter("storeZipCode"));
        dto.setStoreOpenDate(request.getParameter("storeOpenDate"));
        dto.setStoreOpenTime(request.getParameter("storeOpenTime"));
        dto.setStoreCloseTime(request.getParameter("storeCloseTime"));
        double storeLongitude = Double.parseDouble(request.getParameter("storeLongitude"));
        dto.setStoreLongitude(storeLongitude);
        double storeLatitude = Double.parseDouble(request.getParameter("storeLatitude"));
        dto.setStoreLatitude(storeLatitude);

        sellerDAO.updateSellerInfo(dto);
        
		// 파라미터 Map 생성
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberNumber", memberNumber);

		if (newPassword != null && !newPassword.isEmpty() && !"null".equals(newPassword)) {
		    paramMap.put("memberPassword", newPassword);
		    updateCount += sellerDAO.updatePassword(paramMap);
		     ;
		}

		if (newPhone != null && !newPhone.isEmpty() && !"null".equals(newPhone)) {
		    paramMap.put("memberPhoneNumber", newPhone);
		    updateCount += sellerDAO.updatePhone(paramMap);
		}
		
		

		// JSON 응답
		response.setContentType("application/json; charset=UTF-8");
		String jsonResult = updateCount > 0 ? "{\"status\":\"success\"}" : "{\"status\":\"fail\"}";
		response.getWriter().write(jsonResult);
        result.setPath("/sellerMyPage/editSellerInfo.se");
        result.setRedirect(true);
        return result;

	}

}
