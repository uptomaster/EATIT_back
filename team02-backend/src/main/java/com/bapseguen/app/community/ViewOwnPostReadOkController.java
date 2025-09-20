package com.bapseguen.app.community;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.NoticeDTO;
import com.bapseguen.app.img.dao.PostImageDAO;

public class ViewOwnPostReadOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("====ViewOwnPostReadOkController 실행====");
		
		Result result = new Result();
        HttpSession session = request.getSession();

        // postNumber 파라미터 체크
        String postNumberStr = request.getParameter("postNumber");
        if (postNumberStr == null || postNumberStr.trim().isEmpty()) {
            System.out.println("postNumber 값이 없습니다");
            result.setPath("/app/community/communityMainUser.jsp");
            result.setRedirect(true);
            return result;
        }

        int postNumber = Integer.parseInt(postNumberStr);

        //  게시글 조회
        CommunityDAO communityDAO = new CommunityDAO();
        NoticeDTO noticeDTO = communityDAO.selectNotice(postNumber);
        if (noticeDTO == null) {
            System.out.println("존재하지 않는 게시글입니다. postNumber=" + postNumber);
            result.setPath("/app/community/communityMainUser.jsp");
            result.setRedirect(true);
            return result;
        }
        request.setAttribute("notice", noticeDTO);

        //  관리자 이미지 조회
        PostImageDAO postImageDAO = new PostImageDAO();
        List<AdminImageDTO> noticeImages = postImageDAO.noticeimgselect(postNumber);
        request.setAttribute("noticeImages", noticeImages);

        //  로그인 사용자 체크 (회원 / 관리자 모두 고려)
        Integer loginMemberNumber = (Integer) session.getAttribute("memberNumber");
        Integer loginAdminNumber = (Integer) session.getAttribute("adminNumber");

        // 조회수 증가 (로그인 여부 상관없이, 단 작성자는 제외)
        if ((loginMemberNumber == null || !loginMemberNumber.equals(noticeDTO.getAdminNumber()))
            && (loginAdminNumber == null || !loginAdminNumber.equals(noticeDTO.getAdminNumber()))) {
            communityDAO.updateReadCount(postNumber);
        }

        // JSP 경로
        result.setPath("/app/community/viewOwnPost.jsp");
        result.setRedirect(false);
        return result;
    }
		
		
}
