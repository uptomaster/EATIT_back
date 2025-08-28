package com.bapseguen.app.community;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteFreeBoardOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("==== WriteFreeBoardOkController 실행 ====");
	    Result result = new Result();

	    //업로드 경로 준비
	    final String UPLOAD_PATH = request.getServletContext().getRealPath("/upload");
	    final int FILE_SIZE = 10 * 1024 * 1024; // 10MB
	    File dir = new File(UPLOAD_PATH);
	    if (!dir.exists()) dir.mkdirs();

	    //cos 파싱
	    MultipartRequest multi = new MultipartRequest(
	            request, UPLOAD_PATH, FILE_SIZE, "UTF-8", new DefaultFileRenamePolicy());

	    //세션 회원 번호
	    Object attr = request.getSession().getAttribute("memberNumber");
	    if (attr == null) {
	        result.setPath(request.getContextPath() + "/app/login/login.jsp");
	        result.setRedirect(true);
	        return result;
	    }
	    long memberNumber = ((Number) attr).longValue(); //Optional사용

	    //파라미터
	    String postTitle   = multi.getParameter("postTitle");
	    String freeContent = multi.getParameter("freeContent");
	    if (postTitle == null || postTitle.isBlank() || freeContent == null || freeContent.isBlank()) {
	        request.setAttribute("error", "제목/내용을 입력하세요.");
	        result.setPath("/app/community/writeFreeBoard.jsp");
	        result.setRedirect(false);
	        return result;
	    }

	    //DAO 호출 (부모 -> 자식 트랜잭션)
	    Map<String, Object> postParams = new HashMap<>();
	    postParams.put("memberNumber", memberNumber);
	    postParams.put("postTitle", postTitle);
	    postParams.put("freeContent", freeContent);

	    new CommunityDAO().insertFreePost(postParams);

	    // 파일 업로드 테이블 저장 시에는 multi.getFileNames()로 반복하면됨

	    result.setPath(request.getContextPath() + "/community/freeBoardListOk.co");
	    result.setRedirect(true);
	    return result;
	}
}
