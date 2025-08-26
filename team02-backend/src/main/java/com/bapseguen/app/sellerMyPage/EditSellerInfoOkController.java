package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        dto.setMemberNumber(Integer.parseInt(request.getParameter("memberNumber")));
        dto.setMemberPassword(request.getParameter("memberPassword"));
        dto.setSellerName(request.getParameter("sellerName"));
        dto.setSellerPhoneNumber(request.getParameter("sellerPhoneNumber"));
        dto.setStoreName(request.getParameter("storeName"));
        dto.setStoreTel(request.getParameter("storeTel"));
        dto.setStoreAddress(request.getParameter("storeAddress"));
        dto.setStoreAddressDetail(request.getParameter("storeAddressDetail"));
        dto.setStoreZipCode(request.getParameter("storeZipCode"));
        dto.setStoreOpenTime(request.getParameter("storeOpenTime"));
        dto.setStoreCloseTime(request.getParameter("storeCloseTime"));

        sellerDAO.updateSellerInfo(dto);

        result.setPath("/sellerMyPage/editSellerInfo.se");
        result.setRedirect(true);
        return result;

	}

}
