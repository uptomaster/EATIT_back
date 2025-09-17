package com.bapseguen.app.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.StoreDTO;
import com.bapseguen.app.dto.view.ReviewWithUserDTO;
import com.bapseguen.config.MyBatisConfig;

public class ReviewDAO {
	private SqlSession sqlSession;

	public ReviewDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	/** 특정 가게의 리뷰 목록 */
	public List<ReviewWithUserDTO> selectReviewsByBusiness(String businessNumber) {
		return sqlSession.selectList("review.selectReviewsByBusiness", businessNumber);
	}

	/** 특정 가게의 리뷰 총 개수 */
	public int countReviewsByBusiness(String businessNumber) {
		return sqlSession.selectOne("review.countReviewsByBusiness", businessNumber);
	}

	/** 특정 가게 정보 조회 */
	public StoreDTO selectStoreInfo(String businessNumber) {
		return sqlSession.selectOne("review.selectStoreInfo", businessNumber);
	}

	/** 특정 가게 평균 별점 */
	public double selectAvgRatingByBusiness(String businessNumber) {
		Double avg = sqlSession.selectOne("review.selectAvgRatingByBusiness", businessNumber);
		return avg != null ? avg : 0.0;
	}

}
