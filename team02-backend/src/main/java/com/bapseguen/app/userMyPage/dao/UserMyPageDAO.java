package com.bapseguen.app.userMyPage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CommentDTO;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.dto.PostDTO;
import com.bapseguen.app.dto.ReviewDTO;
import com.bapseguen.app.dto.view.MyPageDTO;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.config.MyBatisConfig;

public class UserMyPageDAO {
	private SqlSession sqlSession;

	public UserMyPageDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

	// 내 정보 조회
	public MyPageDTO select(int memberNumber) {
		return sqlSession.selectOne("mypage.select", memberNumber);
	}

	// 이름 수정
	public int updateName(MyPageDTO dto) {
		System.out.println("개인마이페이지 이름수정 updateName 실행 ");
		return sqlSession.update("mypage.updateName", dto);
	}

	// 전화번호 수정
	public int updatePhone(MyPageDTO dto) {
		System.out.println("개인마이페이지 전화번호수정 updatePhone 실행 ");
		return sqlSession.update("mypage.updatePhone", dto);
	}

	// 비밀번호 수정
	public int updatePassword(MyPageDTO dto) {
		System.out.println("개인마이페이지 비밀번호수정 updatePassword 실행 ");
		return sqlSession.update("mypage.updatePassword", dto);
	}

	// 회원 탈퇴
	public int delete(int memberNumber) {
		System.out.println("개인마이페이지 회원탈퇴 delete 실행 ");
		return sqlSession.update("mypage.delete", memberNumber);
	}

	//  내가 작성한 게시글 목록 조회
    public List<PostDTO> selectMyPosts(int memberNumber) {
    	System.out.println("개인마이페이지 게시글 목록 조회 selectMyPosts 실행 ");
        return sqlSession.selectList("mypage.selectMyPosts", memberNumber);
    }

    // 내가 작성한 댓글 조회
    public List<CommentDTO> selectMyComments(int memberNumber) {
    	System.out.println("개인마이페이지 댓글 조회 selectMyPosts 실행 ");
        return sqlSession.selectList("mypage.selectMyComments", memberNumber);
    }

    //  내가 작성한 리뷰 조회
    public List<ReviewDTO> selectMyReviews(int memberNumber) {
    	System.out.println("개인마이페이지 리뷰 조회  selectMyReviews 실행 ");
        return sqlSession.selectList("mypage.selectMyReviews", memberNumber);
    }

    //  내 구매 내역 조회
    public List<OrdersDTO> selectMyFoodOrders(int memberNumber) {
    	System.out.println("개인마이페이지 구매내역 조회  selectMyFoodOrders 실행 ");
        return sqlSession.selectList("mypage.selectMyFoodOrders", memberNumber);
    }

    // 구매한 음식 메뉴 리뷰 작성
    public int insertFoodReview(ReviewWriteDTO dto) {
    	System.out.println("개인마이페이지 리뷰작성  insertFoodReview 실행 ");
        return sqlSession.insert("mypage.insertFoodReview", dto);
    }

    
    //찜목록 조회
    public List<StoreFavoriteDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<StoreFavoriteDTO> list = sqlSession.selectList("myStoreFavorite.selectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
}
