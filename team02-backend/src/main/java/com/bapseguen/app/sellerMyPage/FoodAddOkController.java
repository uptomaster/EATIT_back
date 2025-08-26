package com.bapseguen.app.sellerMyPage;

import java.io.File;
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
		System.out.println("FoodAddOkController 접근 성공 ===");
		
		Result result = new Result();
		// 이미지 file
		ItemImageDAO ItemImageDAO = new ItemImageDAO();
		ItemImageDTO ItemImageDTO = new ItemImageDTO();
		// 메뉴 정보 관련
		ItemWithImgDTO itemWithImgDTO = new ItemWithImgDTO();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		
		
		//파일 업로드 환경 설정
		// 업로드 저장 경로 : webapp 루트 하위 "upload" 폴더에 저장
		// 배포/재배푓 지워질 수 있어 실제 서비스에서는 외부 경로 사용 권장
		// 
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; //멀티 파트 요청의 최대 바이트 5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);
		
		//MultipartRequest를 이용한 데이터 파싱 왜? 
		// multipartRequest 생성 : 요청을 파트단위로 순회하며 텍스트, 파일 을 분리
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8", new DefaultFileRenamePolicy());
		//request : HTTP 요청객체
		//UPLOAD_PATH : 파일을 저장할 경로
		//FILE_SIZE : 파일의 최대 크기
		//"utf-8" : 파일명 인코딩 방식
		//new DefaultFileRenamePolicy() : 파일명이 중복될 경우 자동으로 이름 변경해주는 정책
		
		 // 업로드 경로가 없으면 생성(없을 경우 파일 저장 실패 방지)
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                // 디렉터리 생성 실패 시 즉시 오류 처리 가능
                throw new ServletException("업로드 디렉터리 생성 실패: " + UPLOAD_PATH);
            }
        }
        
//     // 개발 환경일 때만 WTP 경로 사용
//        if (request.getServletContext().getRealPath("/").contains("wtpwebapps")) {
//            final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
//            
//            // 디렉터리가 존재하지 않으면 생성
//            File uploadDir = new File(UPLOAD_PATH);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//        }
		
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

		
		//
		// 게시글 정보 설정
		itemWithImgDTO.setBusinessNumber(multipartRequest.getParameter("businessNumber"));
		itemWithImgDTO.setItemType(multipartRequest.getParameter("itemType"));
        itemWithImgDTO.setItemName(multipartRequest.getParameter("itemName"));
        itemWithImgDTO.setItemPrice(multipartRequest.getParameter("itemPrice"));
        itemWithImgDTO.setItemContent(multipartRequest.getParameter("itemContent"));
        itemWithImgDTO.setItemQuantity(Integer.parseInt(multipartRequest.getParameter("itemQuantity")));
        itemWithImgDTO.setItemOrigin(multipartRequest.getParameter("itemOrigin"));
        itemWithImgDTO.setItemExpireDate(multipartRequest.getParameter("itemExpireDate"));
        itemWithImgDTO.setItemCreatedTime("sysdate");
        itemWithImgDTO.setItemUpdatedTime("sysdate");
        String sellStateStr = multipartRequest.getParameter("itemSellState"); // String boolean, 
        itemWithImgDTO.setItemSellState(sellStateStr.equals("Y") ? true : false );
        
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

			// 파일을 선택하지 않은 경우 -> 다음 반복으로 이동
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
		 String path = "/app/sellerMyPage/foodSalesWriteOk.se";
		 System.out.println("[FoodAddOkController] 지정한 path : "+path);
		 result.setPath(path);
			return result;	
	}

}
