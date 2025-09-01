package com.bapseguen.app.login.dao;

import org.apache.ibatis.session.SqlSession;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.config.MyBatisConfig;

public class LoginDAO {
    SqlSession sqlSession;

    public LoginDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    public int login(MemberDTO memberDTO) {
        Integer memberNumber = sqlSession.selectOne("member.login", memberDTO);
        return memberNumber == null ? -1 : memberNumber;
    }

    // 아이디 존재 여부
    public boolean existsMemberId(String memberId) {
        Integer count = sqlSession.selectOne("member.existsId", memberId);
        return count != null && count > 0;
    }

    // 회원유형: 멤버번호로 조회
    public String getMemberType(int memberNumber) {
        String type = sqlSession.selectOne("member.getMemberType", memberNumber);
        return type;
    }
}