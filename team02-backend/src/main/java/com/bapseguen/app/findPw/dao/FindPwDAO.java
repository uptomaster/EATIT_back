package com.bapseguen.app.findPw.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.config.MyBatisConfig;

public class FindPwDAO {
	public SqlSession sqlSession;
	public FindPwDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public Integer findPw(String memberId, String phoneInput) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
	    map.put("generalPhoneNumber", phoneInput);
	    map.put("sellerPhoneNumber",  phoneInput);
		return sqlSession.selectOne("member.findPw", map);
	}
	
	public int updatePw(int memberNumber, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberNumber", memberNumber);
		map.put("memberPassword", newPassword);
		return sqlSession.update("member.updatePw", map);
	}
    // 일반회원 UPDATED_DATE 갱신
    public int generalUpdatedDate(int memberNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberNumber", memberNumber);
        return sqlSession.update("member.generalUpdatedDate", map);
    }

    // 판매자 UPDATED_DATE 갱신
    public int sellerUpdatedDate(int memberNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberNumber", memberNumber);
        return sqlSession.update("member.sellerUpdatedDate", map);
    }

    // 편의: 비번 변경 후 두 테이블 UPDATED_DATE까지 처리
    public int updatePwAndTouchDates(int memberNumber, String newPassword) {
        int updated = updatePw(memberNumber, newPassword);
        if (updated > 0) {
            generalUpdatedDate(memberNumber);
            sellerUpdatedDate(memberNumber);
        }
        return updated;
    }
}
