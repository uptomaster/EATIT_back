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
	public List<PostDTO> freeSelectAll(Map<String, Integer> pageMap) {
		System.out.println("자유게시판 게시글 조회하기 - freeSelectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("post.freeSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	//공지목록 조회
	public List<PostDTO> noticeSelectAll(Map<String, Integer> pageMap) {
		System.out.println("공지 목록 모든 게시글 조회하기 - noticeSelectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("notice.noticeSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
//	public List<PostDTO> typeSelectAll(Map<String, Object> pageMap) {
//	    System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
//	    List<PostDTO> list = sqlSession.selectList("post.typeSelectAll", pageMap);
//	    System.out.println("조회결과 : " + list);
//	    return list;
//	}
//	
	
	//홍보게시판 목록 조회
	public List<PostDTO> promoSelectAll(Map<String, Integer> pageMap) {
		System.out.println("홍보게시판 게시글 조회하기 - promoSelectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("post.promoSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	//레시피 게시판 목록 조회
	public List<PostDTO> recipeSelectAll(Map<String, Integer> pageMap) {
		System.out.println("레시피 게시글 조회하기 - recipeSelectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("post.recipeSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	// 게시글 총 개수 가져오기
//	public int getTotal() {
//		System.out.println("게시글 총 개수 조회 - getTotal 메소드 실행");
//		return sqlSession.selectOne("post.postGetTotal");
//	}
	// 공지사항 게시글 총 개수 가져오기
	public int noticeGetTotal() {
		System.out.println("공지 게시글 총 개수 조회 - noticeGetTotal 메소드 실행");
		return sqlSession.selectOne("notice.noticeGetTotal");
	}
	// 자유게시판 게시글 총 개수 가져오기
	public int freeGetTotal() {
		System.out.println("자유 게시글 총 개수 조회 - freeGetTotal 메소드 실행");
		return sqlSession.selectOne("post.freeGetTotal");
	}
	// 홍보게시판 게시글 총 개수 가져오기
	public int promoGetTotal() {
		System.out.println("홍보 게시글 총 개수 조회 - promoGetTotal 메소드 실행");
		return sqlSession.selectOne("post.promoGetTotal");
	}
	// 레시피게시판 게시글 총 개수 가져오기
	public int recipeGetTotal() {
		System.out.println("레시피 게시글 총 개수 조회 - recipeGetTotal 메소드 실행");
		return sqlSession.selectOne("post.recipeGetTotal");
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
	public void update(PostDetailDTO postDetailDTO) {
		sqlSession.update("post.postUpdate", postDetailDTO);
	}
	
	// 게시글 추가 후 자동으로 생성된 boardNumber 반환 -> 파일 테이블에서도 써야하기 때문에
	public int insertPost(PostDTO postDTO) {
		int insert = sqlSession.insert("post.insert", postDTO);
		System.out.println(postDTO + "출력");
		//System.out.println(postDTO.getBoardContent() + "출력 === ");
		System.out.println("게시글 작성 - insertBoard 메소드 실행 ");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 boardNumber : " + postDTO.getPostNumber());
		return postDTO.getPostNumber();
	}

	// 내가 작성한 게시글 목록 조회
	public List<PostDTO> myPostSelect(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("post.myPostSelect", pageMap);
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

}
