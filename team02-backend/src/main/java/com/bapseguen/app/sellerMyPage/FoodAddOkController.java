package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FoodAddOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Result result = new Result();
		ItemDTO itemDTO = new ItemDTO();
		SellerMyPageDAO SellerMyPageDAO = new SellerMyPageDAO();
		System.out.println("FoodAddOkController 진입 성공");

		// 음식 정보
		itemDTO.setBusinessNumber(request.getParameter("businessNumber"));
		itemDTO.setItemName(request.getParameter("itemName"));
		itemDTO.setItemPrice(Integer.parseInt(request.getParameter("itemPrice")));
		itemDTO.setItemContent(request.getParameter("itemContent"));
		itemDTO.setItemQuantity(Integer.parseInt(request.getParameter("itemQuantity")));
		itemDTO.setItemOrigin(request.getParameter("itemOrigin"));
		itemDTO.setItemExpireDate(request.getParameter("itemExpireDate"));

		// // 음식 사진 등록
		// 파일 업로드 환경 설정
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; // 5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);

		// MultipartRequest를 이용한 데이터 파싱
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8",
				new DefaultFileRenamePolicy());
	System.out.println("FoodAddOkController 진입 성공");
		// request : HTTP 요청객체
		// UPLOAD_PATH : 파일을 저장할 경로
		// FILE_SIZE : 파일의 최대 크기
		// "utf-8" : 파일명 인코딩 방식
		// new DefaultFileRenamePolicy() : 파일명이 중복될 경우 자동으로 이름 변경해주는 정책

		// 게시글 추가
		int boardNumber = itemDAO.insertBoard(itemDTO);
		System.out.println("생성된 게시글 번호 : " + boardNumber);

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

			fileDTO.setFileSystemName(fileSystemName);
			fileDTO.setFileOriginalName(fileOriginalName);
			fileDTO.setBoardNumber(boardNumber);

			System.out.println("업로드 된 파일 정보 : " + fileDTO);
			fileDAO.insert(fileDTO);
		}

//        new StoreManageDAO().addFood(itemDTO);
		SellerMyPageDAO.addFood(itemDTO);

		result.setRedirect(true);
		result.setPath("/seller/food/listOk.se?businessNumber=" + itemDTO.getBusinessNumber());
		return result;
	}

}
	