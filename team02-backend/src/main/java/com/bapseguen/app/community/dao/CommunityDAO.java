package com.bapseguen.app.community.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.FaqDTO;
import com.bapseguen.app.dto.FreeBoardDTO;
import com.bapseguen.app.dto.InquiryDTO;
import com.bapseguen.app.dto.NoticeDTO;
import com.bapseguen.app.dto.PostDTO;
import com.bapseguen.app.dto.PromoBoardDTO;
import com.bapseguen.app.dto.RecipeBoardDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.config.MyBatisConfig;

public class CommunityDAO {
	private SqlSession sqlSession;

	public CommunityDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 전체 게시글 목록 조회
	public List<PostDTO> postSelectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("post.postSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}

	//자유게시판 목록 조회
	public List<FreeBoardDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<FreeBoardDTO> list = sqlSession.selectList("board.freeSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	//홍보게시판 목록 조회
	public List<PromoBoardDTO> promoSelectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<PromoBoardDTO> list = sqlSession.selectList("board.promoSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	//레시피 게시판 목록 조회
	public List<RecipeBoardDTO> recipeSelectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<RecipeBoardDTO> list = sqlSession.selectList("board.recipeSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	// 게시글 총 개수 가져오기
	public int getTotal() {
		System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
		return sqlSession.selectOne("notice.noticeGetTotal");
	}

	// 게시글 상세 조회
	public PostDetailDTO select(int postNumber) {
		return sqlSession.selectOne("post.postSelect", postNumber);
	}

	// 게시글 조회수 증가
	public void updateReadCount(int postNumber) {
		sqlSession.update("post.postUpdateReadCount", postNumber);
	}

	// 게시글 삭제
	public void delete(int postNumber) {
		sqlSession.delete("post.postDelete", postNumber);
	}

	// 게시글 수정
	public void update(PostDTO postDTO) {
		sqlSession.update("post.postUpdate", postDTO);
	}

	// 내가 작성한 게시글 목록 조회
	public List<PostDTO> myPostSelect(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("post.myPostSelect", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	// 고객센터 문의 목록 조회 (페이징 적용)
	public List<InquiryDTO> inquirySelectAll(Map<String, Integer> pageMap) {
		System.out.println("고객센터 모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<InquiryDTO> list = sqlSession.selectList("inquiry.inquirySelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}

	// 고객센터 문의글 작성
	public int insertInquiry(InquiryDTO inquiry) {
		return sqlSession.insert("inquiry.inquiryInsert", inquiry);
	}

	// 고객센터 문의글 상세 조회
	public InquiryDTO selectInquiryDetail(int postNumber) {
		return sqlSession.selectOne("inquiry.inquirySelect", postNumber);
	}
	
	//공지목록 조회
	public List<NoticeDTO> noticeSelectAll(Map<String, Integer> pageMap) {
		System.out.println("공지 목록 모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<NoticeDTO> list = sqlSession.selectList("notice.noticeSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	
	
	// FAQ 목록 조회
	public List<FaqDTO> faqSelectAll(Map<String, Integer> pageMap) {
		System.out.println("고객센터 모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<FaqDTO> list = sqlSession.selectList("faq.faqSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	
	
	// FAQ 개별 조회
    public FaqDTO selectFaqDetail(int faqNumber) {
        return sqlSession.selectOne("faq.faqSelect", faqNumber);
    }
	
	

}
