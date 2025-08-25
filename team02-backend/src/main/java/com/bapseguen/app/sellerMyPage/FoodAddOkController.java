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
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.item.dao.ItemDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FoodAddOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ItemDAO ItemDAO = new ItemDAO();
		ItemDTO ItemDTO = new ItemDTO();
		Result result = new Result();
		ItemImageDAO ItemImageDAO = new ItemImageDAO();
		ItemImageDTO ItemImageDTO = new ItemImageDTO();
		ItemWithImgDTO itemWithImgDTO = new ItemWithImgDTO();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		
		
		//파일 업로드 환경 설정
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; //5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);
		
		//MultipartRequest를 이용한 데이터 파싱
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8", new DefaultFileRenamePolicy());
		//request : HTTP 요청객체
		//UPLOAD_PATH : 파일을 저장할 경로
		//FILE_SIZE : 파일의 최대 크기
		//"utf-8" : 파일명 인코딩 방식
		//new DefaultFileRenamePolicy() : 파일명이 중복될 경우 자동으로 이름 변경해주는 정책

		
		//로그인 한 회원 정보 가져오기
		Integer memberNumber = (Integer)request.getSession().getAttribute("memberNumber");
		
		if(memberNumber == null) {
			System.out.println("오류 : 로그인된 사용자가 없습니다");
			response.sendRedirect("login.jsp");
			return null;
		}


		// 게시글 정보 설정
		itemWithImgDTO.setItemType(multipartRequest.getParameter("itemType"));
        itemWithImgDTO.setItemName(multipartRequest.getParameter("itemName"));
        itemWithImgDTO.setItemPrice(multipartRequest.getParameter("itemPrice"));
        itemWithImgDTO.setItemContent(multipartRequest.getParameter("itemContent"));
        itemWithImgDTO.setItemQuantity(Integer.parseInt(multipartRequest.getParameter("itemQuantity")));
        itemWithImgDTO.setItemExpireDate(multipartRequest.getParameter("itemExpireDate"));
        itemWithImgDTO.setBusinessNumber(multipartRequest.getParameter("businessNumber"));
        itemWithImgDTO.setItemCreatedTime("sysdate");
        itemWithImgDTO.setItemUpdatedTime("sysdate");
        itemWithImgDTO.setItemSellState(true);
        
		// 게시글 추가
        int itemNumber = sellerDAO.addFood(itemWithImgDTO); // 음식 정보 등록 + 등록한 아이템 번호 가져오기
        sellerDAO.addItemImage(itemWithImgDTO); // 음식 사진 등록
		System.out.println("생성된 게시글 번호 : " + itemNumber);

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

			ItemImageDTO.setItemImageSystemName(fileSystemName);
			ItemImageDTO.setItemImageOriginalName(fileOriginalName);
			ItemImageDTO.setItemImageNumber(itemNumber);

			System.out.println("업로드 된 파일 정보 : " + ItemImageDTO);
			ItemImageDAO.insert(ItemImageDTO);
		}
		 result.setRedirect(false);
			result.setPath("/app/sellerMyPage/foodSalesWrite.jsp");
			return result;	
	}

}
