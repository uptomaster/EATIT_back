package com.bapseguen.app.sellerMyPage;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
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

	    // -------------------------------
	    // 0) 공통 준비
	    // -------------------------------
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json; charset=UTF-8");

	    // JSON 응답 기본값
	    String json = "{\"result\":\"fail\"}";

	    // 세션에서 사업자번호 우선 취득
	    HttpSession session = request.getSession(false);
	    String businessNumber = null;
	    if (session != null) {
	      Object bn = session.getAttribute("businessNumber");
	      if (bn != null) { businessNumber = String.valueOf(bn); }
	    }
	    // 폼 파라미터로도 넘어올 수 있으므로 보조로 체크(세션이 우선)
	    // (multipart이므로 아래 MultipartRequest에서 다시 꺼낼 수도 있음)
	    if (businessNumber == null) {
	      businessNumber = request.getParameter("businessNumber");
	    }

	    // 사업자번호 없으면 업로드 진행 불가
	    if (businessNumber == null || businessNumber.isBlank()) {
	      response.getWriter().write(json);
	      return null;
	    }

	    // -------------------------------
	    // 1) 업로드 경로/환경 설정
	    // -------------------------------
	    ServletContext app = request.getServletContext();

	    // /upload 실제 경로 (배포 환경에 맞춰 조정 가능)
	    String uploadPath = app.getRealPath("upload");
	    if (uploadPath == null) {
	      // 일부 서버에서 getRealPath가 null을 줄 수 있으므로 방어
	      uploadPath = app.getRealPath("/") + "upload";
	    }

	    // 업로드 폴더 자동 생성 (존재하지 않을 경우)
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	      boolean made = uploadDir.mkdirs();
	      // 생성 실패하더라도 아래에서 예외로 처리되므로 로그 정도만
	      // System.out.println("업로드 폴더 생성: " + made + " -> " + uploadPath);
	    }

	    // 업로드 제한(10MB 권장), 파일명 중복정책
	    int maxPostSize = 10 * 1024 * 1024; // 10MB
	    String encType = "UTF-8";
	    DefaultFileRenamePolicy rename = new DefaultFileRenamePolicy();

	    // -------------------------------
	    // 2) Multipart 파싱 (cos)
	    // -------------------------------
	    MultipartRequest multipart = null;
	    try {
	      multipart = new MultipartRequest(request, uploadPath, maxPostSize, encType, rename);
	    } catch (IllegalArgumentException iae) {
	      // "Not a directory" 같은 오류의 근본 원인: uploadPath가 디렉토리가 아닐 때 발생
	      // 위에서 mkdirs로 방어했지만, 혹시 권한 문제/경로 이상이면 여기로 옴
	      response.getWriter().write(json);
	      return null;
	    } catch (Exception e) {
	      e.printStackTrace();
	      response.getWriter().write(json);
	      return null;
	    }

	    // multipart 내부 파라미터에서 businessNumber가 오버라이드 되었는지 보조 확인
	    if (multipart.getParameter("businessNumber") != null) {
	      businessNumber = multipart.getParameter("businessNumber");
	    }
	    if (businessNumber == null || businessNumber.isBlank()) {
	      response.getWriter().write(json);
	      return null;
	    }

	    // -------------------------------
	    // 3) 파일 필드명 확인 후 파일 정보 추출
	    //    (JSP에서 name="storeImage" 를 사용한다고 가정)
	    // -------------------------------
	    String fileFieldName = null;
	    String savedSystemName = null;   // 서버에 저장된(변경될 수 있는) 파일명
	    String originalName = null;      // 사용자가 올린 원본 파일명

	    // 기존 스크립트/마크업을 유지하기 위해 "삭제하지 말고 주석처리" 원칙 준수
	    // name이 바뀌지 않았다는 전제에서 storeImage를 우선 시도하고,
	    // 아니면 multipart의 파일 필드 열거로 보조 처리
	    try {
	      // 1순위: 우리가 합의한 name
	      savedSystemName = multipart.getFilesystemName("storeImage");
	      originalName    = multipart.getOriginalFileName("storeImage");
	      fileFieldName   = "storeImage";

	      // 보조: 혹시나 name이 다르면 전체 열거에서 첫 파일을 채택
	      if (savedSystemName == null) {
	        Enumeration<?> files = multipart.getFileNames();
	        if (files != null && files.hasMoreElements()) {
	          fileFieldName   = String.valueOf(files.nextElement());
	          savedSystemName = multipart.getFilesystemName(fileFieldName);
	          originalName    = multipart.getOriginalFileName(fileFieldName);
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      response.getWriter().write(json);
	      return null;
	    }

	    // 파일을 전혀 선택하지 않고 저장을 눌렀을 경우: "유지" 정책이면 success로 처리 가능
	    // 여기서는 선택 파일이 없으면 변경 없이 성공 처리(필요 시 정책에 맞춰 조정)
	    if (savedSystemName == null || originalName == null) {
	      json = "{\"result\":\"success\"}";
	      response.getWriter().write(json);
	      return null;
	    }

	    // -------------------------------
	    // 4) 기존 이미지 삭제 후 새 이미지 INSERT
	    //     - 매퍼 id: image.storeDelete / image.storeInsert / image.storeSelect
	    // -------------------------------
	    StoreImageDAO imageDAO = new StoreImageDAO();

	    try {
	      // (1) 기존 이미지 제거
	      //  - 동일 BUSINESS_NUMBER에 매핑된 레코드를 정리
	      imageDAO.delete(businessNumber);  // -> mapper: image.storeDelete  :contentReference[oaicite:1]{index=1}

	      // (2) 신규 DTO 구성
	      StoreImageDTO dto = new StoreImageDTO();
	      dto.setBusinessNumber(businessNumber);
	      dto.setStoreImageOriginalName(originalName);
	      dto.setStoreImageSystemName(savedSystemName);

	      // (3) INSERT
	      imageDAO.insert(dto);            // -> mapper: image.storeInsert   :contentReference[oaicite:2]{index=2}

	      // (4) 검증/조회(선택)
	      StoreImageDTO selected = imageDAO.selectone(businessNumber); // -> mapper: image.storeSelect  :contentReference[oaicite:3]{index=3}
	      // System.out.println("업로드 확인: " + selected);

	      json = (selected != null) ? "{\"result\":\"success\"}" : "{\"result\":\"fail\"}";

	    } catch (Exception e) {
	      e.printStackTrace();
	      json = "{\"result\":\"fail\"}";
	    }

	    // -------------------------------
	    // 5) JSON 응답
	    // -------------------------------
	    response.getWriter().write(json);

	    // 본 컨트롤러는 비동기(JSON) 응답이므로 Result 반환 없이 끝냄
	    return null;
	  }
	
}
