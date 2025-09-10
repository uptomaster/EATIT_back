package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ItemInsertDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FoodEditOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("FoodEditOkController 접근 성공 ===");
		
		request.setCharacterEncoding("UTF-8");
		
		Result result = new Result();
		
		System.out.println("request 요청");
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
		    String name = parameterNames.nextElement();
		    String value = request.getParameter(name);
		    System.out.println("param: " + name + " = " + value);
		}
		System.out.println("=== Request Parameters ===");
		request.getParameterMap().forEach((k, v) -> {
		    System.out.println(k + " : " + Arrays.toString(v));
		});
		// 이미지 file
		ItemImageDAO ItemImageDAO = new ItemImageDAO();
		ItemImageDTO ItemImageDTO = new ItemImageDTO();
		// 메뉴 정보 관련
		ItemInsertDTO itemInsertDTO = new ItemInsertDTO();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		
		//1) 세션 정보 확인하기
//		System.out.println(""+multipartRequest.get);
		//로그인 한 회원 정보 가져오기
		Integer memberNumber = (Integer)request.getSession().getAttribute("memberNumber");
		String businessNumber = (String)request.getSession().getAttribute("businessNumber");
		System.out.println("회원번호 : "+memberNumber +"  사업자번호 : "+businessNumber);
		
		if(memberNumber == null) {
			System.out.println("오류 : 로그인된 사용자가 없습니다");
			response.sendRedirect("login.jsp");
			return null;
		}
		
		

		
		// 2) 파일 업로드 환경 설정
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; //5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);
				
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8", new DefaultFileRenamePolicy());

		
		// 3) 파라미터 수집 및 DTO 설정
		int itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
		System.out.println(itemNumber);
		
		
		// 게시글 정보 설정
		itemInsertDTO.setBusinessNumber(businessNumber); // String
		itemInsertDTO.setItemType("FOOD");
		itemInsertDTO.setItemNumber(itemNumber);

		itemInsertDTO.setItemName(multipartRequest.getParameter("itemName")); //String
		System.out.println("itemName  "+multipartRequest.getParameter("itemName"));
		
        int price = Integer.parseInt(multipartRequest.getParameter("itemPrice"));
//        System.out.println(price+1); // 타입 확인용 출력문
        itemInsertDTO.setItemPrice(price); //int
        
        itemInsertDTO.setItemContent(multipartRequest.getParameter("itemContent")); //String
        
		int quantity = Integer.parseInt(multipartRequest.getParameter("itemQuantity")); //int
//		System.out.println(quantity+1); // 타입 확인용 출력문
		itemInsertDTO.setItemQuantity(quantity);
		
//		itemInsertDTO.setItemOrigin(multipartRequest.getParameter("itemOrigin")); //String
		
		itemInsertDTO.setItemExpireDate(multipartRequest.getParameter("itemExpireDate")); //String
		
        String sellStateStr = multipartRequest.getParameter("itemSellState").trim(); // String 
        itemInsertDTO.setItemSellState(sellStateStr);
//        System.out.println(sellStateStr);
        
		// 게시글 추가
        sellerDAO.editFood(itemInsertDTO); // 음식 정보 등록 + 등록한 아이템 번호 가져오기
		
		
		
		// 파일 업로드 처리
		// Enumeration : java.util 패키지에 포함된 인터페이스, Iterator와 비슷한 역할함
		Enumeration<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasMoreElements()) {
			String name = fileNames.nextElement();
			String fileSystemName = multipartRequest.getFilesystemName(name);
			String fileOriginalName = multipartRequest.getOriginalFileName(name);

			// 파일을 선택하지 않은 경우 -> 다음 반복으로 이동
			if (fileSystemName == null) {
				continue;
			}

			ItemImageDTO.setItemNumber(itemNumber);
			ItemImageDTO.setItemImageSystemName(fileSystemName);
			ItemImageDTO.setItemImageOriginalName(fileOriginalName);

			System.out.println("업로드 된 파일 정보 : " + ItemImageDTO);
			
			ItemImageDAO.delete(itemNumber);
			ItemImageDAO.insert(ItemImageDTO);
		}
		
		//
		System.out.println(itemInsertDTO);
		 result.setRedirect(false);
		 String path = "/sellerMyPage/storeInfo.se";
		 System.out.println("[FoodAddOkController] 지정한 path : "+path);
		 result.setPath(path);
			return result;	
    }
}