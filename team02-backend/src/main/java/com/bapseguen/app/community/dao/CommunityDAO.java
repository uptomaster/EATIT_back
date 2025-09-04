package com.bapseguen.app.community.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.bapseguen.app.dto.FaqDTO;
import com.bapseguen.app.dto.InquiryDTO;
import com.bapseguen.app.dto.PostDTO;
import com.bapseguen.app.dto.view.InquiryDetailDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.config.MyBatisConfig;

public class CommunityDAO {
	private SqlSession sqlSession;

	public CommunityDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
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
	

	//공지목록 조회 (단건)
	public List<PostDTO> noticeSelect(Map<String, Integer> pageMap) {
		System.out.println("공지 목록 게시글 상세 조회하기 - noticeSelectOne 메소드 실행 : " + pageMap);
		List<PostDTO> list = sqlSession.selectList("notice.noticeSelect", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	// 게시글 상세 조회
	public PostDetailDTO select(int postNumber) {
		return sqlSession.selectOne("post.postDetailSelect", postNumber);
	}

	// 게시글 조회수 증가
	public void updateReadCount(int postNumber) {
		sqlSession.update("post.postUpdateReadCount", postNumber);
	}

	// 게시글 삭제
	public void delete(int postNumber) {
	    SqlSession session = null;
	    try {
	        session = MyBatisConfig.getSqlSessionFactory().openSession(false); // 트랜잭션 수동 처리

	        // 삭제 순서 중요
	        session.delete("post.freeBoardImageDelete", postNumber); 
	        session.delete("post.freeBoardDelete", postNumber);       
	        session.delete("post.postDelete", postNumber);            

	        session.commit();
	        System.out.println("게시글 전체 삭제 성공");
	    } catch (Exception e) {
	        if (session != null) session.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session != null) session.close();
	    }
	}
	
	

	// 게시글 수정
	public void update(PostDetailDTO postDetailDTO) {
	    if (postDetailDTO.getPostType() == null) {
	        throw new IllegalArgumentException("postType이 null입니다.");
	        // 또는 기본값으로 설정: postDetailDTO.setPostType("FREE");
	    }

	    sqlSession.update("post.updatePostTitle", postDetailDTO);

	    switch (postDetailDTO.getPostType()) {
	        case "FREE":
	            sqlSession.update("post.updateFreeContent", postDetailDTO);
	            break;
	        case "PROMOTION":
	            sqlSession.update("post.updatePromoContent", postDetailDTO);
	            break;
	        case "RECIPE":
	            sqlSession.update("post.updateRecipeContent", postDetailDTO);
	            break;
	        default:
	            throw new IllegalArgumentException("지원하지 않는 게시판 타입입니다: " + postDetailDTO.getPostType());
	    }
	}

	
	
	// 게시글 추가 후 자동으로 생성된 boardNumber 반환 -> 파일 테이블에서도 써야하기 때문에
//	public int insertPost(PostDTO postDTO) {
//		int insert = sqlSession.insert("post.insert", postDTO);
//		System.out.println(postDTO + "출력");
//		//System.out.println(postDTO.getBoardContent() + "출력 === ");
//		System.out.println("게시글 작성 - insertBoard 메소드 실행 ");
//		System.out.println("insert 결과 : " + insert);
//		System.out.println("생성된 boardNumber : " + postDTO.getPostNumber());
//		return postDTO.getPostNumber();
//	}
	
	//게시글 상세 
	private SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();

	public int insertFreePost(Map<String, Object> postParams) {
	    int postNumber = 0;

	    try (SqlSession session = sqlSessionFactory.openSession(false)) {
	        // insertFreePost와 insertFreeContent 실행
	        session.insert("post.insertFreePost", postParams);
	        session.insert("post.insertFreeContent", postParams);

	        session.commit();

	        // insert 시 <selectKey>에서 채워진 postNumber 가져오기
	        postNumber = (int) postParams.get("postNumber");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return postNumber;
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
    public FaqDTO selectFaqDetail(int postNumber) {
        return sqlSession.selectOne("faq.faqSelect", postNumber);
    }
    
    // 회원정보 총 개수 반환
    public int getTotal1() {
    	System.out.println("INQUIRY 총 개수 조회 - getTotal 메소드 실행");
    	return sqlSession.selectOne("faq.faqListCount");
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
    public InquiryDetailDTO selectInquiryDetail(int postNumber) {
    	return sqlSession.selectOne("inquiry.inquirySelect", postNumber);
    }

    // 회원정보 총 개수 반환
    public int getTotal2() {
    	System.out.println("INQUIRY 총 개수 조회 - getTotal 메소드 실행");
    	return sqlSession.selectOne("inquiry.inquiryListCount");
    }

}
