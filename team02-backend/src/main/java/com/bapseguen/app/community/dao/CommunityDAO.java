package com.bapseguen.app.community.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.FaqDTO;
import com.bapseguen.app.dto.InquiryDTO;
import com.bapseguen.app.dto.NoticeDTO;
import com.bapseguen.app.dto.PostDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.config.MyBatisConfig;

public class CommunityDAO {
	private SqlSession sqlSession;

	public CommunityDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 전체 게시글 목록 조회
	public List<PostDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("board.selectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}

	// 게시글 추가 후 자동으로 생성된 boardNumber 반환 -> 파일 테이블에서도 써야하기 때문에
	public int insertBoard(PostDTO postDTO) {
		int insert = sqlSession.insert("board.insert", postDTO);
		System.out.println(postDTO + "출력");
		System.out.println(postDTO.getfreeContent() + "출력 === ");
		System.out.println("게시글 작성 - insertBoard 메소드 실행 ");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 postNumber : " + postDTO.getPostNumber());
		return postDTO.getPostNumber();
	}

	public int insertBoard(PostDTO postDTO) {
		int insert = sqlSession.insert("board.insert", postDTO);
		System.out.println(postDTO + "출력");
		System.out.println(postDTO.getPromoContent() + "출력 === ");
		System.out.println("게시글 작성 - insertBoard 메소드 실행 ");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 postNumber : " + postDTO.getPostNumber());
		return postDTO.getPostNumber();
	}

	public int insertBoard(PostDTO postDTO) {
		int insert = sqlSession.insert("board.insert", postDTO);
		System.out.println(postDTO + "출력");
		System.out.println(postDTO.getRecipeContent() + "출력 === ");
		System.out.println("게시글 작성 - insertBoard 메소드 실행 ");
		System.out.println("insert 결과 : " + insert);
		System.out.println("생성된 postNumber : " + postDTO.getPostNumber());
		return postDTO.getPostNumber();
	}

	// 게시글 상세 조회
	public PostDetailDTO select(int postNumber) {
		return sqlSession.selectOne("post.select", postNumber);
	}

	// 게시글 조회수 증가
	public void updateReadCount(int postNumber) {
		sqlSession.update("post.updateReadCount", postNumber);
	}

	// 게시글 삭제
	public void delete(int postNumber) {
		sqlSession.delete("post.delete", postNumber);
	}

	// 게시글 수정
	public void update(PostDTO postDTO) {
		sqlSession.update("post.update", postDTO);
	}

	// 내가 작성한 게시글 목록 조회
	public List<PostDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("board.selectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	

	
	// 고객센터 문의 목록 조회 (페이징 적용)
	public List<InquiryDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("고객센터 모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<InquiryDTO> list = sqlSession.selectList("inquiry.selectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}

	// 고객센터 문의글 작성
	public int insertInquiry(InquiryDTO inquiry) {
		return sqlSession.insert("inquiry.insertInquiry", inquiry);
	}

	// 고객센터 문의글 상세 조회
	public InquiryDTO selectInquiryDetail(int postNumber) {
		return sqlSession.selectOne("inquiry.select", postNumber);
	}
	
	//공지목록 조회
	public List<NoticeDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("공지 목록 모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<NoticeDTO> list = sqlSession.selectList("notice.selectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	
	// FAQ 목록 조회
	public List<FaqDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("고객센터 모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<FaqDTO> list = sqlSession.selectList("faq.selectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	
	
	// FAQ 상세 조회
    public FaqDTO selectFaqDetail(int faqNumber) {
        return sqlSession.selectOne("faq.select", faqNumber);
    }
	
	

}
