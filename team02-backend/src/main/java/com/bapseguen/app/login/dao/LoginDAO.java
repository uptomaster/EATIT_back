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
	
	public String getMemberType(MemberDTO memberDTO) {
	    String type = sqlSession.selectOne("member.getMemberType", memberDTO);
	    if (type == null || type.isEmpty()) return "GENERAL";
	    return type;
	}
}
