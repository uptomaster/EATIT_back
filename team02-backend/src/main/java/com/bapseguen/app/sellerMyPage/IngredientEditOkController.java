package com.bapseguen.app.sellerMyPage;

import java.io.File;
import java.io.IOException;
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

public class IngredientEditOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== IngredientEditOkController 접근 성공 ===");
		
		request.setCharacterEncoding("UTF-8");
		
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
    	final int FILE_SIZE = 1024 * 1024 * 5; //5MB
    	Result result = new Result();
    	// 이미지 file
		ItemImageDAO itemImageDAO = new ItemImageDAO();
		ItemImageDTO itemImageDTO = new ItemImageDTO();
    	// 메뉴 정보 관련
		ItemInsertDTO itemInsertDTO = new ItemInsertDTO();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		
    	// 1) Multipart 파서 생성 (이미 있으니 위치만 앞쪽으로)
    	MultipartRequest multipartRequest = new MultipartRequest(
    	    request,
    	    UPLOAD_PATH,         // 예: getRealPath("/")+ "upload/"
    	    FILE_SIZE,           // 예: 5 * 1024 * 1024
    	    "utf-8",
    	    new DefaultFileRenamePolicy()
    	);

    	// 2) 디버그는 request가 아니라 multipartRequest로!
    	Enumeration<Object> pnames = multipartRequest.getParameterNames();
    	System.out.println("=== Multipart Parameters ===");
    	while (pnames.hasMoreElements()) {
    		System.out.println("pname is ");
    	    String n = (String) pnames.nextElement();
    	    System.out.println(n + " = " + multipartRequest.getParameter(n));
    	}

    	// 3) 값 읽기
    	String itemNumberStr   = multipartRequest.getParameter("itemNumber");
    	String itemName        = multipartRequest.getParameter("itemName");
    	String itemPriceStr    = multipartRequest.getParameter("itemPrice");
    	String itemContent     = multipartRequest.getParameter("itemContent");
    	String itemQtyStr      = multipartRequest.getParameter("itemQuantity");
    	String expireDate      = multipartRequest.getParameter("itemExpireDate");
    	String sellState       = multipartRequest.getParameter("itemSellState"); // null 가능

    	int itemNumber = Integer.parseInt(itemNumberStr);
    	int itemPrice  = Integer.parseInt(itemPriceStr);
    	int itemQty    = Integer.parseInt(itemQtyStr);

    	// 4) NPE 방지
    	if (sellState == null) sellState = "N"; // 혹은 기본값 로직

    	// 5) DTO 매핑
    	itemInsertDTO.setItemNumber(itemNumber);
    	itemInsertDTO.setItemName(itemName);
    	itemInsertDTO.setItemPrice(itemPrice);
    	itemInsertDTO.setItemContent(itemContent.trim());
    	itemInsertDTO.setItemQuantity(itemQty);
    	itemInsertDTO.setItemExpireDate(expireDate);
    	itemInsertDTO.setItemSellState(sellState.trim());
    	sellerDAO.editIngredient(itemInsertDTO); // 음식 정보 등록 + 등록한 아이템 번호 가져오기

    	// 6) 파일 처리 (있을 때만)
    	File uploaded = multipartRequest.getFile("itemImage");
    	if (uploaded != null) {
    	    String fileSystemName   = multipartRequest.getFilesystemName("itemImage");
    	    String fileOriginalName = multipartRequest.getOriginalFileName("itemImage");

    	    itemImageDTO.setItemNumber(itemNumber);
    	    itemImageDTO.setItemImageSystemName(fileSystemName);
    	    itemImageDTO.setItemImageOriginalName(fileOriginalName);

    	    itemImageDAO.delete(itemNumber);
    	    itemImageDAO.insert(itemImageDTO);
    	}
    	
    	System.out.println(itemInsertDTO);
		 result.setRedirect(false);
		 String path = "/sellerMyPage/storeIngre.se";
		 System.out.println("[IngredientEditOkController] 지정한 path : "+path);
		 result.setPath(path);
			return result;	
	}
	
}
