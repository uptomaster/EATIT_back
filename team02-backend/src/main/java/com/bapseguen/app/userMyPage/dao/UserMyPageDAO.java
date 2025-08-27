package com.bapseguen.app.userMyPage.dao;

import java.util.HashMap;
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
	
	// 비밀번호 확인
    public boolean checkPassword(int memberNumber, String memberPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberNumber", memberNumber);
        map.put("memberPassword", memberPassword);
        Integer cnt = sqlSession.selectOne("myPage.checkPassword", map);
        return cnt != null && cnt == 1;
    }

	// 내 정보 조회
	public MyPageDTO MyPageSelect(int memberNumber) {
		return sqlSession.selectOne("myPage.myPageSelect", memberNumber);
	}

	// 비밀번호 업데이트
	public int updatePassword(int memberNumber, String newPassword) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("memberNumber", memberNumber);
	    map.put("memberPassword", newPassword);
	    return sqlSession.update("myPage.updatePassword", map);
	}

	// 전화번호 업데이트
	public int updatePhone(int memberNumber, String newPhone) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("memberNumber", memberNumber);
	    map.put("memberPhoneNumber", newPhone);
	    return sqlSession.update("myPage.updatePhone", map);
	}
	public int delete(int memberNumber) {
        return sqlSession.delete("myPage.withDrawDelete", memberNumber);
    }
}
