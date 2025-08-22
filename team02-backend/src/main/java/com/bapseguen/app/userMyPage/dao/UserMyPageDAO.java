package com.bapseguen.app.userMyPage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CommentDTO;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.dto.PostDTO;
import com.bapseguen.app.dto.ReviewDTO;
import com.bapseguen.app.dto.StoreFavoriteDTO;
import com.bapseguen.app.dto.view.MyPageDTO;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.config.MyBatisConfig;

public class UserMyPageDAO {
	private SqlSession sqlSession;

	public UserMyPageDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

	// 내 정보 조회
	public MyPageDTO MyPageSelect(int memberNumber) {
		return sqlSession.selectOne("mypage.myPageSelect", memberNumber);
	}

	// 내 정보 수정
	public int MyPageMemberUpdate(MyPageDTO dto) {
		System.out.println("개인마이페이지 정보수정 실행 ");
		return sqlSession.update("mypage.myPageMemberUpdate", dto);
	}

	// 회원 탈퇴
	public int Delete(int memberNumber) {
		System.out.println("개인마이페이지 회원탈퇴 delete 실행 ");
		return sqlSession.update("withDraw.withDrawDelete", memberNumber);
	}

	//  내가 작성한 게시글 목록 조회
    public List<PostDTO> selectMyPosts(int memberNumber) {
    	System.out.println("개인마이페이지 게시글 목록 조회 selectMyPosts 실행 ");
        return sqlSession.selectList("post.myPostSelect", memberNumber);
    }

    // 내가 작성한 댓글 조회
    public List<CommentDTO> selectMyComments(int memberNumber) {
    	System.out.println("개인마이페이지 댓글 조회 selectMyPosts 실행 ");
        return sqlSession.selectList("myComment.myCommentSelect", memberNumber);
    }

    //  내가 작성한 리뷰 조회
    public List<ReviewDTO> selectMyReviews(int memberNumber) {
    	System.out.println("개인마이페이지 리뷰 조회  selectMyReviews 실행 ");
        return sqlSession.selectList("myReview.myReviewSelect", memberNumber);
    }

    //  내 구매 내역 조회
    public List<OrdersDTO> selectMyFoodOrders(int memberNumber) {
    	System.out.println("개인마이페이지 구매내역 조회  selectMyFoodOrders 실행 ");
        return sqlSession.selectList("myOrder.myOrderSelect", memberNumber);
    }

    // 구매한 음식 메뉴 리뷰 작성
    public int InsertFoodReview(ReviewWriteDTO dto) {
    	System.out.println("개인마이페이지 리뷰작성  insertFoodReview 실행 ");
        return sqlSession.insert("myReview.myReviewInsert", dto);
    }

    
    //찜목록 조회
    public List<StoreFavoriteDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<StoreFavoriteDTO> list = sqlSession.selectList("myStoreFavorite.MyFavSelectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
}
