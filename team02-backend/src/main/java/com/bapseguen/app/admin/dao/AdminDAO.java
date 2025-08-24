package com.bapseguen.app.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.BannerDTO;
import com.bapseguen.app.dto.FaqDTO;
import com.bapseguen.app.dto.InquiryDTO;
import com.bapseguen.app.dto.MemberBlacklistDTO;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.dto.MemberSuspendDTO;
import com.bapseguen.app.dto.NoticeDTO;
import com.bapseguen.app.dto.PostReportDTO;
import com.bapseguen.app.dto.view.MemberListDTO;
import com.bapseguen.config.MyBatisConfig;

public class AdminDAO {

    private SqlSession sqlSession;

    public AdminDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }
    
    /** 관리자 로그인 => 로그인 성공 시 memberNumber, 로그인 실패 시 -1 반환 */
    public int loginAdmin(MemberDTO dto) {
        Integer memberNumber = sqlSession.selectOne("admin.loginAdmin", dto);
        return memberNumber == null ? -1 : memberNumber;
    }
    

    /* ===================== 배너 ===================== */

    /** 배너 등록 */
    public int insertBanner(BannerDTO dto) {
        return sqlSession.insert("admin.insertBanner", dto);
    }

    /** 배너 목록 조회 */
    public List<BannerDTO> selectBannerList() {
        return sqlSession.selectList("admin.selectBannerList");
    }

    /** 배너 수정 */
    public int updateBanner(BannerDTO dto) {
        return sqlSession.update("admin.updateBanner", dto);
    }

    /** 배너 삭제 */
    public int deleteBanner(int bannerNumber) {
        return sqlSession.delete("admin.deleteBanner", bannerNumber);
    }

    /* ===================== 고객센터 문의 ===================== */

    /** 문의글 목록 조회 */
    public List<InquiryDTO> selectInquiryList() {
        return sqlSession.selectList("admin.selectInquiryList");
    }

    /** 문의글 상세 조회 */
    public InquiryDTO selectInquiryDetail(int postNumber) {
        return sqlSession.selectOne("admin.selectInquiryDetail", postNumber);
    }

    /** 문의글 댓글 작성 */ 
    // 댓글은 내일 수업 배우고 진행하기
    //    public int insertInquiryComment(CommentDTO dto) {
    //        return sqlSession.insert("admin.insertInquiryComment", dto);
    //    }

    /* ===================== 공지 ===================== */
    // 작성은 2단계: insertNoticePost → insertNotice

    /** 공지 POST 생성(제목/메타) */
    public int insertNoticePost(NoticeDTO dto) {
        return sqlSession.insert("admin.insertNoticePost", dto);
    }

    /** 공지 본문 생성 */
    public int insertNotice(NoticeDTO dto) {
        return sqlSession.insert("admin.insertNotice", dto);
    }

    /** 공지 목록 조회 */
    public List<NoticeDTO> selectNoticeList() {
        return sqlSession.selectList("admin.selectNoticeList");
    }

    /** 공지 제목 수정 */
    public int updateNoticeTitle(NoticeDTO dto) {
        return sqlSession.update("admin.updateNoticeTitle", dto);
    }

    /** 공지 본문 수정 */
    public int updateNoticeContent(NoticeDTO dto) {
        return sqlSession.update("admin.updateNoticeContent", dto);
    }

    /** 공지 삭제(POST 삭제 → NOTICE는 CASCADE) */
    public int deleteNotice(int postNumber) {
        return sqlSession.delete("admin.deleteNotice", postNumber);
    }

    /* ===================== FAQ ===================== */
    // 작성은 2단계(Post에 공통 속성 먼저쓰고 FAQ만들기): insertFaqPost → insertFaq
  

    /** FAQ POST 생성(제목/메타) */
    public int insertFaqPost(FaqDTO dto) {
        return sqlSession.insert("admin.insertFaqPost", dto);
    }

    /** FAQ 본문 생성 */
    public int insertFaq(FaqDTO dto) {
        return sqlSession.insert("admin.insertFaq", dto);
    }

    /** FAQ 목록 조회 */
    public List<FaqDTO> selectFaqList() {
        return sqlSession.selectList("admin.selectFaqList");
    }

    /** FAQ 상세 조회 */
    public FaqDTO selectFaqDetail(int postNumber) {
        return sqlSession.selectOne("admin.selectFaqDetail", postNumber);
    }

    /** FAQ 제목 수정 */
    public int updateFaqTitle(FaqDTO dto) {
        return sqlSession.update("admin.updateFaqTitle", dto);
    }

    /** FAQ 본문 수정 */
    public int updateFaqContent(FaqDTO dto) {
        return sqlSession.update("admin.updateFaqContent", dto);
    }

    /** FAQ 삭제(POST 삭제 → FAQ는 CASCADE) */
    public int deleteFaq(int postNumber) {
        return sqlSession.delete("admin.deleteFaq", postNumber);
    }

    /* ===================== 신고/정지/블랙리스트 ===================== */

    /** 신고 목록 조회 (PostReportDTO 사용) */
    public List<PostReportDTO> selectReportList() {
        return sqlSession.selectList("admin.selectReportList");
    }

    /** 정지 회원 목록 조회 */
    public List<MemberSuspendDTO> selectSuspendList() {
        return sqlSession.selectList("admin.selectSuspendList");
    }

    /** 블랙리스트 목록 조회 */
    public List<MemberBlacklistDTO> selectBlacklistList() {
        return sqlSession.selectList("admin.selectBlacklistList");
    }
    
 // 회원 목록 가져오기
 	public List<MemberListDTO> selectMember(Map<String, Integer> pageMap) {
 		System.out.println("모든 회원 조회하기 - selectAll 메소드 실행 : " + pageMap);
 		List<MemberListDTO> list = sqlSession.selectList("admin.selectMemberListList", pageMap);
 		System.out.println("조회결과 : " + list);
 		return list;
 	}

	public int getTotal() {
		System.out.println("회원정보 총 개수 조회 - getTotal 메소드 실행");
		return sqlSession.selectOne("admin.memberListCount");
	}
}