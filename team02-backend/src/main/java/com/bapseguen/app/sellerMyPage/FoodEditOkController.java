package com.bapseguen.app.sellerMyPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.ItemListDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class FoodEditOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 

        Result result = new Result();
        ItemListDTO ItemListDTO = new ItemListDTO(); //boardDTO
        SellerMyPageDAO sellerDAO = new SellerMyPageDAO(); //boardDAO
 		ItemImageDAO fileDAO = new ItemImageDAO(); //fileDAO
 		ItemImageDTO fileDTO = new ItemImageDTO(); //fileDTO

    	
 		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
        final int FILE_SIZE = 1024 * 1024 * 5; 

        // MultipartParser 실행
        MultipartParser parser = new MultipartParser(request, FILE_SIZE);
        parser.setEncoding("utf-8");
        System.out.println("MultipartParser 초기화 완료");

        int itemNumber = (int) request.getAttribute("itemNumber");
        System.out.println("[FoodEditOkController] itemNumber : "+itemNumber);
        boolean isFileUpload = false;

        // 파일, 텍스트 데이터 처리
        Part part;
        while ((part = parser.readNextPart()) != null) {
            System.out.println("Part: " + part.getClass().getSimpleName());

            if (part.isParam()) {
                // 텍스트 파라미터 처리
                ParamPart paramPart = (ParamPart) part;
                String paramName = paramPart.getName();
                String paramValue = paramPart.getStringValue();

                System.out.println("파라미터: " + paramName + " = " + paramValue);
                
                // 내용 수정 파트
                if ("itemNumber".equals(paramName)) {
                	// 세션에서 가져온 값이 null일 수 있으므로 null 체크
                    String sessionItemNumber = (String) request.getSession().getAttribute("ItemNumber");
                    if (sessionItemNumber != null) {
                        itemNumber = Integer.parseInt(sessionItemNumber);
                    }
                    ItemListDTO.setItemNumber(itemNumber);
                    
                } else if ("itemName".equals(paramName)) {
                	// 상품명 수정
                	ItemListDTO.setItemName(paramValue);
                } else if ("itemPrice".equals(paramName)) {
                	// 가격 수정
                	 try {
                	        double price = Double.parseDouble(paramValue);
                	        if (price < 0) {
                	            System.out.println("가격은 0 이상이어야 합니다.");
                	            ItemListDTO.setItemPrice("0");
                	        } else {
                	            ItemListDTO.setItemPrice(paramValue);
                	        }
                	    } catch (NumberFormatException e) {
                	        System.out.println("가격 형식 오류: " + paramValue);
                	        ItemListDTO.setItemPrice("0");
                	    }
            	} else if ("itemContent".equals(paramName)) {
                	// 상품 설명 수정
                	ItemListDTO.setItemContent(paramValue);
                } else if ("itemQuantity".equals(paramName)) {
                	// 수량 변환 시 NumberFormatException 처리
                    try {
                        int itemQuantity = Integer.parseInt(paramValue);
                        ItemListDTO.setItemQuantity(itemQuantity);
                    } catch (NumberFormatException e) {
                        System.out.println("수량 변환 오류: " + paramValue);
                        ItemListDTO.setItemQuantity(0); // 기본값 설정
                    }
                } else if ("itemExpireDate".equals(paramName)) {
                	// 소비기한 수정
                	ItemListDTO.setItemExpireDate(paramValue);
                } else if ("itemOrigin".equals(paramName)) {
                	// 소비기한 수정
                	ItemListDTO.setItemOrigin(paramValue);
                } else if ("itemSellState".equals(paramName)) {
                	// 판매상태 수정
//                	 // 'Y'/'N' 변환
                    ItemListDTO.setItemSellState("Y".equals(paramValue) ? "Y" : "N");
                } 
            } else if (part.isFile() && !isFileUpload) {
            	//파일 이미지 처리 ( 게시글 별 하나의 파일만 존재할 수 있음)
                FilePart filePart = (FilePart) part;
                filePart.setRenamePolicy(new DefaultFileRenamePolicy());
                String fileOriginalName = filePart.getFileName();
                
                // 기존 파일 삭제
                if (itemNumber != 0) {
                    List<ItemImageDTO> existingFiles = fileDAO.select(itemNumber);
                    for (ItemImageDTO file : existingFiles) {
                        File oldFile = new File(UPLOAD_PATH, file.getItemImageSystemName());
                        if (oldFile.exists()) {
                            System.out.println("기존 파일 삭제: " + oldFile.getAbsolutePath());
                            oldFile.delete();
                        }
                    }
                    fileDAO.delete(itemNumber);
                    System.out.println("기존 파일 DB 삭제 완료");
                }

                if (fileOriginalName != null) {
                    String newFileName = System.currentTimeMillis() + "_" + fileOriginalName;
                    File newFile = new File(UPLOAD_PATH, newFileName);
                    filePart.writeTo(newFile);

                    if (newFile.exists()) {
                        System.out.println("새로운 파일 저장 완료: " + newFile.getAbsolutePath());
                    } else {
                        System.out.println("새로운 파일 저장 실패: " + newFile.getAbsolutePath());
                    }

                    // DB 저장
                    ItemImageDTO itemImageDTO = new ItemImageDTO();
                    itemImageDTO.setItemImageSystemName(newFileName);
                    itemImageDTO.setItemImageOriginalName(fileOriginalName);
                    itemImageDTO.setItemImageNumber(itemNumber);
                    fileDAO.insert(itemImageDTO);
                    System.out.println("새로운 파일 DB 저장 완료: " + itemImageDTO);

                    isFileUpload = true; // 파일s이 업로드되었음을 표시
                } else {
                    System.out.println("업로드된 파일이 없습니다 (파일 선택하지 않음)");
                }
            }
        }

        // 게시글 업데이트 실행
        ItemListDTO.setItemNumber((Integer) request.getSession().getAttribute("ItemNumber"));
        sellerDAO.editFood(ItemListDTO);
        System.out.println("게시글 수정 완료");

        //수정 완료 후 리스트 페이지로 이동
        result.setPath("/sellerMyPage/storeInfo.se");
        result.setRedirect(true);
        return result;
    }
}