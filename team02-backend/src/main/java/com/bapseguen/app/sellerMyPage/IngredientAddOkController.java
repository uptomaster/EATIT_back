package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class IngredientAddOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		ItemDTO itemDTO = new ItemDTO();
		ItemImageDTO itemImageDTO = new ItemImageDTO();
		ItemWithImgDTO itemWithImgDTO = new ItemWithImgDTO();
		SellerMyPageDAO dao = new SellerMyPageDAO();
		Result result = new Result();

		// 로그인 한 회원 정보 가져오기
		Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		String memberType = (String) request.getSession().getAttribute("memberType");

		if (memberNumber == null) {
			System.out.println("오류 : 로그인된 사용자가 없습니다");
			response.sendRedirect("/app/login/login.jsp");
			return null;
		}

		// 파일 업로드 환경 설정
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; // 5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);

		// MultipartRequest를 이용한 데이터 파싱
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8",
				new DefaultFileRenamePolicy());

		itemDTO.setBusinessNumber(request.getParameter("businessNumber"));
		itemDTO.setItemName(request.getParameter("itemName"));
		itemDTO.setItemPrice(Integer.parseInt(request.getParameter("itemPrice")));
		itemDTO.setItemContent(request.getParameter("itemContent"));
		itemDTO.setItemQuantity(Integer.parseInt(request.getParameter("itemQuantity")));
		itemDTO.setItemOrigin(request.getParameter("itemOrigin"));
		itemDTO.setItemExpireDate(request.getParameter("itemExpireDate"));

		// 판매 등록


		// 파일 업로드 처리
		// Enumeration : java.util 패키지에 포함된 인터페이스, Iterator와 비슷한 역할함
		Enumeration<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasMoreElements()) {
			String name = fileNames.nextElement();
			String fileSystemName = multipartRequest.getFilesystemName(name);
			String fileOriginalName = multipartRequest.getOriginalFileName(name);

			if (fileSystemName == null) {
				continue;
			}

			itemImageDTO.setItemImageSystemName(fileSystemName);
			itemImageDTO.setItemImageOriginalName(fileOriginalName);
			itemImageDTO.setItemNumber(itemNumber);

			System.out.println("업로드 된 파일 정보 : " + itemImageDTO);
			itemImageDTO.insert(itemImagdto);
		}

		result.setRedirect(true);
			result.setPath("/app/sellerMyPage/foodSalesWrite.jsp");
			return result;	
	}
}