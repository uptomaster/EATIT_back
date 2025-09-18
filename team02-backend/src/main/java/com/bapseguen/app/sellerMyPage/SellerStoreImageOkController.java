package com.bapseguen.app.sellerMyPage;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.StoreImageDTO;
import com.bapseguen.app.img.dao.StoreImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class SellerStoreImageOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Result result = new Result();

	    // 1) 세션/파라미터
	    HttpSession session = request.getSession(false);
	    Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;
	    String businessNumber = (session != null) ? (String) session.getAttribute("businessNumber") : null;

	    // multipart 파싱
	    String uploadPath = request.getServletContext().getRealPath("/upload");
		File dir = new File(uploadPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	    int maxSize = 10 * 1024 * 1024; // 10MB
	    MultipartRequest multi = new MultipartRequest(request, uploadPath, 10 * 1024 * 1024, // 10MB
				"UTF-8", new DefaultFileRenamePolicy());

	    // 3) DAO/Service 호출 
        SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
        StoreImageDAO imageDAO = new StoreImageDAO(); // 혹은 SellerMyPageDAO 내부에 이미지 관련 메서드 추가
        
	    // 2) 업로드 파일 획득
	    File uploaded = multi.getFile("storeImage");
	    String orgName = (uploaded != null) ? multi.getOriginalFileName("storeImage") : null;
	    String sysName = (uploaded != null) ? uploaded.getName() : null;

        // (A) 기존 이미지 조회
        StoreImageDTO old = imageDAO.selectone(businessNumber);
        System.out.println("oldFile : "+old);
        
        // (B) 기존 이미지 DB 삭제
        if (old != null) {
            imageDAO.delete(businessNumber);

			/*
			 * // (선택) 기존 실제 파일 삭제 if (old.getStoreImageSystemName() != null) { File oldFile
			 * = new File(dir, old.getStoreImageSystemName());
			 *  if (oldFile.exists())
			 * oldFile.delete(); }
			 */
        }

        // 새 이미지 업로드
 		Enumeration<?> files = multi.getFileNames();
 		while (files.hasMoreElements()) {
 			String paramName = (String) files.nextElement();
 			String systemName = multi.getFilesystemName(paramName);
 			String originalName = multi.getOriginalFileName(paramName);

 			if (systemName != null) { // 업로드된 파일이 있을 때만
 				StoreImageDTO imageDTO = new StoreImageDTO();
 				imageDTO.setBusinessNumber(businessNumber);
 				imageDTO.setStoreImageSystemName(systemName);
 				imageDTO.setStoreImageOriginalName(originalName);
 				System.out.println(imageDTO);
 				
 				imageDAO.insert(imageDTO);
 			}
 		}

	    // 4) 완료 후 .se 경로로 복귀 (MVC2 규칙)
	    result.setPath(request.getContextPath() + "/sellerMyPage/storeInfo.se");
	    result.setRedirect(true);
	    return result;

	}
	
}
