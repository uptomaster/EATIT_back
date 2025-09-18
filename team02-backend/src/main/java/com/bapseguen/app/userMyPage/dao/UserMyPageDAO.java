package com.bapseguen.app.userMyPage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.view.CommentListDTO;
import com.bapseguen.app.dto.view.MyPageDTO;
import com.bapseguen.app.dto.view.MyPurchaseDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.config.MyBatisConfig;

public class UserMyPageDAO {
	private SqlSession sqlSession;

	public UserMyPageDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }
	
	// 비밀번호 확인
    public boolean checkPassword(int memberNumber, String memberPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberNumber", memberNumber);
        map.put("memberPassword", memberPassword);
        Integer cnt = sqlSession.selectOne("myPage.checkPassword", map);
        return cnt != null && cnt == 1;
    }

	// 내 정보 조회
	public MyPageDTO myPageSelect(int memberNumber) {
		return sqlSession.selectOne("myPage.myPageSelect", memberNumber);
	}

	
	// 비밀번호 업데이트
	public int updatePassword(Map<String, Object> paramMap) {
	    return sqlSession.update("myPage.updatePassword", paramMap);
	}

	// 전화번호 업데이트
	public int updatePhone(Map<String, Object> paramMap) {
	    return sqlSession.update("myPage.updatePhone", paramMap);
	}
	
	// 비밀번호 업데이트
	public int updatePassword(int memberNumber, String memberPassword) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("memberNumber", memberNumber);
	    map.put("memberPassword", memberPassword);
	    return updatePassword(map); 
	}

	// 전화번호 업데이트
	public int updatePhone(int memberNumber, String memberPhone) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("memberNumber", memberNumber);
	    map.put("memberPhone", memberPhone);
	    return updatePhone(map); 
	}
	
	//회원탈퇴
	public int delete(int memberNumber) {
        return sqlSession.delete("myPage.withDrawDelete", memberNumber);
    }

	// 음식 구매 내역
    public List<MyPurchaseDTO> selectMyFoodOrders(Map<String, Integer> pageMap) {
        return sqlSession.selectList("myOrder.myOrderFoodSelect", pageMap);
    }
    // 음식 구매 내역 갯수
    public int selectMyFoodOrdersCount(Map<String, Integer> pageMap) {
        return sqlSession.selectOne("myOrder.myOrderFoodCount", pageMap);
    }
    
    // 재료 구매 내역
    public List<MyPurchaseDTO> selectMyIngreOrders(Map<String, Integer> pageMap) {
        return sqlSession.selectList("myOrder.myOrderIngreSelect", pageMap);
    }

    //재료 구매 내역 개수
    public int selectMyIngreOrdersCount(Map<String, Integer> pageMap) {
        return sqlSession.selectOne("myOrder.myOrderIngreCount", pageMap);
    }
    
    //내가 쓴 게시글 조회
    public List<PostDetailDTO> myPostSelect(Map<String, Integer> pageMap) {
        return sqlSession.selectList("myPage.myPostSelect", pageMap);
    }
    
    //내가 쓴 게시글 전체 수
    public int getMyPostCount(int memberNumber) {
        return sqlSession.selectOne("myPage.getMyPostCount", memberNumber);
    }
    
    // 내가 작성한 댓글 조회
    public List<CommentListDTO> myCommentSelect(Map<String, Object> paramMap) {
        return sqlSession.selectList("myComment.myCommentSelect", paramMap);
    }
    // 내가 작성한 댓글 수 조회
    public int myCommentCount(int memberNumber) {
        return sqlSession.selectOne("myComment.myCommentCount", memberNumber);
    }
    
    // 내가 작성한 리뷰 조회
    public List<ReviewWriteDTO> myReviewSelect(Map<String, Object> paramMap) {
        return sqlSession.selectList("myReview.myReviewSelect", paramMap);
    }
    //내가 작성한 리뷰 수 조회
    public int myReviewCount(int memberNumber) {
        return sqlSession.selectOne("myReview.myReviewCount", memberNumber);
    }
    
}
