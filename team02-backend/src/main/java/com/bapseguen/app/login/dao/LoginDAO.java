package com.bapseguen.app.login.dao;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.dto.MemberSuspendDTO;
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
    
    // 블랙리스트/블랙상태 여부 (있으면 true)
    public boolean isBlacklisted(int memberNumber) {
        Integer isblacked = sqlSession.selectOne("member.isBlacklisted", memberNumber);
        return isblacked != null && isblacked > 0;
    }

    // 현재 활성 정지 1건 조회 (없으면 null)
    public MemberSuspendDTO findActiveSuspend(int memberNumber) {
        return sqlSession.selectOne("member.findActiveSuspend", memberNumber);
    }    
}