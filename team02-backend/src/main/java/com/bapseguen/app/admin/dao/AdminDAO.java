package com.bapseguen.app.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.AdminDTO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.MemberBlacklistDTO;
import com.bapseguen.app.dto.MemberSuspendDTO;
import com.bapseguen.app.dto.PostReportDTO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.config.MyBatisConfig;

public class AdminDAO {
	private SqlSession sqlSession;

	public AdminDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	/* ===================== 로그인 ===================== */
	public AdminDTO loginAdmin(AdminDTO dto) {
		return sqlSession.selectOne("admin.loginAdmin", dto);
	}

	/* ===================== 공지 ===================== */
	public void insertNoticePost(AdminPostDTO postDTO) {
		sqlSession.insert("admin.insertNoticePost", postDTO);
	}

	public void insertNotice(AdminPostDTO postDTO) {
		sqlSession.insert("admin.insertNotice", postDTO);
	}

	public List<AdminPostDTO> selectNoticeList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectNoticeList", params);
	}

	public int countNotices(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countNotices", params);
	}

	public int countNotices() {
		return sqlSession.selectOne("admin.countNotices");
	}

	public AdminPostDTO selectNoticeDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectNoticeDetail", postNumber);
	}

	public void updateNoticeTitle(AdminPostDTO postDTO) {
		sqlSession.update("admin.updateNoticeTitle", postDTO);
	}

	public void updateNoticeContent(AdminPostDTO postDTO) {
		sqlSession.update("admin.updateNoticeContent", postDTO);
	}

	public void deleteNotice(int postNumber) {
		sqlSession.delete("admin.deleteNotice", postNumber);
	}

	/* ===================== FAQ ===================== */
	public void insertFaqPost(AdminPostDTO postDTO) {
		sqlSession.insert("admin.insertFaqPost", postDTO);
	}

	public void insertFaq(AdminPostDTO postDTO) {
		sqlSession.insert("admin.insertFaq", postDTO);
	}

	public List<AdminPostDTO> selectFaqList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectFaqList", params);
	}

	public int countFaqs(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countFaqs", params);
	}

	public int countFaqs() {
		return sqlSession.selectOne("admin.countFaqs");
	}

	public AdminPostDTO selectFaqDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectFaqDetail", postNumber);
	}

	public void updateFaqTitle(AdminPostDTO postDTO) {
		sqlSession.update("admin.updateFaqTitle", postDTO);
	}

	public void updateFaqContent(AdminPostDTO postDTO) {
		sqlSession.update("admin.updateFaqContent", postDTO);
	}

	public void deleteFaq(int postNumber) {
		sqlSession.delete("admin.deleteFaq", postNumber);
	}

	/* ===================== 관리자 이미지 ===================== */
	public void insertAdminImage(AdminImageDTO imgDTO) {
		sqlSession.insert("admin.insertAdminImage", imgDTO);
	}

	public List<AdminImageDTO> selectAdminImagesByPost(int postNumber) {
		return sqlSession.selectList("admin.selectAdminImagesByPost", postNumber);
	}

	public void deleteAdminImagesByPost(int postNumber) {
		sqlSession.delete("admin.deleteAdminImagesByPost", postNumber);
	}

	/* ===================== 문의(Inquiry) ===================== */
	public List<AdminPostDTO> selectInquiryList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectInquiryList", params);
	}

	public int countInquiries(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countInquiries", params);
	}

	public int countInquiries() {
		return sqlSession.selectOne("admin.countInquiries");
	}

	public AdminPostDTO selectInquiryDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectInquiryDetail", postNumber);
	}

	public void updateInquiryStatus(AdminPostDTO postDTO) {
		sqlSession.update("admin.updateInquiryStatus", postDTO);
		sqlSession.commit();
	}

	public void updateInquiryAnswer(AdminPostDTO postDTO) {
		sqlSession.update("admin.updateInquiryAnswer", postDTO);
		sqlSession.commit();
	}

	public void deleteInquiryAnswer(int postNumber) {
		sqlSession.update("admin.deleteInquiryAnswer", postNumber);
		sqlSession.commit();
	}

	/* ===================== 신고 / 정지 / 블랙리스트 ===================== */
	// 전체 신고 목록
	public List<PostReportDTO> selectReportList() {
		return sqlSession.selectList("admin.selectReportList");
	}

	// 페이징/검색용 신고 목록
	public List<PostReportDTO> selectReportListPaged(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectReportListPaged", params);
	}

	// 신고 목록 개수 (검색/페이징용)
	public int countReportList(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countReportList", params);
	}

	public List<MemberSuspendDTO> selectSuspendList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectSuspendList", params);
	}

	public int countSuspendList(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countSuspendList", params);
	}

	public List<MemberBlacklistDTO> selectBlacklistList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectBlacklistList", params);
	}

	public int countBlacklistList(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countBlacklistList", params);
	}

	public void insertSuspend(MemberSuspendDTO dto) {
		sqlSession.insert("admin.insertSuspend", dto);
	}

	public void insertBlacklist(MemberBlacklistDTO dto) {
		sqlSession.insert("admin.insertBlacklist", dto);
	}

	public void clearExpiredSuspends() {
		sqlSession.delete("admin.clearExpiredSuspends");
		sqlSession.commit();
	}

	public boolean isSuspended(int memberNumber) {
		Integer result = sqlSession.selectOne("admin.checkSuspend", memberNumber);
		return result != null && result > 0;
	}

	public boolean isBlacklisted(int memberNumber) {
		Integer result = sqlSession.selectOne("admin.checkBlacklist", memberNumber);
		return result != null && result > 0;
	}

	public void deleteBlacklist(int blacklistNumber) {
		sqlSession.delete("admin.deleteBlacklist", blacklistNumber);
		sqlSession.commit();
	}

	public void deleteSuspend(int memberNumber) {
		sqlSession.delete("admin.deleteSuspend", memberNumber);
		sqlSession.commit();
	}

	/* ===================== 대시보드 통계 ===================== */
	public int memberListCount(Map<String, Object> params) {
		return sqlSession.selectOne("admin.memberListCount", params);
	}

	public int memberListCount() {
		return sqlSession.selectOne("admin.memberListCount", new java.util.HashMap<>());
	}

	public int countUnansweredInquiries() {
		return sqlSession.selectOne("admin.countUnansweredInquiries");
	}

	public int countReports() {
		return sqlSession.selectOne("admin.countReports");
	}

	public List<Map<String, Object>> countMonthlyMembers() {
		return sqlSession.selectList("admin.countMonthlyMembers");
	}

	/* ===================== 회원 ===================== */
	public Map<String, Object> selectMemberDetail(int memberNumber) {
		return sqlSession.selectOne("admin.selectMemberDetail", memberNumber);
	}

	public List<Map<String, Object>> selectMemberList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectMemberList", params);
	}

	public void giveWarning(int memberNumber, String memberType) {
		if ("GENERAL".equalsIgnoreCase(memberType)) {
			sqlSession.update("admin.giveGeneralWarning", memberNumber);
		} else if ("SELLER".equalsIgnoreCase(memberType)) {
			sqlSession.update("admin.giveSellerWarning", memberNumber);
		}
	}

	public List<Map<String, Object>> selectBoardFreeList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectBoardFreeList", params);
	}

	public Map<String, Object> selectBoardFreeDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectBoardFreeDetail", postNumber);
	}

	public int deleteBoardFree(int postNumber) {
		return sqlSession.delete("admin.deleteBoardFree", postNumber);
	}

	public List<Map<String, Object>> selectBoardPromotionList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectBoardPromotionList", params);
	}

	public Map<String, Object> selectBoardPromotionDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectBoardPromotionDetail", postNumber);
	}

	public int deleteBoardPromotion(int postNumber) {
		return sqlSession.delete("admin.deleteBoardPromotion", postNumber);
	}

	public List<Map<String, Object>> selectBoardRecipeList(Map<String, Object> params) {
		return sqlSession.selectList("admin.selectBoardRecipeList", params);
	}

	public Map<String, Object> selectBoardRecipeDetail(int postNumber) {
		return sqlSession.selectOne("admin.selectBoardRecipeDetail", postNumber);
	}

	public int deleteBoardRecipe(int postNumber) {
		return sqlSession.delete("admin.deleteBoardRecipe", postNumber);
	}

	public int countBoardFree(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countBoardFree", params);
	}

	public int countBoardPromotion(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countBoardPromotion", params);
	}

	public int countBoardRecipe(Map<String, Object> params) {
		return sqlSession.selectOne("admin.countBoardRecipe", params);
	}
}
