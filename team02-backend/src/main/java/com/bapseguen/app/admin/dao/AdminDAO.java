package com.bapseguen.app.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.AdminDTO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.config.MyBatisConfig;

public class AdminDAO {
    private SqlSession sqlSession;

    public AdminDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // auto-commit
    }

    /* ===================== 관리자 로그인 ===================== */
    public AdminDTO loginAdmin(AdminDTO adminDTO) {
        return sqlSession.selectOne("admin.loginAdmin", adminDTO);
    }

    /* ===================== 공지/이벤트 ===================== */
    public void insertNoticePost(AdminPostDTO postDTO) {
        sqlSession.insert("admin.insertNoticePost", postDTO);
        sqlSession.insert("admin.insertNotice", postDTO);
    }

    public List<AdminPostDTO> selectNoticeList(Map<String, Object> pageMap) {
        return sqlSession.selectList("admin.selectNoticeList", pageMap);
    }

    public int countNotices(Map<String, Object> pageMap) {
        return sqlSession.selectOne("admin.countNotices", pageMap);
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
        sqlSession.insert("admin.insertFaq", postDTO);
    }

    public List<AdminPostDTO> selectFaqList(Map<String, Object> pageMap) {
        return sqlSession.selectList("admin.selectFaqList", pageMap);
    }

    public int countFaqs(Map<String, Object> pageMap) {
        return sqlSession.selectOne("admin.countFaqs", pageMap);
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
    public void insertAdminImage(AdminImageDTO imageDTO) {
        sqlSession.insert("admin.insertAdminImage", imageDTO);
    }

    public List<AdminImageDTO> selectAdminImagesByPost(int postNumber) {
        return sqlSession.selectList("admin.selectAdminImagesByPost", postNumber);
    }

    public void deleteAdminImagesByPost(int postNumber) {
        sqlSession.delete("admin.deleteAdminImagesByPost", postNumber);
    }

    /* ===================== 대시보드 통계 ===================== */
    public int countReports() {
        return sqlSession.selectOne("admin.countReports");
    }

    public int countInquiries() {
        return sqlSession.selectOne("admin.countInquiries");
    }

    public int countUnansweredInquiries() {
        return sqlSession.selectOne("admin.countUnansweredInquiries");
    }
    
    /* ===================== 문의(Inquiry) ===================== */
    public List<AdminPostDTO> selectInquiryList(Map<String, Object> pageMap) {
        return sqlSession.selectList("admin.selectInquiryList", pageMap);
    }

    public int countInquiries(Map<String, Object> pageMap) {
        return sqlSession.selectOne("admin.countInquiries", pageMap);
    }

    public AdminPostDTO selectInquiryDetail(int postNumber) {
        return sqlSession.selectOne("admin.selectInquiryDetail", postNumber);
    }

    public void updateInquiryStatus(AdminPostDTO postDTO) {
        sqlSession.update("admin.updateInquiryStatus", postDTO);
    }
}
