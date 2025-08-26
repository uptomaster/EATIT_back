package com.bapseguen.app.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.*;
import com.bapseguen.app.dto.view.*;
import com.bapseguen.config.MyBatisConfig;

public class AdminDAO {
	private SqlSession sqlSession;

	public AdminDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	/* ===================== 로그인 ===================== */
	public int loginAdmin(MemberDTO dto) {
		Integer memberNumber = sqlSession.selectOne("admin.loginAdmin", dto);
		return memberNumber == null ? -1 : memberNumber;
	}

	/* ===================== 배너 ===================== */
	public int insertBanner(BannerDTO dto) {
		return sqlSession.insert("admin.insertBanner", dto);
	}

	public List<BannerDTO> selectBannerList() {
		return sqlSession.selectList("admin.selectBannerList");
	}

	public BannerDTO selectBannerDetail(int bannerNumber) {
		return sqlSession.selectOne("admin.selectBannerDetail", bannerNumber);
	}

	public int updateBanner(BannerDTO dto) {
		return sqlSession.update("admin.updateBanner", dto);
	}

	public int deleteBanner(int bannerNumber) {
		return sqlSession.delete("admin.deleteBanner", bannerNumber);
	}

	/* ===================== 고객센터 문의 ===================== */
	public List<InquiryDTO> selectInquiryList() {
		return sqlSession.selectList("admin.selectInquiryList");
	}

	public InquiryDTO selectInquiryDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectInquiryDetail", postNumber);
	}

	public int insertInquiryComment(InquiryCommentDTO dto) {
		return sqlSession.insert("admin.insertInquiryComment", dto);
	}

	public int updateInquiryStatus(int postNumber, String status) {
		Map<String, Object> params = new HashMap<>();
		params.put("postNumber", postNumber);
		params.put("status", status);
		return sqlSession.update("admin.updateInquiryStatus", params);
	}

	/* ===================== 공지/이벤트 ===================== */
	public int insertNoticePost(AdminNoticeDTO dto) {
		return sqlSession.insert("admin.insertNoticePost", dto);
	}

	public int insertNotice(AdminNoticeDTO dto) {
		return sqlSession.insert("admin.insertNotice", dto);
	}

	public List<AdminNoticeDTO> selectNoticeList() {
		return sqlSession.selectList("admin.selectNoticeList");
	}

	public AdminNoticeDTO selectNoticeDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectNoticeDetail", postNumber);
	}

	public int updateNoticeTitle(AdminNoticeDTO dto) {
		return sqlSession.update("admin.updateNoticeTitle", dto);
	}

	public int updateNoticeContent(AdminNoticeDTO dto) {
		return sqlSession.update("admin.updateNoticeContent", dto);
	}

	public int deleteNotice(int postNumber) {
		return sqlSession.delete("admin.deleteNotice", postNumber);
	}

	/* ===================== FAQ ===================== */
	public int insertFaqPost(FaqDTO dto) {
		return sqlSession.insert("admin.insertFaqPost", dto);
	}

	public int insertFaq(FaqDTO dto) {
		return sqlSession.insert("admin.insertFaq", dto);
	}

	public List<FaqDTO> selectFaqList() {
		return sqlSession.selectList("admin.selectFaqList");
	}

	public FaqDTO selectFaqDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectFaqDetail", postNumber);
	}

	public int updateFaqTitle(FaqDTO dto) {
		return sqlSession.update("admin.updateFaqTitle", dto);
	}

	public int updateFaqContent(FaqDTO dto) {
		return sqlSession.update("admin.updateFaqContent", dto);
	}

	public int deleteFaq(int postNumber) {
		return sqlSession.delete("admin.deleteFaq", postNumber);
	}

	/* ===================== 신고/정지/블랙리스트 ===================== */
	public List<PostReportDTO> selectReportList() {
		return sqlSession.selectList("admin.selectReportList");
	}

	public int insertSuspendMember(MemberSuspendDTO dto) {
		return sqlSession.insert("admin.insertSuspendMember", dto);
	}

	public int insertBlacklistMember(MemberBlacklistDTO dto) {
		return sqlSession.insert("admin.insertBlacklistMember", dto);
	}

	public List<MemberSuspendDTO> selectSuspendList() {
		return sqlSession.selectList("admin.selectSuspendList");
	}

	public List<MemberBlacklistDTO> selectBlacklistList() {
		return sqlSession.selectList("admin.selectBlacklistList");
	}

	/* ===================== 회원 관리 ===================== */
	// 회원 목록 (검색 + 페이징)
	public List<MemberListDTO> selectMemberList(Map<String, Object> pageMap) {
		return sqlSession.selectList("admin.selectMemberList", pageMap);
	}

	// 회원 수 (검색 포함)
	public int memberListCount(Map<String, Object> pageMap) {
		return sqlSession.selectOne("admin.memberListCount", pageMap);
	}

	// 회원 상세
	public MemberDetailDTO selectMemberDetail(int memberNumber) {
		return sqlSession.selectOne("admin.selectMemberDetail", memberNumber);
	}

	/* ===================== 대시보드 통계 ===================== */
	public int countNotices() {
		return sqlSession.selectOne("admin.countNotices");
	}

	public int countFaqs() {
		return sqlSession.selectOne("admin.countFaqs");
	}

	public int countInquiries() {
		return sqlSession.selectOne("admin.countInquiries");
	}

	public int countUnansweredInquiries() {
		return sqlSession.selectOne("admin.countUnansweredInquiries");
	}

	public int countReports() {
		return sqlSession.selectOne("admin.countReports");
	}

	public int countActiveBanners() {
		return sqlSession.selectOne("admin.countActiveBanners");
	}

	// 대시보드 그래프 (최근 6개월 회원수)
	public List<Map<String, Object>> countMonthlyMembers() {
		return sqlSession.selectList("admin.countMonthlyMembers");
	}

	/* ===================== 회원 제재 ===================== */
	// 회원 타입 조회
	public String getMemberType(int memberNumber) {
		return sqlSession.selectOne("admin.getMemberType", memberNumber);
	}

	// 일반 회원 경고 증가
	public int increaseWarningCount(int memberNumber) {
		return sqlSession.update("admin.increaseWarningCount", memberNumber);
	}

	// 판매자 경고 증가
	public int increaseWarningCountSeller(int memberNumber) {
		return sqlSession.update("admin.increaseWarningCountSeller", memberNumber);
	}

	// 현재 경고 횟수 조회
	public int getWarningCount(int memberNumber) {
		return sqlSession.selectOne("admin.getWarningCount", memberNumber);
	}
}
