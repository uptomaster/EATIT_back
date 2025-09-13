package com.bapseguen.app.review.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.bapseguen.app.dto.view.ReviewWithUserDTO;
import com.bapseguen.config.MyBatisConfig;

public class ReviewDAO {
    private SqlSession sqlSession;

    public ReviewDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    // 특정 가게 리뷰 조회
    public List<ReviewWithUserDTO> selectReviewsByBusiness(String businessNumber) {
        return sqlSession.selectList("review.selectReviewsByBusiness", businessNumber);
    }
}
